/**
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 */
package com.jayxu.training.dynamicproxy;

/**
 * @author ijay
 * 
 */
public class ServiceImpl {

    public int hashCode(Object o) {
        return o.hashCode();
    }

    public int add(int a, int b) {
        return a + b;
    }
}
