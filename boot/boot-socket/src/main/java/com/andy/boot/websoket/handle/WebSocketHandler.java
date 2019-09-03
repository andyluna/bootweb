package com.andy.boot.websoket.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2018-12-21 星期五 16:00
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketHandler {
    private Session session;

    private static CopyOnWriteArraySet<WebSocketHandler> cacheHandler = new CopyOnWriteArraySet<WebSocketHandler>();

    @OnOpen
    public void open(Session session,EndpointConfig config){
        log.info("开始连接 session"+session);
        this.session = session;
        cacheHandler.add(this);
        sendAllMessage("服务器说"+session.getId()+"于"+getCurDate()+"上线了");
    }

    /**
     * 收到信息时触发
     * @param message
     */
    @OnMessage
    public void onMessage(Session session ,String message){
        log.info("发送消息 session"+session);
        sendAllMessage(session.getId()+"于"+getCurDate()+"说: "+message);
    }

    /**
     * 连接关闭触发
     */
    @OnClose
    public void onClose(Session session){
        cacheHandler.remove(this);
        log.info("关闭 session"+session);
        sendAllMessage("服务器说"+session.getId()+"于"+getCurDate()+"下线了");
    }

    /**
     * 发生错误时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }




    /**
     * 获取当前连接数
     * @return
     */
    public static int getOnlineNum(){
        return cacheHandler.size();
    }





    public void sendMessage(Session session ,String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAllMessage(String message){
        for(WebSocketHandler handler:cacheHandler){
            sendMessage(handler.getSession() , message);
        }
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public static String getCurDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }


    public static void main(String[] args) {
        System.out.println(getCurDate());
    }


}
