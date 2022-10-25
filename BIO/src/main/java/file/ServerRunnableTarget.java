package file;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 15:17
 */
public class FileServerRunnableTarget implements Runnable {

    private static Logger logger = Logger.getLogger(FileServerRunnableTarget.class);

    private Socket socket;

    public FileServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream os = null;
        try {
            // 1.从Socket管道中得到一个字节输入流对象
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            // 2.读取文件类型
            String suffix = dataInputStream.readUTF();
            // 3.定义一个字节输出管道
            os = new FileOutputStream("C:\\Users\\Admin\\Desktop\\targetDic\\" + UUID.randomUUID().toString() + suffix);
            // 4.从数据输入流中读取文件数据 写出到字节输出流中
            byte[] buffer = new byte[1024];
            int len;
            while ((len = dataInputStream.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();
            logger.info("服务端接收文件保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
