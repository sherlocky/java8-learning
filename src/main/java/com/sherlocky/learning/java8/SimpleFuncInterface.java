package com.sherlocky.learning.java8;

@FunctionalInterface
public interface SimpleFuncInterface {
    public void doWork(Object obj);
    
	default void say(String name) {
		System.out.println("hello " + name);
	}
}
class FunctionalInterfaceWithDefaultMethod {
	public static void main(String[] args) {
		SimpleFuncInterface i = (o) -> {
			System.out.println("FunctionalInterface invoked");
		};
		i.doWork(null);
		i.say("default method");
	}
}