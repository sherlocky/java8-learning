package com.sherlocky.learning.java8.lambda.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * 【【本质上还是 lambda 表达式】】
 * 在Java8中，我们可以直接通过【方法引用】来简写lambda表达式中已经存在的方法，这种特性就叫做方法引用(Method Reference)
 * 可以使语言的构造更紧凑简洁，减少冗余代码。
 *
 * 是用来直接访问类或者实例的已经存在的方法或者构造方法。方法引用提供了一种引用而不执行方法的方式，它需要由兼容的函数式接口构成的目标类型上下文。计算时，方法引用会创建函数式接口的一个实例。
 *
 * - 1.当Lambda表达式中只是执行一个方法调用时，不用Lambda表达式，直接通过方法引用的形式可读性更高一些。
 * - 2.方法引用其实是一种更简洁易懂的 Lambda 表达式，操作符是双冒号【::】。
 *
 * @author zhangcx
 */
public class MethodreferenceSample {
    public static void main(String[] args) {
        /**
         * 在Java 8中，我们会使用Lambda表达式创建匿名方法，但是有时候，我们的Lambda表达式可能仅仅调用一个已存在的方法，
         * 而不做任何其它事，对于这种情况：
         * 通过一个方法名字来引用这个已存在的方法会更加清晰，Java 8的【方法引用】允许我们这样做。
         */
        // 比如：
        String[] stringsArray = {"Google", "Runoob", "Iflytek"};
        Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));
        // 可以写成
        Arrays.sort(stringsArray, String::compareToIgnoreCase);

        // 再比如 遍历 List 可以这么写
        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);


        /************************************【归纳起来有4种】*****************************************/

        // 1.构造器引用：它的语法是【Class::new】，或者更一般的【Class< T >::new】实例如下：
        final Car car = Car.create(Car::new);
        // 此处 Car::new 相当于
        Supplier<Car> supplier = () -> new Car();
        final Car car2 = Car.create(supplier);
        final List<Car> cars = Arrays.asList(car, car2);
        /**
         * 1.1数组构造方法引用, 组成语法格式：TypeName[]::new
         * int[]::new 是一个含有一个参数的构造器引用，这个参数就是数组的长度。等价于lambda表达式  x -> new int[x]
         */
        /**
         * IntFunction<R>
         * 接受一个int类型输入参数，返回一个结果
         */
        // 假想存在一个接收int参数的数组构造方法
        IntFunction<int[]> arrayMaker = int[]::new;
        int[] array = arrayMaker.apply(10); // 创建数组 int[10]

        // 2.静态方法引用：它的语法是【Class::static_method【，实例如下：
        cars.forEach(Car::collide);

        // 3.特定类的任意对象的方法引用：它的语法是【Class::method】实例如下：
        cars.forEach(Car::repair);

        // 4.特定对象(实例)的方法引用：它的语法是【Instance::method】实例如下：
        final Car police = Car.create( Car::new );
        cars.forEach(police::follow);
        // 4.1 目标方法包含泛型的
        /**
         * 例如 MyArrayOps::<Integer>countMatching
              参数传递发生在::的后面。这种语法可以推广。当把泛型方法指定为方法引用时，类型参数出现在::之后、方法名之前。但是，需要指出的是，在这种情况(和其它许多情况)下，并非必须显示指定类型参数，因为类型参数会被自动推断得出。
         */
        /**
         * 4.2 需要指定泛型类的： 对于指定泛型类的情况，类型参数位于类名的后面::的前面
         */
        /**
         * 4.3 超类上的实例方法引用： super::methodName
         *      还可以捕获 this指针，this::equals
         */
    }
}

class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    /**
     * java.util.function 里提供的用来支持 Java 函数式编程的函数式接口：
     * 	Supplier<T>
     * 无参数，返回一个结果。
     */
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}