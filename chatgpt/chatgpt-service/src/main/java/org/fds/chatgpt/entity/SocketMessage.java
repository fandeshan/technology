package org.fds.chatgpt.entity;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/15
 */
public class SocketMessage {

    private Integer messageType;
    private String message;

    public SocketMessage(){

    }

    public SocketMessage(Integer messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
