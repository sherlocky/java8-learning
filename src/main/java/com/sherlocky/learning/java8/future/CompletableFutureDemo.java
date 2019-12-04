package com.sherlocky.learning.java8.future;

import java.util.concurrent.CompletableFuture;

/**
 * 为了让程序更加高效，让CPU最大效率的工作，我们会采用异步编程。
 *
 * 首先想到的是开启一个新的线程去做某项工作。再进一步，为了让新线程可以返回一个值，
 * 告诉主线程事情做完了，于是乎Future粉墨登场。
 *
 * 然而Future提供的方式是主线程主动问询新线程，要是有个回调函数就爽了。
 * 所以，为了满足Future的某些遗憾，强大的CompletableFuture随着Java8一起来了。
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        //没有返回值的 runAsync 异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回，update mysql ok");
        });
        completableFuture.get();

        //有返回值的  供给型参数接口
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
            System.out.println(Thread.currentThread().getName() + "线程休息2s~");
            // int i = 10 / 0;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        System.out.println(completableFuture2.whenComplete((t, u) -> { //编译完成，正常结束输出
            System.out.println("两秒到了~~~");
            System.out.println("===t:" + t);  //正常结果
            System.out.println("===u:" + u);  //信息
        }).exceptionally(e -> {  //结果异常，非正常结束
            System.out.println("=======exception:" + e.getMessage());
            return -1;
        }).get());

    }
}