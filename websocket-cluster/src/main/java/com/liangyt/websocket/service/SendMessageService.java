package com.liangyt.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liangyt.websocket.redis.RedisMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 描述：发送消息到 redis 的订阅
 * 作者：lyt
 * 日期：2019/9/11 4:53 PM
 * 类名：SendMessageService
 * 版本： version 1.0
 */
@Service
public class SendMessageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate template;
    @Autowired
    private ObjectMapper mapper;

    /**
     * 批定发送的用户进行消息发送
     * @param userId
     * @param message
     * @throws Exception
     */
    public void send(String userId, String message) throws Exception {
        String json = mapper.writeValueAsString(new RedisMsg(userId, message));
        logger.info("sendMessage -> {}", json);
        template.convertAndSend("socket", json);
    }
}
