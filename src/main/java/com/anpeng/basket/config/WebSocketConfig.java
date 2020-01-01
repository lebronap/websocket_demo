package com.anpeng.basket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//启用websocket服务器
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册一个websocket端点，客户端使用它链接到websocket服务器
     * stomp代表简单文本导向的消息传递协议，用于定义数据交换的格式和规则。websocket只是一种通信协议。
     * 它没有定义诸如以下内容：如何仅向订阅特定主题的用户发送消息，或者如何向特定用户发送消息。我们需要STOMP来实现这些功能。
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //withSockJS 是用来为不支持websocket的浏览器的后备选项
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * 配置一个消息代理，用于将消息从一个客户端路由到另一个客户端
     * 目前使用内存中的消息代理，可以使用rabbitmq 或 activemq等消息进行代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 以/app开头的消息应该路由到消息处理方法
        registry.setApplicationDestinationPrefixes("/app");
        // 以/topic开头的消息应该路由到消息代理。消息代理向订阅特定主题的所有连接客户端广播消息
        registry.enableSimpleBroker("/topic");
    }
}
