package com.c9.common.rpc.connection;

import com.c9.common.rpc.client.Client;
import com.c9.jjc.service.JJCService;

import java.net.InetSocketAddress;

public class StartClient {
    public static void main(String[] args) throws ClassNotFoundException {
        JJCService jjc = Client.getRemoteProxyObj(Class.forName("com.c9.jjc.service.JJCService"), new InetSocketAddress("127.0.0.1", 63333));
        jjc.sayHi("张文");
    }

}
