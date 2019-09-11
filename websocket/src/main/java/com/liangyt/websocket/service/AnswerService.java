package com.liangyt.websocket.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：回答
 * 作者：lyt
 * 日期：2019/9/10 3:54 PM
 * 类名：AnswerService
 * 版本： version 1.0
 */
@Service
public class AnswerService {

    private ListAnswer listAnswer = new ListAnswer();

    public String answer(String qa) {
        return listAnswer.getAnswer(qa);
    }

    class ListAnswer {
        private Map<String, String> list = new HashMap<>();

        public ListAnswer() {
            list.put("身高", "185cm");
            list.put("有多少钱", "您有1万亿");
            list.put("你能回答什么", "小女子知道一些事情, 您想问什么呢?");
            list.put("你知道毛爷爷吗？", "您真讨厌");
        }

        public String getAnswer(String qa) {
            for (String key : list.keySet()) {
                if (key.indexOf(qa) >= 0) {
                    return list.get(key);
                }
            }

            return "请您正确描述问题";
        }
    }
}
