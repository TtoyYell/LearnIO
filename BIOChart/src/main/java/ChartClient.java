import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 14:55
 */
public class ChartClient {
    public static void main(String[] args) throws IOException {
        System.out.println("===客户端启动===");
        // 1.创建Socket对象请求服务端的连接
        Socket socket = new Socket("127.0.0.1", 5000);
        // 2.从Socket对象中获取一个字节输出流 封装成数据流
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        // 3.把字节输出流包装成一个打印流
        Scanner scanner = new Scanner(System.in);
        // 初始化一个线程池对象
        HandlerSocketClientPool pool = new HandlerSocketClientPool(6, 10);
        // 把socket对象交给线程池处理,while循环监听是否有信息发来
        Runnable target = new ClientRunnableTarget(socket);
        pool.execute(target);

        // 登录
        System.out.print("输入自己id登录:");
        String ownId = scanner.nextLine();
        dataOutputStream.writeUTF(ownId);
        for (int i = 0; i < 10; i++) {
            System.out.print("输入id:");
            String id = scanner.nextLine();
            dataOutputStream.writeUTF(id);
            System.out.print("输入内容:");
            String msg = scanner.nextLine();
            dataOutputStream.writeUTF(msg);
        }
        socket.shutdownOutput();
    }
}