package com.sherlocky.learning.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
 *
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 *
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 *
 * Stream（流）是一个来自数据源的元素队列并支持聚合操作
 * - 1.元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 * - 2.数据源 流的来源。 可以是【【集合，数组，I/O channel， 产生器generator】】等。
 * - 3.聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。和以前的Collection操作不同， Stream操作还有两个基础的特征：
 * - 4.Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 * 5. 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。
 *
 * @author: zhangcx
 * @date: 2018/11/13 20:26
 */
public class StreamSimple {
    public static void main(String[] args) {
        // 1.在 Java 8 中, 集合接口有两个方法来生成流：
        System.out.println("-------------1-----------");
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 1.1 为集合创建串行流，对于 Collectors 可以参考【8】
        strings.stream();
        System.out.println(strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList()));
        // 1.2 为集合创建并行流 (具体参考下面【7】)
        strings.parallelStream();
        strings.parallelStream().filter(str -> !str.isEmpty()).forEach(System.out::println);

        // 2. forEach
        System.out.println("-------------2-----------");
        Random r = new Random();
        r.ints(6).filter(i -> i > 0).forEach(System.out::println);
        // 等价于
        r.ints().limit(6).filter(i -> i > 0).forEach(System.out::println);

        // 3.map 方法用于映射每个元素到对应的结果
        System.out.println("-------------3-----------");
        // 3.1 使用 map 输出了元素对应的平方数：
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).collect(Collectors.toList());
        // 去重
        List<Integer> squaresList2 = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        // 4.filter 方法用于通过设置的条件过滤出元素
        System.out.println("-------------4-----------");
        // 4.1 使用 filter 方法过滤出空字符串
        List<String> strs = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long emptyCount = strs.stream().filter(s -> s.isEmpty()).count();
        System.out.println(emptyCount);

        // 5.limit 方法用于获取指定数量的流
        System.out.println("-------------5-----------");
        // 比如 【2.forEach】里的用法
        System.out.println(numbers.stream().limit(2).collect(Collectors.toList()));

        // 6.sorted 方法用于对流进行排序
        System.out.println("-------------6-----------");
        // 6.1 以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        Random random = new Random();
        random.ints().limit(3).sorted().forEach(System.out::println);

        // 7. 并行（parallel）程序 parallelStream 是流并行处理程序的代替方法。
        /**
         * 具有平行处理能力，处理的过程会分而治之，也就是将一个大任务切分成多个小任务
         * forEach的顺序不一定是原始顺序，可以调用forEachOrdered()来保持原始顺序
         */
        System.out.println("-------------7-----------");
        // 7.1 使用 parallelStream 来输出空字符串的数量
        List<String> ss = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("-------------forEach-----------");
        ss.parallelStream().filter(s -> !s.isEmpty()).forEach(System.out::println);
        System.out.println("-------------forEachOrdered-----------");
        ss.parallelStream().filter(s -> !s.isEmpty()).forEachOrdered(System.out::println);

        // Collectors 类实现了很多归约操作
        System.out.println("-------------8-----------");
        // 8.1 将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> stringsX = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = stringsX.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining("', '"));
        System.out.println("合并字符串: " + mergedString);

        // 9.统计 一些产生统计结果的收集器也非常有用
        System.out.println("-------------9-----------");
        // 9.1 它们主要用于int、double、long等基本类型上，可以用来产生类似如下的统计结果
        List<Integer> numbersX = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbersX.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
    /**
    +--------------------+       +------+   +------+   +---+   +-------+
    | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
    +--------------------+       +------+   +------+   +---+   +-------+

    // 以上的流程转换为 Java 代码为：
    List<Integer> transactionsIds =
            widgets.stream()
                    .filter(b -> b.getColor() == RED)
                    .sorted((x,y) -> x.getWeight() - y.getWeight())
                    .mapToInt(Widget::getWeight)
                    .sum();
     */
}
