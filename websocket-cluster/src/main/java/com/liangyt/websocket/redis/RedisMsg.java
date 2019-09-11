package com.liangyt.websocket.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述：订阅传播的消息
 * 作者：lyt
 * 日期：2019/9/11 4:29 PM
 * 类名：RedisMsg
 * 版本： version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedisMsg implements Serializable {
    // 用户id
    private String userId;
    // 发送的消息
    private String message;
}
