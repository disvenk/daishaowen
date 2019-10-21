package com.daishaowen.test.RPCFrame;

import java.net.InetSocketAddress;

public class RpcModelClient {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8888);
        Service service = RpcClientFrame.getRemoteProxyObj(Service.class,inetSocketAddress);
        String say = service.say();
        System.out.println(say);

        //调用其它服务
        //OtherService service = RpcClientFrame.getRemoteProxyObj(OtherService.class,inetSocketAddress);
        //String other = OtherService.other();
    }
}
