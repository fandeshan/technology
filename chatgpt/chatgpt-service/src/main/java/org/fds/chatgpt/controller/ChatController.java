package org.fds.chatgpt.controller;

import net.sf.json.JSONObject;
import org.fds.chatgpt.service.SSeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/13
 */

@Controller
public class ChatController {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    @MessageMapping("/hello")

    public String greeting(String message) throws Exception{
//        CloseableHttpClient client = null;
//        try {
//            client = new SSLClient();
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
//        String result = "";
//        post.addHeader("Authorization", "Bearer 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");
//        post.addHeader("charset", "utf-8");
//
//        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset
//                .forName("UTF-8"));
//        StringEntity myEntity = new StringEntity("{\n" +
//                "\t\"model\" : \"" + "text-davinci-003" + "\",\n" +
//                "\t\"prompt\" : \"" + "GPT-3模型都有哪些封装版本" + "\",\n" +
//               // "\t\"stream\" : " + "true" + ",\n" +
//                "\t\"temperature\" : " + "0" + ",\n" +
//                "\t\"max_tokens\" : " + "2048" + "\n" +
//                "}",
//                ContentType.APPLICATION_JSON);// 构造请求数据
//        long startTime = System.currentTimeMillis();
//
//        post.setEntity(myEntity);// 设置请求体
//        //reqEntity.addPart("uid", uid);
//        //reqEntity.addPart("message", message);
//        //post.setEntity(reqEntity);
//        CloseableHttpResponse httpResponse;
//        try {
//            httpResponse = client.execute(post);
//            HttpEntity httpEntity = httpResponse.getEntity();
//
//            if (null != reqEntity) {
//                result = EntityUtils.toString(httpEntity, "utf-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("接口响应耗时："+ (System.currentTimeMillis() - startTime));
//        System.out.println(result);
//        Thread.sleep(1000);
        MessageThread messageThread = new MessageThread();
        messageThread.message = "测试，你好";
        executorService.execute(messageThread);
        return "Hello client:"+message;
    }

    @Autowired
    private SSeServer sSeServer;

    @GetMapping(value = "/chatAI/textCompletionStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getChatAITextCompletionStream(@RequestParam(required = true)String requestMessage,
                                                    @RequestParam(required = false)String conversationId) {

        SseEmitter sseEmitter = new SseEmitter(120000L);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() ->
        {
            JSONObject requestJson = sSeServer.genTextCompleteRequestJson(requestMessage, null);
            sSeServer.callTextCompletionBySse(requestJson.toString(),sseEmitter);

        });

        return sseEmitter;
    }


}
