package com.sincoyw;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception {
    	// write your code here
        System.out.println("Hello world!!");

        // 服务端在20006端口监听客户端请求的TCP连接
        ServerSocket serverSocket = new ServerSocket(20006);
        Socket client = null;

        boolean f = true;
        while (f) {
            // 等待客户端的连接，如果没有获取连接
            client = serverSocket.accept();
            System.out.println("与客户端连接成功！");
            // 为每个客户端连接开启一个线程
            new Thread(new TcpServerThread(client)).start();
        }
        serverSocket.close();
    }
}
