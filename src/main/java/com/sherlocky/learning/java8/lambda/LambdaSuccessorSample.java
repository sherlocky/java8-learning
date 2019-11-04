package com.sherlocky.learning.java8.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 使用Lambda andThen() 简单实现责任链模式
 *
 * @author: zhangcx
 * @date: 2019/7/10 14:12
 */
public class LambdaSuccessorSample {
    public static void main(String[] args) {
        /**
         * 函数式编程思维
         */
        UnaryOperator<String> headerProcessing = (String text) -> {
            return "Someone said: " + text;
        };
        UnaryOperator<String> spellCheckProcessing = text -> text.replace("labda", "lambda");

        Function<String, String> function = headerProcessing.andThen(spellCheckProcessing);

        String src = "Aren't labdas really sexy?!!";
        System.out.println(function.apply(src));

        System.out.println(function.andThen(text -> {
            return StringUtils.join( "【", text, "】");
        }).apply(src));
    }
}
