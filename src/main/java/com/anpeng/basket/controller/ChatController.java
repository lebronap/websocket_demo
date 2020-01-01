package com.anpeng.basket.controller;

import com.anpeng.basket.vo.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * controller用来接收和发送消息
 * 以/app开头的客户端发送的消息都将路由到这些使用@MessageMapping的消息处理方法
 */
@Controller
public class ChatController {

    /**
     * 发送消息将路由到此方法
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    //发送到/topic/public
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }


    /**
     * 添加用户将路由到此方法
     * @param chatMessage
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/chat.addUser")
    //发送到/topic/public
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }
}
