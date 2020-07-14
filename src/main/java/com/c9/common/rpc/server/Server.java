package com.c9.common.rpc.server;

public interface Server {
    void start() throws Throwable;

    void stop();

    void register(Class<?> service,Class<?> serviceImpl);

}
