package com.liangyt.websocket.socket;

import com.liangyt.websocket.service.AnswerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述：处理 websocket 连接/发送/关闭等
 * 作者：lyt
 * 日期：2019/9/9 5:36 PM
 * 类名：AnswerSocketHandler
 * 版本： version 1.0
 */
@Component
public class AnswerSocketHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 连接列表
    private Map<String, Node> onlineList = new HashMap<>();

    @Autowired
    private AnswerService answerService;

    /**
     * 在WebSocket协商成功并且WebSocket连接打开并准备好使用后调用。
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        InetSocketAddress address = session.getLocalAddress();

        Object oUserId = session.getAttributes().get("userid");
        String userId = null;
        if (Objects.nonNull(oUserId)) {
            userId = (String) oUserId;
        }

        onlineList.put(session.getId(), new Node(session, userId, LocalDateTime.now()));

        logger.info("hosoname: {}, port: {}, userid: {}", address.getHostName(), address.getPort(), userId);
    }

    /**
     * 在新的WebSocket消息到达时调用。
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String content = message.getPayload();
        logger.info("receive: {}", content);
        session.sendMessage(new TextMessage(answerService.answer(content)));

        // 刷新最新的在线时间
        onlineList.get(session.getId()).setTime(LocalDateTime.now());
    }

    /**
     * 处理底层WebSocket消息传输中的错误。
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    /**
     * WebSocket连接被任何一方关闭后，或者在发生传输错误后调用。
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.warn("closed: {}", status.getReason());
        onlineList.remove(session.getId());
    }

    /**
     * WebSocketHandler是否处理部分消息。
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return super.supportsPartialMessages();
    }

    /**
     * 指定发送
     * @param sessionId
     * @param msg
     * @throws Exception
     */
    public void autoSend(@NotNull String sessionId, String msg) throws Exception {
        onlineList.get(sessionId).getSession().sendMessage(new TextMessage(msg));
    }

    /**
     * 指定关闭
     * @param sessionId
     * @throws Exception
     */
    public void autoClose(@NotNull String sessionId) throws Exception {
        onlineList.get(sessionId).getSession().close();
        onlineList.remove(sessionId);
    }

    /**
     * 返回连接列表
     * @return
     */
    public Map<String, Node> getOnlineList() {
        return onlineList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Node {
        private WebSocketSession session;
        private String userId;
        private LocalDateTime time;
    }
}
