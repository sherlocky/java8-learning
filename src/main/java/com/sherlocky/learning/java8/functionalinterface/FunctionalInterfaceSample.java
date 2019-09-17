package com.sherlocky.learning.java8.functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 *
 * #可以使用Lambda表达式来表示该接口的一个实现(注：JAVA 8 之前一般是用匿名类实现的)
 * #函数式接口可以对现有的函数友好地支持 lambda
 *
 * Java 8为函数式接口引入了一个新注解 @FunctionalInterface，主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错(加不加 @FunctionalInterface 对于接口是不是函数式接口没有影响，该注解只是提醒编译器去检查该接口是否仅包含一个抽象方法)
 * - 1.函数式接口里允许定义默认方法
 *      因为默认方法不是抽象方法，其有一个默认实现
 * - 2.函数式接口里允许定义静态方法
 *      因为静态方法不能是抽象方法，是一个已经实现了的方法
 * - 3.函数式接口里允许定义 java.lang.Object 里的 public 方法
 *      这些方法对于函数式接口来说，不被当成是抽象方法（虽然它们是抽象方法）；因为任何一个函数式接口的实现，默认都继承了 Object 类，包含了来自 java.lang.Object 里对这些抽象方法的实现
 */
public class FunctionalInterfaceSample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("输出所有数据:");
        // 传递参数 n
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true
        eval(list, n -> true); // Predicate<Integer> predicate = n -> true

        System.out.println("输出所有偶数:");
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true
        eval(list, (n) -> n % 2 == 0); // Predicate<Integer> predicate = n -> n % 2 == 0

        System.out.println("输出大于 3 的所有数字:");
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true
        eval(list, n -> n > 3);

        // 还可以不用定义 eval 函数,这样使用：
        // filter 接收一个 Predicate<T> 函数接口， forEach 接收一个 Consumer<T> 函数接口
        list.stream().filter(n -> n > 3).forEach(System.out::println);
    }

    /**
     * java.util.function 里提供的用来支持 Java 函数式编程的函数式接口：
     * Predicate<T>
     * 接受一个输入参数，返回一个布尔值结果。
     */
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    // eval 函数可以写成这样
    private static void eval_1(List<Integer> list, Predicate<Integer> predicate) {
        list.stream().filter(predicate).forEach(System.out::println);
    }
}
