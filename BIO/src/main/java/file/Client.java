package file;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 14:55
 */
public class FileClient {
    public static void main(String[] args) throws IOException {
        System.out.println("===客户端启动===");
        // 1.创建Socket对象请求服务端的连接
        Socket socket = new Socket("127.0.0.1", 5000);
        // 2.把字节输出流包装成一个数据输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // 3.先把文件的后缀发送给服务器
        dos.writeUTF(".sql");
        // 4.把文件数据发送给服务端
        InputStream is = new FileInputStream("C:\\Users\\Admin\\Desktop\\SPELL.sql");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) > 0) {
            dos.write(buffer,0,len);
        }
        dos.flush();
        socket.shutdownOutput();
    }
}