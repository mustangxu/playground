/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.dynamicproxy;

public class ServiceImpl {

	public int hashCode(Object o) {
		return o.hashCode();
	}

	public int add(int a, int b) {
		return a + b;
	}
}
