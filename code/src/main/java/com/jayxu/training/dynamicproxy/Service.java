/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.dynamicproxy;

/**
 * @author ijay
 * 
 */
public interface Service {
    int hashCode(@NotNull Object o);

    int add(int a, int b);
}
