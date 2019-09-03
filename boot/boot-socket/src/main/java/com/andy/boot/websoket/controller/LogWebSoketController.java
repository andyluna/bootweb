package com.andy.boot.websoket.controller;//package com.andy.boot.websoket.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//
///**
// * @author : andy<xiangdan311@163.com>
// * @description:
// * @create : 2018-12-21 星期五 14:30
// */
//@RestController
//@ServerEndpoint("/websocket")
//public class LogWebSoketController {
//    private static Logger logger = LoggerFactory.getLogger(LogWebSoketController.class);
//    private Session session;//每个websocket会话连接对应一个session
//    private static Set<LogWebSoketController> webSocketSet = new CopyOnWriteArraySet<LogWebSoketController>();
//
//    @OnOpen
//    public void onOpen(Session session) throws IOException {
//        this.session = session;
//        webSocketSet.add(this);
//        session.getBasicRemote().sendText("正在获取日志<br>");
//    }
//
//    /**
//     * WebSocket请求关闭。
//     * websocket连接关闭后，会触发onClose方法
//     */
//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this);
//    }
//
//    @OnError
//    public void onError(Throwable thr) {
//        thr.printStackTrace();
//    }
//
////    @OnMessage
////    public void onMessage (String message, Session session) {
////        logger.info("来自客户端的message:" + message);
////
////
////        for ( Session item : webSocketSet ){
////                item.getBasicRemote().sendText(message);
////        }
////    }
//
//}
