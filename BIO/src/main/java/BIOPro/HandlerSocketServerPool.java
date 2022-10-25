package BIOPro;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/10/11 15:33
 */
public class HandlerSocketServerPool {
    // 1.创建一个线程池的成员便变量
    private final ExecutorService executorService;

    /** 2.创建此类对象时初始化线程池对象
     *
     * @param maxThreadNum 最大线程数
     * @param queueSize 任务队列数量
     */
    public HandlerSocketServerPool(int maxThreadNum,int queueSize) {
        /* corePoolSize 最多长久保留多少个线程
           maximumPoolSize 最大线程数
           keepAliveTime 线程保持多少秒
           unit 单位
           workQueue 任务队列*/
        executorService = new ThreadPoolExecutor(3,maxThreadNum,120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    /**
     * 3.提供一个方法提交任务 给线程池的的任务队列来缓存，等线程池来处理
     * */
    public void execute(Runnable target) {
        executorService.execute(target);
    }
}
