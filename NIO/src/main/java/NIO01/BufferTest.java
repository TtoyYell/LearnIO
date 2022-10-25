package NIO01;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/20 9:08
 */
public class BufferTest {

    @Test
    public void testBuffer(){
        // 初始化一个容量为10的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("position:"+buffer.position()); // 0
        System.out.println("limit:"+buffer.limit()); // 10
        System.out.println("capacity:"+buffer.capacity()); // 10

        System.out.println("put后=================================");
        buffer.put("aaa".getBytes(StandardCharsets.UTF_8));
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        buffer.flip(); // 把缓冲区的界限limit设为当前位置，并把position设置为0
        System.out.println("flip后=================================");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        System.out.println("get后=================================");
        System.out.println((char) buffer.get(2));
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());
    }

    @Test
    public void testBuffer02(){
        // 初始化一个容量为10的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("position:"+buffer.position()); // 0
        System.out.println("limit:"+buffer.limit()); // 10
        System.out.println("capacity:"+buffer.capacity()); // 10

        System.out.println("put后=================================");
        buffer.put("aaa".getBytes(StandardCharsets.UTF_8));
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        buffer.clear();
        System.out.println("clear后=================================");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        System.out.println("clear后再get=================================");
        System.out.println((char) buffer.get(2));
    }

}
