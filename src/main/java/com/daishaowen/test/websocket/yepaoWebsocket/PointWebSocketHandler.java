//package com.daishaowen.test.websocket.yepaoWebsocket;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//
//public class PointWebSocketHandler extends  TextWebSocketHandler {
//
//
//	    //在线用户列表
//	    private static final ConcurrentHashMap<Long, WebSocketSession> users;
//	    //用户标识
//	    private static final String CLIENT_ID = "user";
//
//	    static {
//	        users = new ConcurrentHashMap<>();
//	    }
//
//
//	    @Override
//	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//	        System.out.println("成功建立连接");
//	        Long userId = getClientId(session);
//	        if (userId != null) {
//	            users.put(userId, session);
//	            //session.sendMessage(new TextMessage("成功建立socket连接"));
//	            System.out.println(userId);
//	            System.out.println(session);
//	        }
//	    }
//
//	    @Override
//	    public void handleTextMessage(WebSocketSession session, TextMessage message) {
//	        // ...
//	        System.out.println(message.getPayload());
//
//	       // WebSocketMessage message1 = new TextMessage("server:"+message);
//	        sendMessageToUser(13100738632L,message);
//	       /* try {
//	            session.sendMessage(message1);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }*/
//	    }
//
//	    /**
//	     * 发送信息给指定用户
//	     * @param clientId
//	     * @param message
//	     * @return
//	     */
//	    public boolean sendMessageToUser(Long clientId, TextMessage message) {
////	    	OrderAlertService apImpl = (OrderAlertService) MyApplicationContextUtil.getContext().getBean("orderAlertServiceImpl");
////	    	System.out.println("走了这里");
////	        if (users.get(clientId) == null){
////	        	apImpl.saveOrderAlert(clientId);
////	        	return false;
////	        }
////	        WebSocketSession session = users.get(clientId);
////	        System.out.println("sendMessage:" + session);
////	        if (!session.isOpen()){
////	        	apImpl.saveOrderAlert(clientId);
////	        	return false;
////	        }
////	        try {
////	            session.sendMessage(message);
////	        } catch (IOException e) {
////	            e.printStackTrace();
////	            return false;
////	        }
//	        return true;
//	    }
//
//		//连接错误而断开
//	    @Override
//	    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//	        if (session.isOpen()) {
//	            session.close();
//	        }
//	        System.out.println("连接出错");
//	        users.remove(getClientId(session));
//	    }
//
//	//session过期自然断开
//	    @Override
//	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//	        System.out.println("连接已关闭：" + status);
//	        users.remove(getClientId(session));
//	    }
//
//	    @Override
//	    public boolean supportsPartialMessages() {
//	        return false;
//	    }
//
//	    /**
//	     * 获取用户标识
//	     * @param session
//	     * @return
//	     */
//	    private Long getClientId(WebSocketSession session) {
//	        try {
//	            Long clientId = (Long) session.getAttributes().get("websocket_username");
//	           // String objectToJson = JsonUtils.objectToJson(session.getAttributes());
//	           // System.out.println(objectToJson);
//	            return clientId;
//	        } catch (Exception e) {
//	            return null;
//	        }
//	    }
//	}
//
