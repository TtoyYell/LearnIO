import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 15:17
 */
public class ServerRunnableTarget implements Runnable{

    private Socket socket;

    private Logger logger = Logger.getLogger(ServerRunnableTarget.class);

    public ServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String ownId = null;
        String id = null;
        String msg = null;
        try {
            // 1.从Socket管道中得到一个字节输入流对象
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            // 2.读取
            ownId = dataInputStream.readUTF();
            // 3.把socket和id存入map中
            ChartServer.socketMap.put(ownId,socket);
            while(true){
                id = dataInputStream.readUTF();
                msg = dataInputStream.readUTF();
                Socket toSocket = ChartServer.socketMap.get(id);
                if (toSocket != null) {
                    DataOutputStream dataOutputStream = new DataOutputStream(toSocket.getOutputStream());
                    logger.info("来自用户"+ownId+"的私聊信息 "+msg+" ,发送至用户"+id);
                    dataOutputStream.writeUTF("服务器消息:用户"+ownId+"发送信息 "+msg+" 给用户"+id);
                    dataOutputStream.flush();
                } else {
                    DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
                    logger.info("用户"+id+"不在线");
                    dataOutputStream.writeUTF("用户"+id+"不在线");
                    dataOutputStream.flush();
                }
            }
        } catch (Exception e) {
            logger.info(ownId+"断开连接");
        }
    }
}
