package com.sherlocky.learning.java8.optional;

import java.util.Optional;

/**
 * Optional 类是一个可以为null的容器对象。
 *      如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 *
 * 它可以保存类型T的值，或者仅仅保存null。【可以很好的解决空指针异常 NPE】
 * @author: zhangcx
 * @date: 2018/11/15 14:24
 */
public class OptionalSimple {
    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> o1 = Optional.ofNullable(value1);
        // Optional<Integer> o11 = Optional.of(value1); // 报 NPE
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> o2 = Optional.of(value2);

        System.out.println(OptionalSimple.sum(o1, o2));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));
        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}