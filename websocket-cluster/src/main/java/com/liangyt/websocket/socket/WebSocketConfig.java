package com.liangyt.websocket.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 描述：
 * 作者：lyt
 * 日期：2019/9/10 2:14 PM
 * 类名：WebSocketConfig
 * 版本： version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Autowired
    private AnswerSocketHandler handler;
    @Autowired
    private WebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "/answer").addInterceptors(interceptor);
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "/answerSockJs").addInterceptors(interceptor).withSockJS();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return handler;
    }
}
