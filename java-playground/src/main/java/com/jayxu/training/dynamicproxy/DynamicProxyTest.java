/**
 * Authored by jayxu @2021
 */
package com.jayxu.training.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DynamicProxyTest {
    public static void main(String[] args) {
        InvocationHandler handler = (proxy, method, arg) -> {
            var impl = new ServiceImpl();
            var annotations = method.getParameterAnnotations();

            for (var i = 0; i < annotations.length; i++) {
                if (Arrays.stream(annotations[i])
                    .anyMatch(it -> it.annotationType().equals(NotNull.class))
                    && arg[i] == null) {
                    throw new IllegalArgumentException(
                        "NULL parameter not allowed");
                }
            }

            switch (method.getName()) {
                case "toString":
                    return ToStringBuilder.reflectionToString(proxy);
                case "hashCode":
                    return 12 + 12;
                case "add":
                    return impl.add((Integer) arg[0], (Integer) arg[1]);
                default:
                    return method.invoke(proxy, arg);
            }
        };

        var service = (Service) Proxy.newProxyInstance(
            DynamicProxyTest.class.getClassLoader(),
            new Class<?>[] { Service.class }, handler);

        System.out.println(service);
        System.out.println("" + service.add(12, 23));
        System.out.println("".hashCode() + ", " + service.hashCode(""));
        System.out.println("" + service.hashCode(null));
    }
}
