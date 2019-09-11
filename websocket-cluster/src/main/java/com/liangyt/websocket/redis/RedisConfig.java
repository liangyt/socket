package com.liangyt.websocket.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 描述：redis 配置
 * 作者：lyt
 * 日期：2019/9/11 4:09 PM
 * 类名：RedisConfig
 * 版本： version 1.0
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Object> template() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(factory);

        return template;
    }

    @Bean
    public RedisMessageListenerContainer container(MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);

        container.addMessageListener(adapter, new ChannelTopic("socket"));

        return container;
    }

    @Bean
    public MessageListenerAdapter adapter(RedisMessage message) {
        // 注册订阅接收方法
        MessageListenerAdapter adapter = new MessageListenerAdapter(message, "receive");
        // 指定序列化工具 主要是防乱码
        adapter.setSerializer(new JdkSerializationRedisSerializer());
        return adapter;
    }
}
