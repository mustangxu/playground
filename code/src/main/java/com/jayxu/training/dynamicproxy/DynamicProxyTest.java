/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.dynamicproxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ijay
 * 
 */
public class DynamicProxyTest {
    public static void main(String[] args) throws IllegalArgumentException,
            InstantiationException, IllegalAccessException, SecurityException,
            InvocationTargetException, NoSuchMethodException {
        InvocationHandler handler = new InvocationHandler() {
            private ServiceImpl impl = new ServiceImpl();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                Annotation[][] annotations = method.getParameterAnnotations();
                for (int i = 0; i < annotations.length; i++) {
                    for (int j = 0; j < annotations[i].length; j++) {
                        if (annotations[i][j].annotationType().equals(
                            NotNull.class)
                            && args[i] == null) {

                            throw new IllegalArgumentException(
                                "NULL parameter not allowed");
                        }
                    }
                }

                if ("hashCode".equals(method.getName())) {
                    return 12 + 12;
                } else if ("add".equals(method.getName())) {
                    return this.impl.add((Integer) args[0], (Integer) args[1]);
                } else {
                    return method.invoke(proxy, args);
                }
            }
        };

        Class<?> proxy = Proxy.getProxyClass(DynamicProxyTest.class
            .getClassLoader(), Service.class);
        System.out.println(proxy);
        Constructor<?> constructor = proxy
            .getConstructor(new Class[] { InvocationHandler.class });
        Service service = (Service) constructor
            .newInstance(new Object[] { handler });

        System.out.println("" + service.add(12, 23));
        System.out.println("".hashCode() + ", " + service.hashCode(""));
        System.out.println("" + service.hashCode(null));
    }
}
