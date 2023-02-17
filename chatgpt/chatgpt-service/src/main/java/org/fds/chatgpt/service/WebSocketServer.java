package org.fds.chatgpt.service;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.fds.chatgpt.entity.SocketMessage;
import org.fds.chatgpt.uitls.SSLClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.sse.SseEventSource;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/15
 */
@ServerEndpoint("/web/socket/{sid}")
@Component

public class WebSocketServer {
    static Log log= LogFactory.getLog(WebSocketServer.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收sid
     */
    private String sid="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        try {
            sendMessage(new SocketMessage(1,"connection success"));
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(SocketMessage socketMessage) throws IOException {
        if (socketMessage.getMessageType() == null){
            sendMessage(socketMessage.getMessage());
        }
        this.session.getBasicRemote().sendText(socketMessage.getMessage());
    }
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        try {
            URL url = new URL("https://api.openai.com/v1/completions");

            trustAllHosts();

            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");

            urlConnection.setDoOutput(true);

            urlConnection.setDoInput(true);

            urlConnection.setUseCaches(false);

            urlConnection.setRequestProperty("Connection", "Keep-Alive");


            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Authorization", "Bearer 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");
            urlConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

            byte[] dataBytes = new String("{\n" +
                    "\t\"model\" : \"" + "text-davinci-003" + "\",\n" +
                    "\t\"prompt\" : \"" + message + "\",\n" +
                    "\t\"stream\" : " + "true" + ",\n" +
                    "\t\"temperature\" : " + "0" + ",\n" +
                    "\t\"user\" : \"" + "fandeshan" + "\",\n" +
                    "\t\"max_tokens\" : " + "2048" + "\n" +
                    "}").getBytes();

            urlConnection.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));

            OutputStream os = urlConnection.getOutputStream();

            os.write(dataBytes);

            os.flush();

            os.close();

            InputStream is = new BufferedInputStream(urlConnection.getInputStream());



            BufferedReader reader = null;

            StringBuffer sb = new StringBuffer();


            reader = new BufferedReader(new InputStreamReader(is));

            String line = "";

            while((line = reader.readLine()) != null) {
                line = line.replace("data: ","");
                if ("[DONE]".equals(line)|| StringUtils.isEmpty(line)){
                    continue;
                }

                JSONObject jsonObject = JSONObject.fromObject(line);
                System.out.println(line);
                this.session.getBasicRemote().sendText(jsonObject.getJSONArray("choices").getJSONObject(0).getString("text"));

            }

            reader.close();

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());

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
            e.printStackTrace();
        }
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
