package com.javen.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTask {

    @Async
    public void asyncTaskOne() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时:" + (end - start) + "ms");
    }

    @Async
    public void asyncTaskTwo() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("任务2耗时:" + (end - start) + "ms");
    }

    @Async
    public void asyncTaskThree() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.println("任务3耗时:" + (end - start) + "ms");
    }

    @Async
    public Future<String> asyncTaskFour() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(4000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("任务4耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
        return new AsyncResult<String>("asyncTaskFour执行完毕");
    }
}