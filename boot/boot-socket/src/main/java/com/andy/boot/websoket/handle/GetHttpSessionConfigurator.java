package com.andy.boot.websoket.handle;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2018-12-21 星期五 17:17
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec,
                                HandshakeRequest request, HandshakeResponse response) {
        //fetchContainerDefaultConfigurator().modifyHandshake(sec, request, response);
//        // TODO Auto-generated method stub
        HttpSession httpSession=(HttpSession) request.getHttpSession();
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);

    }
}
