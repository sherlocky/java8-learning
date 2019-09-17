/**
 *
 */
package com.sherlocky.learning.java8.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * lambda表达式 EffectivelyFinal
 * 		参考：http://www.tuicool.com/articles/iuUVFvN
 * @author zhangcx
 * @date 2017-06-23
 */
public class LambdaInnerClazzEffectivelyFinalSample {

    public static void main(String[] args) {
        testIntStreamRangeWithInnerClassLambda();
    }

    public static void testIntStreamRangeWithInnerClassLambda() {
        /**
		 * 【EffectivelyFinal】：
         * 我们有一个匿名的内部类实现了 Runnable 接口。我们想在 run 方法中访问索引变量 i ，但编译器不允许这么做。
         * 作为此限制的解决办法，我们可以创建一个局部临时变量，比如 temp ，它是索引变量的一个副本。每次新的迭代都会创建变量 temp 。
         * 在 Java 8 以前，我们需要将该变量标记为 final 。从 Java 8 开始，可以将它视为实际的最终结果，因为我们不会再更改它。
         * 无论如何，由于事实上索引变量是一个在迭代中改变的变量， for 循环中就会出现这个额外变量。
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            int temp = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 如果不注释掉下一行代码，会报编译错误
                    //System.out.println("Running task " + i);
                    // 内部类中使用局部变量必须是final 或 effectively final (java8新增)的
                    System.out.println("Running task " + temp);
                }
            });
        }
        executorService.shutdown();

        /**
         * 尝试使用 range 函数解决
         */
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);

        IntStream.range(0, 5).forEach(i -> executorService2.submit(new Runnable() {
            @Override
            public void run() {
                // 在内部类中使用拉姆达参数
                System.out.println("Running task " + i);
            }
        }));

        executorService2.shutdown();

        /**
         * 将内部类也替换为拉姆达表达式
         */
        ExecutorService executorService3 = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5).forEach(i -> executorService3.submit(() -> System.out.println("Running task " + i)));
    }
}
