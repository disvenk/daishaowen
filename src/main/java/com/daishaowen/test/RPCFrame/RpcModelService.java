package com.daishaowen.test.RPCFrame;

public class RpcModelService {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RpcServerFrame serviceServer = new RpcServerFrame(8888);
                    serviceServer.regServer(Service.class,ServiceImpl.class);
                    //其它服务
                    //serviceServer.regServer(Other.class,OtherImpl.class);

                    serviceServer.startService();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
