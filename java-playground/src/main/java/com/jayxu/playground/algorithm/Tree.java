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
import jakarta.annotation.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @param <T>
 *         value type
 *
 * @author xujiajing
 */
@NoArgsConstructor
public abstract class Tree<T> implements Collection<T>, Serializable {
    protected static final BinaryOperator<Boolean> OR = (a, b) -> a || b;
    @Serial
    private static final long serialVersionUID = -2868930520060725242L;
    @Getter
    @Nullable
    protected TreeNode<T> root;
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

        return String.format("order: %s, size: %s, depth: %s\nroot: %s", this.order, this.size, this.depth, this.root);
    }

    public String toString(String separator) {
        if (this.root == null) {
            return "NULL root";
        }

        var sb = new StringBuilder();
        this.root.traverseValue(v -> sb.append(v).append(separator), this.order);

        return sb.delete(sb.length() - separator.length(), sb.length()).toString();
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

    @Override
    public boolean contains(Object o) {
        if (this.root == null || o == null) {
            return false;
        }

        return this.root.traverseMatch((T) o) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator(this.order);
    }

    @Override
    public Object[] toArray() {
        return this.toArray(new Object[this.size]);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        var array = a.length >= this.size ? a : (E[]) Array.newInstance(a.getClass().getComponentType(), this.size);

        if (this.root == null) {
            return array;
        }

        this.root.traverseOrdered(new Counter(), (j, t) -> array[j.getCount()] = (E) t, TreeNode::getValue, this.order);

        return array;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        return c.stream().map(this::add).reduce(OR).orElse(false);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return c.stream().map(this::remove).reduce(OR).orElse(false);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return this.stream().filter(v -> !c.contains(v)).map(this::remove).reduce(OR).orElse(false);
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
        this.depth = 0;
    }

    public Stream<? extends TreeNode<T>> nodeStream() {
        return this.nodeStream(this.order);
    }

    public Stream<? extends TreeNode<T>> nodeStream(@SuppressWarnings("hiding") Order order) {
        return StreamSupport.stream(Spliterators.spliterator(this.nodeIterator(order), this.size, 0), false);
    }

    public Iterator<? extends TreeNode<T>> nodeIterator() {
        return this.nodeIterator(this.order);
    }

    public Iterator<? extends TreeNode<T>> nodeIterator(@SuppressWarnings("hiding") Order order) {
        return (Iterator<TreeNode<T>>) TreeIterator.nodeIterator(this.root, order);
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

    @SuppressWarnings("unchecked")
    public boolean addAll(@NonNull T... values) {
        return Arrays.stream(values).map(this::add).reduce(OR).orElse(false);
    }

}
