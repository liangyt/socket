package com.liangyt.websocket.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 描述：自动关闭 websocket
 * 作者：lyt
 * 日期：2019/9/11 1:32 PM
 * 类名：AutoCloseWebSocketSchedule
 * 版本： version 1.0
 */
@Component
@EnableScheduling
public class AutoCloseWebSocketSchedule {

    @Autowired
    private AnswerSocketHandler handler;

    /**
     * 每分钟跑一次 看看在线情况
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void autoClose() throws Exception {
        Map<String, AnswerSocketHandler.Node> onlineList = handler.getOnlineList();

        LocalDateTime now = LocalDateTime.now();
        for (String userId : onlineList.keySet()) {
            if (!onlineList.containsKey(userId)) continue;
            Duration duration = Duration.between(onlineList.get(userId).getTime(), now);
            // 没有沟通一分钟及以上的 关闭
            if (duration.toMinutes() >= 1) {
                handler.autoSend(userId, "您好久不说话，我要关闭了");
                handler.autoClose(userId);
            }
        }
    }
}
