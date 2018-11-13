package com.sherlocky.learning.java8.lambda;

/**
 *  lambdas 来实现 Runnable 接口
 * @author zhangcx
 */
public class LambdaSimpleInnerClazz {
    public static void main(String[] args) {
        // java8之前的写法 匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();
        // lambda 写法

        new Thread(() -> System.out.println("Hello world ! Lambda~")).start();

        new Thread(() -> {
            System.out.println("Hello world ! Lambda~{}");
        }).start();



        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world ! run 1");
            }
        };
        Runnable race2 = () -> System.out.println("Hello world ! run 2");
        race1.run();
        race2.run();
    }
}
