package com.sherlocky.learning.java8.optional;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * 优雅的Optional空指针处理
 * 
 * @author Sherlocky
 * @date 2016年9月5日
 */
/**
 * Optional除了本类中提到的方法，还有orElseGet、orElseThrow等根据更多需要提供的方法。
 * 		orElseGet会因为出现null值抛出空指针异常
 * 		orElseThrow会在出现null时，抛出一个使用者自定义的异常。可以查看API文档来了解所有方法的细节。
 */
public class OptionalNullPointHandle {
	
	public static void main(String[] args) {
		/**
		 * 使用Optional作为String的外壳对String进行截断处理。
		 * 当在处理过程中遇到null值时，就不再继续处理。我们
		 * 可以发现第二个Optional中出现s->null之后，后续的ifPresent不再执行。
		 */
		final String text = "Hallo world!";
		Optional.ofNullable(text)// 显示创建一个Optional壳
				.map(OptionalNullPointHandle::print).map(OptionalNullPointHandle::print).ifPresent(System.out::println);
		
		System.out.println("###############");
		
		Optional.ofNullable(text).map(s -> {
			System.out.println(s);
			return s.substring(6);
		}).map(s -> null)// 返回 null
				.ifPresent(System.out::println);
		
		System.out.println("###############");
		/**
		 * 动态的处理一个字符串，如果在任何时候发现值为null，则使用orElse返回预设默认的"NaN"。
		 */
		System.out.println(lowerCase(text));//方法一
		lowerCase(null, System.out::println);//方法二
	}

	private static String lowerCase(String str) {
		return Optional.ofNullable(str).map(s -> s.toLowerCase()).map(s->s.replace("world", "java")).orElse("NaN");
	}

	private static void lowerCase(String str, Consumer<String> consumer) {
		consumer.accept(lowerCase(str));
	}
	
	// 打印并截取str[5]之后的字符串
	private static String print(String str) {
		System.out.println(str);
		return str.substring(6);
	}
}
