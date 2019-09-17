package com.sherlocky.learning.java8.stream;

import java.util.stream.IntStream;

/**
 * IntStream 常见用法
 * @author: zhangcx
 * @date: 2019/9/17 16:33
 */
public class IntStreamSample {
    public static void main(String[] args) {
        testIntStreamRange();
        testIntStreamRangeClosed();
        testIntStreamRangeIterate();
    }

    public static void testIntStreamRange() {
        System.out.print("for循环方式：Get set...");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "...");
        }
        System.out.println("");
        System.out.print("IntStream.range方式：Get set...");
        // 下标不包含4
        IntStream.range(1, 4).forEach(i -> System.out.print(i + "..."));
    }

    public static void testIntStreamRangeClosed() {
        System.out.print("IntStream.rangeClosed方式：Get set...");
        // 下标包含4
        IntStream.rangeClosed(1, 4).forEach(i -> System.out.print(i + "..."));
    }
    /**
     * 使用 iterate 的逆向迭代
     */
    public static void testIntStreamRangeIterate() {
        /**
         * range 或 rangeClosed 中的第一个参数不能大于第二个参数，所以我们无法使用这两种方法来执行逆向迭代。
         * 但可以使用 iterate 方法：
         */

        System.out.println("IntStream.iterate(..).sum()");
        IntStream.iterate(7, e -> e - 2).limit(7).forEach(i -> System.out.print(i + "..."));
        System.out.println(IntStream.iterate(7, e -> e - 1).limit(7).sum());
        /**
         * 本例中有一个 陷阱 。不同于 range 和 rangeClosed ，没有参数来告诉 iterate 方法何时停止迭代。
         * 如果我们没有使用limit()限制该值，迭代会一直进行下去
         */

        /*********************Java9新增 takeWhile 和 stopWhile *********************/
        // 有条件的迭代
        // System.out.println(IntStream.iterate(1, e -> e + 3).takeWhile(i -> i <= 100).sum());
    }
}
