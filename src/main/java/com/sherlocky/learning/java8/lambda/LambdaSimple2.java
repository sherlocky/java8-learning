package com.sherlocky.learning.java8.lambda;

/**
 * - 1.lambda 引用的外层局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有 final 的语义），否则会编译错误。
 * - 2.在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量
 * @author zhangcx
 */
public class LambdaSimple2 {

    private static String HELLO = "Hello: ";

    public static void main(String[] args) {
        GreetingService s1 = message -> System.out.println(HELLO + message);

        s1.sayMessage("Java");

        int num = 1;

        Converter<Integer, String> s = (param) -> {
            System.out.println(String.valueOf(param + num));
        };
        // 必须不可被后面的代码修改
        // num = 5;
        s.convert(2);
        // 不允许声明一个与局部变量同名的参数或者局部变量
        // Converter<Integer, String> s2 = (num) -> System.out.println(String.valueOf(num));
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
