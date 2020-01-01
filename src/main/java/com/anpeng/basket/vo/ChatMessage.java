package com.anpeng.basket.vo;

import lombok.Data;

@Data
public class ChatMessage {
    /**
     * 消息类型
     */
    private MessageType type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 发送者
     */
    private String sender;

    public enum MessageType {
        /**
         * 消息
         */
        CHAT,
        /**
         * 加入
         */
        JOIN,
        /**
         * 离开
         */
        LEAVE
    }
}
