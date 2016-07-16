package com.sherlocky.learning.java8;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
	public void lambda() {
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));

		String separator = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> {
			System.out.print(e + separator);
		});
		
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
		    int result = e1.compareTo( e2 );
		    System.out.println(result);
		    return result;
		} );
	}
}
