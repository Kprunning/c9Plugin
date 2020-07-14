package com.c9.common.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCenter implements Server {
    // map:服务端的所有可供用户端访问的接口都注册到该map中
    // key:接口的名字 value:真正的接口实现
    private static HashMap<String,Class<?>> serviceRegister = new HashMap<>();
    private static int port; // 9999
    private static boolean isRunning = false;
    // 连接池:连接池中存在多个连接对象,每个连接对象可以处理一个客户请求
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);

    public ServerCenter(int port){
        ServerCenter.port = port;
    }

    private static class ServiceTask implements Runnable{
        private Socket socket;

        private ServiceTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 接受并处理客户端请求,包装为序列化流
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                // 因为序列化流对发送数据的顺序严格要求,因此要按照发送顺序逐个接受
                String serviceName = input.readUTF(); // 类名
                String methodName = input.readUTF(); // 方法名
                // 接受参数类型数组,并进行强转,为了通用这里变为Class类型
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject(); // 参数类型数组
                // 接受传来的参数
                Object[] arguments = (Object[]) input.readObject();

                // 根据客户端请求返回接口
                Class<?> serviceClass = serviceRegister.get(serviceName);
                // 根据接口名和参数类型反射得到方法
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                // 通过反射运行方法(反射实例对象,方法参数)
                Object result = method.invoke(serviceClass.getDeclaredConstructor().newInstance(), arguments);

                // 将客户端想要调取的方法结果返回给客户端
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    // 开启服务端
    public void start() throws Exception {
        isRunning = true;
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        while(isRunning){
            System.out.println("服务器启动...");
            // 等待客户端连接
            Socket socket = server.accept();
            // 客户端每发送一个请求,则服务端从线程池中获取一个线程对象去处理
            executor.execute(new ServiceTask(socket));
        }
        server.close();
    }

    @Override
    public void stop() {
        isRunning = false;
        // 线程池关闭
        executor.shutdown();
    }

    @Override
    public void register(Class<?> service, Class<?> serviceImpl) {
        serviceRegister.put(service.getName(),serviceImpl);
    }
}
