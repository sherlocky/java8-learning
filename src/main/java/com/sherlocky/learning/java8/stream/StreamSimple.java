package com.sherlocky.learning.java8.stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        // 10.使用Stream初始化Map List
        System.out.println("-------------10.使用Stream初始化Map List-----------");
        // 10.1 Map
        // stream法初始化map
        Map mapByStream = Stream.of("id", "name", "birthday", "sex").collect(
                Collectors.toMap(o -> o, integer -> integer, (o, o2) -> o2, HashMap::new));
        System.out.println(mapByStream);
        /**
         * 双括号初始化法:
         * 第一层花括号：首先对定义了一个继承自HashMap的匿名内部类
         * 第二层花括号：则是一个自定义的对象构造块(称之为 非静态初始化块)
         *
         * 我们得到的 mapByBrackets 实际上是HashMap的子类的引用，但在功能上没有任何改变
         * - 1.相比于常规标准方式进行初始化要简洁许多(但代码可读性相对会差)
         * - 2.效率上来说可能不如标准的集合初始化。原因是使用双大括号会导致内部类文件的产生，而这个过程就会影响代码的执行效率
         * - 3.双大括号初始化方法生成的.class文件要比常规方法多
         * - 4.双大括号初始化方法运行时间要比常规方法长
         * - 5.静态内部类持有所在外部类的引用。如果需要将 Map 返回给到其他地方使用，可能造成内存泄漏。
         * - 6.{{}}法，内部类引用中持有着外部类的引用。所以当串行化这个集合时外部类也会被不知不觉的串行化，如果外部类没有实现serialize接口时，就会报错
         *      <p>解决办法：重新初始化为一个HashMap对象： new HashMap(mapByBrackets);这样就可以正常初始化了</p>
         */
        Map<String, String> mapByBrackets = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("keyN", "valueN");
        }};
        System.out.println(mapByBrackets);

        // 10.2 List
        // stream法
        List listByStream = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        System.out.println(listByStream);
        // 双括号初始化法
        List<String> listByBrackets = new ArrayList<String>() {{
            add("string1");
            add("string2");
            add("stringN");
        }};
        System.out.println(listByBrackets);
        // Arrays.asList 法（除作为常量使用外，并不推荐！）
        /**
         * - 1.Arrays.asList 返回的是 Arrays 的静态内部类ArrayList（静态内部类不持有所在外部类的引用,而非静态内部类会持有外部类的引用）。
         * - 2.这个内部类 ArrayList 继承自 AbstractList，实现了 RandomAccess，内部使用了一个数组来存储元素。
         * - 3.这个内部类 ArrayList 不支持增删元素。
         * - 4.Arrays.asList 的参数如果是基本类型的数组时，需要留意返回值可能和你预期的不同。
         */
        List<String> listByArrayAsList0 = Arrays.asList("a", "b", "c");
        List<String> listByArrayAsList = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        System.out.println(listByArrayAsList);
        // 下面的代码返回值是 List<int[]> 而不是 List<Integer>。
        List<int[]> intArrayList = Arrays.asList(new int[]{1, 2, 3});
        // 如果是JDK9+ 还可以使用：
        // List<String> list = Lists.newArrayList("a", "b", "c");

        //11. flatmap
        /**
         * 在Java 8中，Stream可以容纳不同的数据类型，例如：
         *
         * Stream<String[]>
         * Stream<Set<String>>
         * Stream<List<String>>
         * Stream<List<Object>>
         * 但是，Stream操作（filter，sum，distinct ...）和collectors不支持它，所以我们需要使用flatMap（）进行以下（扁平化）转换：
         *
         * Stream<String[]>		-> flatMap ->	Stream<String>
         * Stream<Set<String>>	-> flatMap ->	Stream<String>
         * Stream<List<String>>	-> flatMap ->	Stream<String>
         * Stream<List<Object>>	-> flatMap ->	Stream<Object>
         */
        System.out.println("-------------11.flatmap-----------");
        // 11.1
        System.out.println("=================Stream<String[]>\t\t-> flatMap ->\tStream<String>=================");
        String[] strsFlat = {"java8", "is", "easy", "to", "use"};
        List<String> distinctStrsByFlatMap = Arrays.stream(strsFlat)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .flatMap(Arrays::stream)  // 扁平化为Stream<String>
                .distinct()
                .collect(Collectors.toList());
        distinctStrsByFlatMap.forEach(s -> System.out.println(s));
        // 11.2 对于原始类型，可以使用flatMapToInt
        System.out.println("=================Stream<int[]>\t\t-> flatMap ->\tIntStream=================");
        int[] intArray = {1, 2, 3, 4, 5, 6};
        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);
        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(Arrays::stream);//(x -> Arrays.stream(x));
        intStream.forEach(x -> System.out.println(x));

        // 12. reduce
        System.out.println("-------------12.reduce-----------");
        // 12.1 reduce操作可以实现从一组值中生成一个值
         /**
          * 如何通过reduce操作对Stream中的数字求和。以0作起点——一个空流
          * Stream的求和结果，每一步都将Stream中的元素累加至accumulator，遍历至Stream中的
          * 最后一个元素时，accumulator的值就是所有元素的和。**/
        /**BinaryOperator reduce包含初始值和BinaryOperator函数接口**/
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        System.out.println("1, 2, 3 累加 = " + count);
        /**展开过程**/
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int anotherCount = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0, 1),
                        2),
                3);
        System.out.println(anotherCount);
        /**等同功能**/
        int acc = 0;
        for (Integer element : Arrays.asList(1, 2, 3)) {
            acc = acc + element;
        }
        System.out.println(acc);
        // 12.2 max和min: 其实包含了reduce功能：reduce(BinaryOperator.minBy(comparator));
        System.out.println("min 方式求最短字符串：");
        List<String> reduceList = Arrays.asList("Bakai", "Violets for Your Furs", "Time Was");
        String shortestStr = reduceList.stream()
                .min(Comparator.comparing(s -> s.length()))
                .get();
        System.out.println(shortestStr);
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
