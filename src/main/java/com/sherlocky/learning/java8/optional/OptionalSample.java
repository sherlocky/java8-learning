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
public class OptionalSample {
    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> o1 = Optional.ofNullable(value1);
        // Optional<Integer> o11 = Optional.of(value1); // 报 NPE
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> o2 = Optional.of(value2);
        // 存在即返回, 无则提供默认值
        System.out.println("###### 存在即返回, 无则提供默认值");
        System.out.println(OptionalSample.sum(o1, o2));

        // orElseGet：存在即返回, 无则由函数来产生
        System.out.println("###### orElseGet：存在即返回, 无则由函数来产生");
        System.out.println(getValue(null));
        System.out.println(getValue(123));

        // ifPresent：存在才对它做点什么
        System.out.println("###### ifPresent：存在才对它做点什么");
        doSomething(null);
        doSomething(12345);

        /**
         * 还与map结合使用，map 是可能无限级联的
         * {@link OptionalNullPointHandle#lowerCase(String)}
         */
        /**
         * flatMap() :
         * 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。
         *
         * flatMap与map（Funtion）方法类似，区别在于：
         * 1.map方法的mapping函数返回值可以是任何类型T
         * 2.flatMap中的mapper返回值必须是Optional，调用结束时，flatMap不会对结果用Optional封装。
         */
        /**
         * filter() :如果有值并且满足条件返回包含该值的Optional，否则返回空Optional。
         */
        /**
         * orElseThrow() 在有值时直接返回, 无值时抛出想要的异常.
         */
        System.out.println("###### orElseThrow() 在有值时直接返回, 无值时抛出想要的异常");
        o1.orElseThrow(() -> new RuntimeException("$$$ 自己声明的异常：o1 为空"));
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

    private static int getValue(Integer int1) {
        Optional<Integer> o1 = Optional.ofNullable(int1);
        return o1.orElseGet(() ->
                defaultInt()
        );
        // 不要使用 return o1.isPresent() ? o1 : defaultInt();
    }

    private static void doSomething(Integer int1) {
        Optional<Integer> o1 = Optional.ofNullable(int1);
        o1.ifPresent(System.out::println);
    }

    private static int defaultInt() {
        return -1;
    }
}
