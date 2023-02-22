package org.fds.chatgpt.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.fds.chatgpt.config.OpenaiApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/22
 */
@EnableConfigurationProperties({OpenaiApiConfig.class})
@Component
@Slf4j
public class SSeServer {

    @Autowired
    private OpenaiApiConfig openaiApiConfig;

    public static final String API_PARAM_MODEL = "model";
    public static final String API_PARAM_PROMPT = "prompt";
    public static final String API_PARAM_TEMPERATURE = "temperature";
    public static final String API_PARAM_MAX_TOKEN = "max_tokens";
    public static final String API_PARAM_STREAM = "stream";
    public static final String API_PARAM_SUFFIX = "suffix";
    public static final String API_PARAM_TOP_P = "top_p";
    public static final String API_PARAM_N = "n";
    public static final String API_PARAM_LOGPROBS = "logprobs";
    public static final String API_PARAM_PRESENCE_PENALTY = "presencePenalty";
    public static final String API_PARAM_FREQUENCY_PENALTY = "frequencyPenalty";
    public static final String API_PARAM_BEST_OF = "bestOf";
    public static final String API_CONSTANT_ENDOFTEXT = "<|endoftext|>";
    public static final String API_CONSTANT_NEWLINE = "\n\n";

    public JSONObject callTextCompletionBySse(String requestBody,SseEmitter sseEmitter){
        try {
            URL url = new URL(openaiApiConfig.getApiPrefixUrl()+openaiApiConfig.getTextCompletionUrl());

            trustAllHosts();

            URLConnection urlConnection = url.openConnection();

            if (url.toString().startsWith("https")){
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)urlConnection;
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
            }else{
                HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                httpURLConnection.setRequestMethod("POST");
            }
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            urlConnection.setRequestProperty("Authorization", "Bearer "+openaiApiConfig.getAppKey());


            byte[] dataBytes = requestBody.getBytes();

            urlConnection.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));

            OutputStream os = urlConnection.getOutputStream();

            os.write(dataBytes);

            os.flush();
            os.close();

            InputStream is = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = null;

            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";

            StringBuffer sb = new StringBuffer();
            JSONObject returnJson = new JSONObject();
            while((line = reader.readLine()) != null) {
                log.info("open ai completion response stream : "+line);
                line = line.replace("data: ","");
                if ("[DONE]".equals(line)|| StringUtils.isEmpty(line)){
                    continue;
                }
                JSONObject jsonObject = JSONObject.fromObject(line);
                returnJson = jsonObject;
                JSONArray choicesJson = jsonObject.getJSONArray("choices");
                String text = choicesJson.getJSONObject(choicesJson.size() - 1).getString("text");
                sb.append(text);
                sseEmitter.send(text);

            }
            JSONArray choicesJson = returnJson.getJSONArray("choices");
            choicesJson.getJSONObject(choicesJson.size() - 1).put("text",sb.toString());
            sseEmitter.complete();
            reader.close();
            return returnJson;
        } catch(Exception e) {
            IllegalArgumentException ee = new IllegalArgumentException("The system is currently busy, please try again later!");
            sseEmitter.completeWithError(ee);
            log.error("callTextCompletionBySse Error:", e);
            throw ee;
        }
    }

    /**
     * 不检查任何证书
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // 创建信任管理器
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                log.info(TAG);
            }
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                log.info(TAG);
            }
        }};
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            log.error(" trustAllHosts Error:", e);
        }
    }

    /**
     * 拼接请求json
     * @param requestMessage 当前提问
     * @param historyRequests 历史提问，用于衔接上下文
     * @return
     */
    public JSONObject genTextCompleteRequestJson(String requestMessage, List<String> historyRequests){
        JSONObject requestJson = new JSONObject();
        requestJson.put(API_PARAM_MODEL,openaiApiConfig.getModel());
        StringBuffer promptSB = new StringBuffer();
        if(CollectionUtils.isNotEmpty(historyRequests)){
            for (String historyRequest:historyRequests) {
                promptSB .append(historyRequest + API_CONSTANT_ENDOFTEXT)
                        .append(API_CONSTANT_NEWLINE);
            }
        }

        promptSB.append(requestMessage)
                .append(API_CONSTANT_ENDOFTEXT)
                .append(API_CONSTANT_NEWLINE);
        String prompts = promptSB.toString();
        log.info("原请求 prompt长度:"+promptSB.length());

        if (prompts.length() > openaiApiConfig.getRequestMaxPromptLen()){
            prompts = prompts.substring(prompts.length() - openaiApiConfig.getRequestMaxPromptLen());
        }
        log.info("截取后 prompt长度:"+prompts.length());
        requestJson.put(API_PARAM_PROMPT,prompts);
        requestJson.put(API_PARAM_STREAM,true);
        requestJson.put(API_PARAM_TEMPERATURE,openaiApiConfig.getTemperature());
        requestJson.put(API_PARAM_MAX_TOKEN,openaiApiConfig.getMaxTokens());
        if (openaiApiConfig.getN() != null){
            requestJson.put(API_PARAM_N,openaiApiConfig.getN());
        }
        if (openaiApiConfig.getSuffix() != null){
            requestJson.put(API_PARAM_SUFFIX,openaiApiConfig.getSuffix());
        }
        if (openaiApiConfig.getTopP() != null){
            requestJson.put(API_PARAM_TOP_P,openaiApiConfig.getTopP());
        }
        if (openaiApiConfig.getLogprobs() != null){
            requestJson.put(API_PARAM_LOGPROBS,openaiApiConfig.getLogprobs());
        }
        if (openaiApiConfig.getPresencePenalty() != null){
            requestJson.put(API_PARAM_PRESENCE_PENALTY,openaiApiConfig.getPresencePenalty());
        }
        if (openaiApiConfig.getFrequencyPenalty() != null){
            requestJson.put(API_PARAM_FREQUENCY_PENALTY,openaiApiConfig.getFrequencyPenalty());
        }
        if (openaiApiConfig.getBestOf() != null){
            requestJson.put(API_PARAM_BEST_OF,openaiApiConfig.getBestOf());
        }
        return requestJson;
    }

}
