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
        System.out.println(Thread.currentThread());

        var tree = new BinaryTree<Integer>();
        var max = 50;
        var r = new Random();

        for (var i = 0; i < max; i++) {
            tree.add(r.nextInt(max) * r.nextInt(max));
        }
        tree.add(-1);

        System.out.println(tree);
        System.out.println("PRE_ORDER: " + tree.toString(", "));

        tree.setOrder(Order.IN_ORDER);
        System.out.println("IN_ORDER: " + tree.toString(", "));
        tree.setOrder(Order.LEVEL_ORDER);
        System.out.println("LEVEL_ORDER: " + tree.toString(", "));
        tree.setOrder(Order.PRE_ORDER);

        System.out.println(tree.stream().map(Objects::toString)
            .collect(Collectors.joining(", ")));

        assertEquals(max + 1, tree.size(), "size()");
        assertTrue(tree.contains(-1), "contains()");
        assertFalse(tree.contains(-2), "!contains()");
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
                .collect(Collectors.joining(", ")));
    }

    @Test
    void testRemove() {
        System.out.println(Thread.currentThread());

        var tree = new BinaryTree<Integer>();
        tree.addAll(1, 2, 3, 4, 5);
        System.out.println(tree.toString(", "));

        assertTrue(tree.remove(2));
        assertEquals(4, tree.size(), "size()");
        System.out.println(tree.toString(", "));

        tree.clear();
        tree.addAll(1, 2);
        assertFalse(tree.remove(3));
        System.out.println(tree);
        tree.remove(1);
        assertEquals(1, tree.size(), "size()");
        assertTrue(tree.getRoot().isRoot(), "isRoot()");
        System.out.println(tree.toString(", "));
        System.out.println(tree);

        tree.clear();
        tree.addAll(2, 7, 1, 9, 6, 5, 8);
        System.out.println(tree.toString(", "));
        System.out.println(tree);
        tree.remove(7);
        assertEquals(6, tree.size(), "size()");
        System.out.println(tree.toString(", "));
        System.out.println(tree);
    }

    @Test
    void testRetainAll() {
        System.out.println(Thread.currentThread());

        var tree = new BinaryTree<Integer>();
        tree.addAll(1, 2, 3, 4, 5);
        System.out.println(tree.toString(", "));

        tree.retainAll(Arrays.asList(5, 2, 11));
        assertEquals(2, tree.size(), "size()");
        System.out.println(tree.toString(", "));
        System.out.println(tree);
    }
}
