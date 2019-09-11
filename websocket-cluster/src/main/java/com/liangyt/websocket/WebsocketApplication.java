package com.liangyt.websocket;

import com.liangyt.websocket.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }

    @Autowired
    private SendMessageService sendMessageService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sockjs")
    public String sockjs() {
        return "sockjs";
    }

    /**
     * 给指定用户发送消息
     * @param userId
     * @param msg
     * @return
     */
    @GetMapping("/send")
    @ResponseBody
    public String send(@RequestParam(required = false) String userId, String msg) throws Exception {
        sendMessageService.send(userId, msg);
        return "成功";
    }
}
