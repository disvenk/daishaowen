package com.daishaowen.test.NioHttpServer;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/*
*
* 手写tomcat服务器
* */
public class DisvenkAioHttpServer {

    private static ThreadPoolExecutor comitTaskPool =(ThreadPoolExecutor) new ScheduledThreadPoolExecutor(40,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    //通道管理器(选择器)，多个用户共用的
    public static Selector selector;

    public static ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        //1.NIO 绑定端口
        serverSocketChannel = ServerSocketChannel.open();
        AsynchronousServerSocketChannel.open();
        //设置通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将该通道的serverSocket绑定到port端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        //2.和操作系统底层建立操作通道
        selector = Selector.open();

        //3.挑选需要处理的连接
        //告诉操作系统底层，当有请求建立连接，发出一个通知，其原理就是监听来自客户端的accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //通知的数量，如果没有的话，等一秒，这期间如果有消息通知(通道就绪)，马上返回
            //参数1000是1秒，参数以毫秒为单位。这个方法会一直阻塞1秒，1秒之内如果没有通道事件就绪的话程序会往下运行
             selector.select(1000);
//             int wait = selector.selectNow();有丢包的风险
//             if(wait==0){
//                continue;
//             }
            //selectNow()其实就是非阻塞，无论有无通道事件就绪，程序都会向下执行
            //selector.selectNow();
            //拿到多个就绪通道集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey detail = iterator.next();
                if(detail.isAcceptable()){//代表有新连接，就是客户端的连接事件
                    //处理连接
                    System.out.println("有新的连接啦，当前线程活跃数量："+comitTaskPool.getActiveCount());
                    //不需要线程池，因为这不是我们需要的状态，这个连接不一定有数据，所以打印的当前活跃线程数量可能是0
                    //获取accept事件通道
                    ServerSocketChannel server = (ServerSocketChannel) detail.channel();
                    //正确的步骤，拿到连接，然后注册事件监听
                    SocketChannel connect = server.accept();
                    connect.configureBlocking(false);
                    //注册了一个该连接，并且注册上数据事件监听器
                    connect.register(selector, SelectionKey.OP_READ);
                }else if(detail.isReadable()){//代表某个连接有数据传递过来了，就是读写事件
                    //获取IO事件通道
                    SocketChannel connect = (SocketChannel) detail.channel();
                    //当前有数据要处理，暂时取消监听
                    detail.cancel();
                    connect.configureBlocking(false);
                    //到此才会创建线程
                    comitTaskPool.execute(()->{
                        //处理过程
                        try {
                            //穿件缓冲区，读取里面的内容
                            //tomcat会根据http协议中定义的长度来读取数据，或者直到通道内无数据为止
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            connect.read(byteBuffer);
                            byteBuffer.flip();//转为读模式
                            String request = new String(byteBuffer.array());
                            //这里是所以会打印出当前线程数，是因为在线程活跃的时候打印的
                            System.out.println("收到新数据，当前线程数"+comitTaskPool.getActiveCount()+",请求内容"+request);
                            //给一个当前时间作为返回值
                            //随意返回去不是http协议
                            byteBuffer.clear();
                            ByteBuffer wrap = ByteBuffer.wrap(("disvenk"+System.currentTimeMillis()).getBytes());
                            connect.write(wrap);
                            wrap.clear();
                            connect.configureBlocking(false);
                            //注册一个监听，表示希望收到该连接上OP_REA事件的通知
                            connect.register(selector,SelectionKey.OP_READ);
                        }catch (Exception e){
                            //e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"服务器线程处理完毕当前线程数"+comitTaskPool.getActiveCount());
                    });
                }else {
                    System.out.println("客户端关闭喽");
                    //SelectionKey对象会失效，这以为这Selector再也不会监控与它相关的事件
                    detail.cancel();
                }
                //删除已选的key，以防止重复处理
                iterator.remove();
            }
            selectionKeys.clear();
            //过滤正在处理的事件
            selector.selectNow();
        }
    }
}
