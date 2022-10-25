package BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞通信架构
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 14:53
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        System.out.println("===服务端启动===");
        // 1.定义一个ServerSocket对象进行服务端的端口注册
        ServerSocket ss = new ServerSocket(5000);
        // 2.定义一个死循环不断的监听接收客户端的socket连接请求
        while (true) {
            Socket socket = ss.accept();
            // 3.创建一个线程来处理与这个客户端socket通信需求
            new ServerThreadReader(socket).start();
        }
    }
    // 缺点：
    // 1.每有一个客户端都会创建线程，线程的竞争，切换上下文影响性能。
    // 2.每个线程占用空间和cpu资源
    // 3.并不是每个线程都会进行io处理
    // 4.客户端过多时会导致线程栈溢出
}
