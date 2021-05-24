/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.lang;

public class OverrideOverload extends SuperOverrideOverload {

	@Override
	public void overload(int number) {
		System.out.println("int overload");
	}

	@Override
	public void overload(Integer number) {
		System.out.println("Integer overload");
	}

	@Override
	public void overload(long number) {
		System.out.println("long overload");
	}

	@Override
	public void overload(Long number) {
		System.out.println("Long overload");
	}

	@Override
	public void overload(Object number) {
		System.out.println("Object overload");
	}

	public static void main(String[] args) {
		new OverrideOverload().overload(100);
	}
}
