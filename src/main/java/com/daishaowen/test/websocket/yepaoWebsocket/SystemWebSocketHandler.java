//package com.daishaowen.test.websocket.yepaoWebsocket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class SystemWebSocketHandler extends  TextWebSocketHandler {
//
//    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("ConnectionEstablished");
//        users.add(session);
//        System.out.println("当前用户"+users.size());
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session,
//            TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
//        sendMessageToUsers(session,message);
//    }
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
//        if(session.isOpen()){
//            session.close();
//        }
//        users.remove(session);
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        users.remove(session);
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//    /**
//     * 给所有在线用户发送消息, E4 c+ [6 ^; O
//     * @Author    张志朋% d# {' ?0 b* m
//     * @param message  void
//     * @Date    2016年3月4日8 ?0 a; d9 |5 Z! Y7 r
//     * 更新日志; ^1 p$ Z, n" j8 j, G3 U/ Z- N# J5 Q/ r
//     * 2016年3月4日 张志朋  首次创建+ J) @  f2 b, i1 {) L
//     *
//     */
//    public void sendMessageToUsers(WebSocketSession session,TextMessage message) {
//        for (WebSocketSession user : users) {
//            try {
//                if (user.isOpen()) {
//                    user.sendMessage(message);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
