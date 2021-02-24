/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ijay
 *
 */
public class DynamicProxyTest {
	public static void main(String[] args) {
		InvocationHandler handler = (proxy, method, arg) -> {
			var impl = new ServiceImpl();
			var annotations = method.getParameterAnnotations();

			for (var i = 0; i < annotations.length; i++) {
				if (Arrays.stream(annotations[i]).anyMatch(it -> it.annotationType().equals(NotNull.class))
						&& arg[i] == null) {
					throw new IllegalArgumentException("NULL parameter not allowed");
				}
			}

			return switch (method.getName()) {
				case "toString" -> ToStringBuilder.reflectionToString(proxy);
				case "hashCode" -> 12 + 12;
				case "add" -> impl.add((Integer) arg[0], (Integer) arg[1]);
				default -> method.invoke(proxy, arg);
			};
		};

		var service = (Service) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
				new Class<?>[] { Service.class }, handler);

		System.out.println(service);
		System.out.println("" + service.add(12, 23));
		System.out.println("".hashCode() + ", " + service.hashCode(""));
		System.out.println("" + service.hashCode(null));
	}
}
