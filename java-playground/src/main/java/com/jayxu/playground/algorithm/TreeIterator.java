/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * @author jayxu
 */
public class TreeIterator<V> implements Iterator<V> {
    private final Function<TreeNode<?>, V> mapper;
    private final LinkedList<TreeNode<?>> list;

    public TreeIterator(TreeNode<?> from, Function<TreeNode<?>, V> mapper, Order order) {
        this.list = new LinkedList<>();
        this.mapper = mapper;

        if (from != null) {
            from.traverse(this.list::add, n -> n, order);
        }
    }

    public static Iterator<? extends TreeNode<?>> nodeIterator(TreeNode<?> from, Order order) {
        return new TreeIterator<>(from, n -> n, order);
    }

    public static <T> Iterator<T> valueIterator(TreeNode<T> from, Order order) {
        return new TreeIterator<>(from, v -> (T) v.getValue(), order);
    }

    @Override
    public boolean hasNext() {
        return !this.list.isEmpty();
    }

    @Override
    public V next() {
        return this.mapper.apply(this.list.poll());
    }
}
