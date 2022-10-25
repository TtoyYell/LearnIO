package BIO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 15:17
 */
public class ServerThreadReader extends Thread{

    private Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 3.从Socket管道中得到一个字节输入流对象
        InputStream is = null;
        try {
            is = socket.getInputStream();
            // 4.把字节输入流包装成一个缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("服务端接收到:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
