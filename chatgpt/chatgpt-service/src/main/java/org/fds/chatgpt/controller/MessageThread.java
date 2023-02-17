package org.fds.chatgpt.controller;

import org.fds.chatgpt.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/15
 */
@Component
public class MessageThread extends Thread{
    String message;
    //private Session session;

    private javax.websocket.Session session;

    @Autowired
    private ChatService chatService;

    @Override
    public void run() {
        //chatService.sentToClient(this.message);
        try {
            session.getBasicRemote().sendText("111");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
