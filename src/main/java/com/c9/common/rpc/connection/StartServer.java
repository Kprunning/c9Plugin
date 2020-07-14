package com.c9.common.rpc.connection;

import com.c9.common.rpc.server.ServerCenter;
import com.c9.jjc.service.JJCService;
import com.c9.jjc.service.impl.JJCServiceImpl;

public class StartServer {
    public static void main(String[] args) {
        // 开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerCenter serverCenter = new ServerCenter(63333);
                // 将JJC接口和实现类注册到服务中心上
                serverCenter.register(JJCService.class, JJCServiceImpl.class);
                try {
                    serverCenter.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
