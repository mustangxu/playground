/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.jayxu.playground.algorithm.BinaryTree.Order;

/**
 * @author xujiajing
 */
class BinaryTreeTest {

    @Test
    void test() {
        var tree = new BinaryTree<Integer>();
        var max = 50;
        var r = new Random();

        for (var i = 0; i < max; i++) {
            var num = r.nextInt(max) * r.nextInt(max);
            tree.add(num);
        }
        tree.add(-1);

        System.out.println(tree);
        System.out.println("PRE_ORDER: " + tree.toString(", "));

        tree.setOrder(Order.IN_ORDER);
        System.out.println("IN_ORDER: " + tree.toString(", "));
        tree.setOrder(Order.PRE_ORDER);

        System.out.println(tree.stream().map(Objects::toString)
            .collect(Collectors.joining(", ")));

        assertEquals(max + 1, tree.size(), "size()");
        assertTrue(tree.contains(-1), "contains()");
        assertFalse(tree.containsAll(List.of(-1, -2)), "containsAll()");

        var postTree = new BinaryTree<Integer>(Order.POST_ORDER);
        postTree.addAll(tree);
        System.out.println("POST_ORDER: " + postTree.toString(", "));

        // toArray
        var array = new Integer[tree.size()];
        var ret = tree.toArray(array);
        assertSame(array, ret);
        System.out.println(Arrays.toString(ret));

        array = new Integer[tree.size() - 1];
        ret = tree.toArray(array);
        assertNotSame(array, ret);
        System.out.println(Arrays.toString(ret));

        // stream
        System.out.println(
            tree.parallelStream().filter(i -> i % 2 == 0).map(Objects::toString)
                .peek(
                    i -> System.out.println(Thread.currentThread() + ": " + i))
                .collect(Collectors.joining(", ")));

        System.out.println(tree.nodeStream()
            .map(n -> n.getValue() + "[lv. " + n.getLevel() + "]")
            .collect(Collectors.joining(", ")));
    }
}
