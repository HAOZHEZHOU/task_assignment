package com.yc.tmwk.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.tmwk.model.Chat;
import com.yc.tmwk.model.Users;
import com.yc.tmwk.service.ChatService;
import com.yc.tmwk.util.SocketHttpSession;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket",configurator = SocketHttpSession.class)
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<Integer, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();

    //socket session
    private Session session;
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private Integer id;

    private static ApplicationContext ctx;



    @Autowired
    ChatService chatService;


    public static void setCtx(ApplicationContext applicationContext){
        ctx = applicationContext;
    }

    /**
     * On connection open*/
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession;
        try{
            httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
            Users user = (Users)httpSession.getAttribute("user");
            this.id = user.getUserId();//接收到发送消息的人员编号
            webSocketSet.put(id, this);     //加入set中
            addOnlineCount();           //在线数加1
            log.info("User"+id+"attend！Current online users counts:" + getOnlineCount());
            sendMessage("Socket connected success");
        }catch(NullPointerException e){
            log.info("no login,exist");
        }catch(IOException e){
            log.error("websocket IO Exception");
        }
    }

    /**
     * onClose
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("One connection closed,Current counts:" + getOnlineCount());
    }

    /**
     * on received Message
     *
     * @param message
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("From client:" + message);
        JsonFactory factory = new JsonFactory();
        try {
            Chat results = new ObjectMapper().readValue(message,Chat.class);
            results.setCreateDate(new Date());
            results.setChatFrom(id);
            log.info("parser：:" + results);
            sendtoUser(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Socket error");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * Send message to user
     * @param msg
     * @throws IOException
     */
    public void sendtoUser(Chat msg) throws IOException {

       chatService = (ChatService)ctx.getBean(ChatService.class);
       if(webSocketSet.get(msg.getChatTo()) != null){
           msg.setState(Short.valueOf("1"));
           chatService.insert(msg);
           webSocketSet.get(msg.getChatTo()).sendMessage(new ObjectMapper().writeValueAsString(msg));
       }else{
           //not online
           msg.setState(Short.valueOf("0"));
           log.debug("User is not online，DB insert message：" + msg);
           chatService.insert(msg);
           //todo chatService.insert(); state =0
       }
    }

    /**
     * send to all
     * @param message
     * @throws IOException
     */
    public void sendtoAll(String message) throws IOException {
        for (Integer key : webSocketSet.keySet()) {
            try {
                webSocketSet.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}
