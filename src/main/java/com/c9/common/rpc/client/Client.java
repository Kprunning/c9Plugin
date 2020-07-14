package com.c9.common.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    // 获取远程服务端的动态代理对象(JJCMain)
    // 这里用<T>来代表任意类型,相当于object
    public  static <T> T getRemoteProxyObj(Class<?> serviceInterface, InetSocketAddress addr){
        // 返回动态代理对象
        // newProxyInstance(),默认返回值是Object,前面是T就强转下
        // newProxyInstance(a,b,c) a:类加载器,将接口的类加载器传入第一个参数
        // b:接口数组  c:通过新建对象,返回服务器处理后的返回值
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface}, new InvocationHandler() {
                    @Override
                    // 反射创建实例invoke(Object proxy,Method method,Object[] args)
                    // proxy:动态代理对象 method:代理对象的方法 args:方法的参数列表
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 客户端向服务端发送请求:请求某一个具体的接口
                        Socket socket = new Socket();
                        // InetSocketAddress addr 包含了IP和端口
                        socket.connect(addr);
                        // 返回序列化流(对象流)
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        // 客户端发送内容:接口名,方法, writeUTF接受字符串类型
                        output.writeUTF(serviceInterface.getName()); // 接口名
                        output.writeUTF(method.getName()); // 方法名
                        // 方法参数类型,方法参数列表,output.writeObject()接收任意类型
                        output.writeObject(method.getParameterTypes()); // 方法的参数类型
                        output.writeObject(args); // 方法的参数

                        // 等待服务器处理,然后接收服务端处理后的返回值
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                        return input.readObject();
                    }
                });
    }
}
