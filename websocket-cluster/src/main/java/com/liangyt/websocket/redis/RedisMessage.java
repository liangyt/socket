package com.liangyt.websocket.redis;

import org.springframework.stereotype.Component;

/**
 * 描述：redis 接收消息
 * 作者：lyt
 * 日期：2019/9/11 4:21 PM
 * 接口：RedisMessage
 * 版本： version 1.0
 */
@Component
public interface RedisMessage {
    void receive(String message) throws Exception;
}
