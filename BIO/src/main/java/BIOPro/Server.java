package BIOPro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步通信架构
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 14:53
 */
public class BioProServer {
    public static void main(String[] args) throws IOException {
        System.out.println("===服务端启动===");
        // 1.定义一个ServerSocket对象进行服务端的端口注册
        ServerSocket ss = new ServerSocket(5000);
        // 2.定义一个死循环不断的监听接收客户端的socket连接请求
        // 初始化一个线程池对象
        HandlerSocketServerPool pool = new HandlerSocketServerPool(6, 10);
        while (true) {
            Socket socket = ss.accept();
            // 3.把socket对象交给线程池处理
            Runnable target = new ServerProRunnableTarget(socket);
            pool.execute(target);
        }
    }
}
