/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.innerclass;

public class InnerClass {
	public void repeat(Runnable runnable, int times) {
		for (var i = 0; i < times; i++) {
			runnable.run();
			runnable.toString();
		}
	}

	public static void main(String[] args) {
		InnerClass c = new InnerClass() {

			@Override
			public void repeat(Runnable runnable, int times) {
				super.repeat(runnable, times);
			}

		};

		Runnable runnable = () -> System.out.println("hello world");
		new InnerClass().repeat(runnable, 10);
	}
}
