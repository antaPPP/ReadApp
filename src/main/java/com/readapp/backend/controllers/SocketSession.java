package com.readapp.backend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.readapp.backend.config.WebSocketConfig;
import com.readapp.backend.dao.UserDao;
import com.readapp.backend.models.User;
import com.readapp.backend.models.events.Event;
import com.readapp.backend.services.UserService;
import com.readapp.backend.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/messaging/{token}", configurator = WebSocketConfig.class)
@Component
public class SocketSession {

    Logger log = LoggerFactory.getLogger(SocketSession.class);

    //要注入的service或者dao
    @Autowired
    private static UserDao userDao;

    @Autowired
    public void setChatService(UserDao userDao) {
        SocketSession.userDao = userDao;
    }

    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    public static ConcurrentHashMap<String,SocketSession> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {

        this.session = session;

        this.userId= JWTUtil.getUserId(token);

        if (userId == null) return;

        System.out.println(userId);

        Optional<User> optionalUser = userDao.findById(Long.parseLong(userId));

        if (!optionalUser.isPresent()) return;

        User user = optionalUser.get();

        if (!JWTUtil.verify(token, String.valueOf(user.getId()), user.getPassword())) return;

        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage(JSONObject.toJSONString(new Event().setType("connect").setData(null)));
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
            try {
                sendMessage(JSONObject.toJSONString(new Event().setType("disconnect").setData(null)));
            } catch (IOException e) {
                log.error("用户:"+userId+",网络异常!!!!!!");
            }
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        System.out.println(message);
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SocketSession.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SocketSession.onlineCount--;
    }

}
