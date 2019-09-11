package com.liangyt.websocket.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：初始化工具
 * 作者：lyt
 * 日期：2019/9/11 4:30 PM
 * 类名：CommonConfig
 * 版本： version 1.0
 */
@Configuration
public class CommonConfig {

    @Bean
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 序列化时只包含不为空的字段
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略类不存在的字段
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
