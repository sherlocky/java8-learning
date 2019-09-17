package com.sherlocky.learning.java8.lambda;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * lambda foreach 性能测试
 *
 * @author: zhangcx
 * @date: 2019/7/19 16:30
 */
/** Warmup 用来配置预热的内容，代表：每个方法执行前都进行5次预热执行，每隔1秒进行一次预热操作 */
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
/** Measurement 用来控制实际执行的内容（配置的选项同Warmup),代表：预热执行结束之后进行5次实际测量执行，每隔1秒进行一次实际执行 */
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class LambdaForeachBenchmark {

    /**
     * JMH为我们提供了状态的支持。该注解只能用来标注在类上，因为类作为一个属性的载体。
     *
     * State的主要状态值：
     * Scope.Benchmark 该状态的意思是会在所有的Benchmark的工作线程中共享变量内容。
     * Scope.Group 同一个Group的线程可以享有同样的变量
     * Scope.Thread 每隔线程都享有一份变量的副本，线程之间对于变量的修改不会相互影响。
     */
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x;

        /**
         * Setup： 必须标示在@State注解的类内部，表示初始化操作
         * TearDown： 必须标示在@State注解的类内部，表示销毁操作
         *
         * 初始化和销毁的动作都只会执行一次。
         */
        @Setup
        public void prepare() {
            x = Math.PI;
        }
        @TearDown
        public void check() {
            assert x > Math.PI : "Nothing changed?";
        }

        /**
         * Param 可以用来测试不同的参数的不同结果
         */
        @Param({"1", "2", "3"})
        int testNum;
    }

    /**
     * 注解@Threads 可以配置在方法或者类上，代表执行测试的线程数量。
     */

    static class Demo {
        Integer id;
        String name;

        public Demo(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static List<Demo> demoList;

    static {
        demoList = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            demoList.add(new Demo(i, String.format("test_%s", i)));
        }
    }

    /** Benchmark 标签是用来标记测试方法的，只有被这个注解标记的方法才会参与基准测试(必须是 public 的) */
    @Benchmark
    /**
     * BenchmarkMode 主要是表示测量的纬度(可以指定多个)
     *
     * Mode.Throughput 吞吐量纬度
     * Mode.AverageTime 平均时间
     * Mode.SampleTime 抽样检测
     * Mode.SingleShotTime 检测一次调用
     * Mode.All 运用所有的检测模式
     */
    @BenchmarkMode(Mode.AverageTime)
    /** OutputTimeUnit代表测量的单位（离目标更近的注解更容易生效）*/
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testCommonForeach() {
        for (Demo demo : demoList) {
            //
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testLambdaStream() {
        demoList.stream().forEach(demo -> {
            //
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testLambdaParallelStream() {
        demoList.parallelStream().forEach(demo -> {
            //
        });
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LambdaForeachBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
/** 执行结果：
 # Run complete. Total time: 00:00:33

 Benchmark                                        Mode  Cnt    Score     Error  Units
 LambdaForeachBenchmark.testCommonForeach         avgt    5  765.037 ± 135.488  us/op
 LambdaForeachBenchmark.testLambdaParallelStream  avgt    5  481.424 ±  77.324  us/op
 LambdaForeachBenchmark.testLambdaStream          avgt    5  591.199 ± 100.151  us/op

 ---------------------------------
 可以明显看到 lambda stream() 的 parallelStream 遍历速度相较 普通的for循环，还是很明显的。
*/