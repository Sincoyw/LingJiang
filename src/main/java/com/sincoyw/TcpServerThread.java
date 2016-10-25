package com.sincoyw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Sincoyw on 2016/10/25.
 * Desc:
 */
public class TcpServerThread implements Runnable {

    private Socket client = null;

    public TcpServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            // 获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            // 获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;

            while (flag) {
                // 接收从客户端发送过来的数据
                String string = bufferedReader.readLine();
                if (null == string || string.isEmpty()) {
                    flag = false;
                } else {
                    if ("bye".equals(string)) {
                        flag = false;
                    } else {
                        // 将接收到的字符串前面加入echo，发送到对应的客户端
                        out.println("echo: " + string);
                    }
                }
            }
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
