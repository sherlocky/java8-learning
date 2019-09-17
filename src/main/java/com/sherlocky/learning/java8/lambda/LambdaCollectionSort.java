package com.sherlocky.learning.java8.lambda;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaCollectionSort {
    private static String[] players = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka", "David Ferrer",
            "Roger Federer", "Andy Murray",
            "Tomas Berdych", "Juan Martin Del Potro",
            "Richard Gasquet", "John Isner"};

    public static void main(String[] args) {
        printArr(players);
        // 使用匿名内部类根据 name 排序 players (正序)
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        printArr(players);
        // 使用 lambda 表达式排序 (倒序)
        Arrays.sort(players, (o1, o2) -> o2.compareTo(o1));
        printArr(players);
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);
        printArr(players);
        Comparator<String> sortBySurname = (String s1, String s2) ->
                (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
        Arrays.sort(players, sortBySurname);
        printArr(players);

        Arrays.sort(players, (String s1, String s2) -> {
            return s1.length() - s2.length();
        });
        printArr(players);
    }

    private static void printArr(Object[] arr) {
        System.out.println(JSON.toJSONString(arr));
    }
}