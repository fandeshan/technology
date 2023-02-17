package org.fds.chatgpt.service;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/15
 */
@Service
public class ChatService {

    @SendTo("/topic/greetings")
    public String sentToClient(String message){
        return message;
    }
}
