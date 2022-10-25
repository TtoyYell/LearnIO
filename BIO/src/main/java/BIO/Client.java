package BIO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 14:55
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        System.out.println("===客户端启动===");
        // 1.创建Socket对象请求服务端的连接
        Socket socket = new Socket("127.0.0.1", 5000);
        // 2.从Socket对象中获取一个字节输出流
        OutputStream os = socket.getOutputStream();
        // 3.把字节输出流包装成一个打印流
        PrintStream ps = new PrintStream(os);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.print("输入:");
            String msg = scanner.nextLine();
            ps.println(msg);
            ps.flush();
        }
        socket.shutdownOutput();
    }
}