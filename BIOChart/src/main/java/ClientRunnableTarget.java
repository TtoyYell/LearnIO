import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 15:17
 */
public class ClientRunnableTarget implements Runnable{

    private Socket socket;

    private Logger logger = Logger.getLogger(ClientRunnableTarget.class);

    public ClientRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1.从Socket管道中得到一个字节输入流对象
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String msg = null;
            while(true){
                msg = dataInputStream.readUTF();
                logger.info(msg);
            }
        } catch (Exception e) {
            logger.info("服务器崩溃");
        }
    }
}
