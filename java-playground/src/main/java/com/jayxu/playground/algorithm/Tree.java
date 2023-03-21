/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.collections.impl.Counter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author     xujiajing
 *
 * @param  <V> value type
 * @param  <N> node type
 */
@NoArgsConstructor
public abstract class Tree<T, N extends TreeNode<T>>
        implements Collection<T>, Serializable {
    @Serial
    private static final long serialVersionUID = -2868930520060725242L;
    protected static final BinaryOperator<Boolean> OR = (a, b) -> a || b;
    @Getter
    protected N root;
    protected int size;
    @Getter
    @Setter
    @NonNull
    protected Order order = Order.PRE_ORDER;
    /**
     * root.depth is 0
     */
    @Getter
    protected int depth;

    public Tree(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "NULL root";
        }

        return String.format("order: %s, size: %s, depth: %s\nroot: %s",
            this.order, this.size, this.depth, this.root.toString());
    }

    public String toString(String separator) {
        if (this.root == null) {
            return "NULL root";
        }

        var sb = new StringBuilder();
        this.root.traverseValue(v -> sb.append(v).append(separator),
            this.order);

        return sb.delete(sb.length() - separator.length(), sb.length())
            .toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        if (this.root == null || o == null) {
            return false;
        }

        return this.root.traverseMatch((T) o) != null;
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] a) {
        var array = a.length >= this.size ? a : (E[]) Array
            .newInstance(a.getClass().getComponentType(), this.size);

        if (this.root == null) {
            return array;
        }

        this.root.traverseOrdered(new Counter(),
            (j, t) -> array[j.getCount()] = (E) t, TreeNode::getValue,
            this.order);

        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator(this.order);
    }

    public Iterator<T> iterator(@SuppressWarnings("hiding") Order order) {
        return TreeIterator.valueIterator(this.root, order);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public Stream<N> nodeStream() {
        return this.nodeStream(this.order);
    }

    public Stream<N> nodeStream(@SuppressWarnings("hiding") Order order) {
        return StreamSupport.stream(
            Spliterators.spliterator(this.nodeIterator(order), this.size, 0),
            false);
    }

    public Iterator<N> nodeIterator() {
        return this.nodeIterator(this.order);
    }

    @SuppressWarnings("unchecked")
    public Iterator<N> nodeIterator(@SuppressWarnings("hiding") Order order) {
        return (Iterator<N>) TreeIterator.nodeIterator(this.root, order);
    }

    protected void recalculateLevelsNDepth() {
        if (this.root != null) {
            this.root.level = 0;
        }

        this.depth = 0;
        this.nodeStream(Order.LEVEL_ORDER).skip(1) // skip root
            .forEach(n -> {
                n.level = n.parent.level + 1;
                this.depth = Math.max(this.depth, n.level);
            });
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        return c.stream().map(this::add).reduce(OR).orElse(false);
    }

    @SuppressWarnings("unchecked")
    public boolean addAll(@NonNull T... values) {
        return Arrays.stream(values).map(this::add).reduce(OR).orElse(false);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return c.stream().map(this::remove).reduce(OR).orElse(false);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return this.stream().filter(v -> !c.contains(v)).map(this::remove)
            .reduce(OR).orElse(false);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
        this.depth = 0;
    }

}
