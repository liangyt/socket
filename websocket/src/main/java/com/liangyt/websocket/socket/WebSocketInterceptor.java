package com.liangyt.websocket.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * 描述：websocket 请求拦截
 * 作者：lyt
 * 日期：2019/9/11 11:07 AM
 * 类名：WebSocketInterceptor
 * 版本： version 1.0
 */
@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String userId = ((ServletServerHttpRequest)request).getServletRequest().getParameter("userid");
        attributes.put("userid", userId);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
