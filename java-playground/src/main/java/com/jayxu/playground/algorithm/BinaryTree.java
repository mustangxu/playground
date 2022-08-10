/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.algorithm;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.collections.impl.Counter;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xujiajing
 */
@RequiredArgsConstructor
public class BinaryTree<T extends Comparable<T>>
        implements Collection<T>, Serializable {
    private static final long serialVersionUID = -2587073973029359971L;
    private static final BinaryOperator<Boolean> OR = (a, b) -> a || b;
    @Getter
    private TreeNode root;
    private int size;
    @Getter
    @Setter
    @NonNull
    private Order order;

    /**
     * root.depth is 0
     */
    @Getter
    private int depth;

    enum Order {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER
    }

    public BinaryTree() {
        this(Order.PRE_ORDER);
    }

    @Data
    @ToString(exclude = "parent")
    @RequiredArgsConstructor()
    public class TreeNode implements Serializable {
        private static final long serialVersionUID = 3051936643420669043L;
        private final T value;
        /**
         * Starts from 0
         */
        private int level;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        private TreeNode insertLeft(@NonNull T v) {
            if (this.left == null) {
                this.left = new TreeNode(v);
                this.left.level = this.level + 1;
                this.left.parent = this;

                return this.left;
            }

            return this.left.insertChild(v);
        }

        private TreeNode insertRight(@NonNull T v) {
            if (this.right == null) {
                this.right = new TreeNode(v);
                this.right.level = this.level + 1;
                this.right.parent = this;

                return this.right;
            }

            return this.right.insertChild(v);
        }

        public boolean isRoot() {
            return this.parent == null;
        }

        public TreeNode insertChild(@NonNull T v) {
            if (v.compareTo(this.value) <= 0) {
                return this.insertLeft(v);
            }

            return this.insertRight(v);
        }

        <V> void traverseOrdered(Counter counter, BiConsumer<Counter, V> fun,
                Function<TreeNode, V> mapper,
                @SuppressWarnings("hiding") Order order) {
            var v = mapper.apply(this);

            switch (order) {
                case PRE_ORDER:
                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper, order);
                    }

                    fun.accept(counter, v);
                    counter.increment();

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper, order);
                    }

                    break;
                case IN_ORDER:
                    fun.accept(counter, v);
                    counter.increment();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper, order);
                    }

                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper, order);
                    }

                    break;
                case POST_ORDER:
                    if (this.right != null) {
                        this.right.traverseOrdered(counter, fun, mapper, order);
                    }

                    fun.accept(counter, v);
                    counter.increment();

                    if (this.left != null) {
                        this.left.traverseOrdered(counter, fun, mapper, order);
                    }

                    break;
                case LEVEL_ORDER:
                    var list = new LinkedList<TreeNode>();
                    list.add(this);

                    TreeNode n;
                    while ((n = list.poll()) != null) {
                        fun.accept(counter, mapper.apply(n));
                        counter.increment();

                        if (n.left != null) {
                            list.add(n.left);
                        }

                        if (n.right != null) {
                            list.add(n.right);
                        }
                    }

                    break;
                default:
                    break;
            }
        }

        public void traverseValue(Consumer<T> fun) {
            this.traverseValue(fun, BinaryTree.this.order);
        }

        public void traverseValue(Consumer<T> fun,
                @SuppressWarnings("hiding") Order order) {
            this.traverse(fun, TreeNode::getValue, order);
        }

        public void traverseNode(Consumer<TreeNode> fun) {
            this.traverse(fun, n -> n, BinaryTree.this.order);
        }

        public void traverseNode(Consumer<TreeNode> fun,
                @SuppressWarnings("hiding") Order order) {
            this.traverse(fun, n -> n, order);
        }

        <V> void traverse(Consumer<V> fun, Function<TreeNode, V> mapper,
                @SuppressWarnings("hiding") Order order) {
            this.traverseOrdered(new Counter(), (i, v) -> fun.accept(v),
                mapper, order);
        }

        TreeNode traverseMatch(T v) {
            if (this.value.equals(v)) {
                return this;
            }

            if (v.compareTo(BinaryTree.this.root.value) < 0
                && this.left != null) {
                return this.left.traverseMatch(v);
            }

            if (v.compareTo(BinaryTree.this.root.value) > 0
                && this.right != null) {
                return this.right.traverseMatch(v);
            }

            return null;
        }

        TreeNode replace(@NonNull T v) {
            var newNode = new TreeNode(v);
            newNode.left = this.left;
            newNode.right = this.right;
            newNode.parent = this.parent;

            if (this.left != null) {
                this.left.parent = newNode;
            }
            if (this.right != null) {
                this.right.parent = newNode;
            }

            if (this.parent != null) {
                if (this.parent.left == this) {
                    this.parent.left = newNode;
                }
                if (this.parent.right == this) {
                    this.parent.right = newNode;
                }
            }

            return newNode;
        }
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
        this.root.traverseValue(v -> sb.append(v).append(separator));

        return sb.delete(sb.length() - separator.length(), sb.length())
            .toString();
    }

    @Override
    public Iterator<T> iterator() {
        return this.iterator(this.order);
    }

    public Iterator<T> iterator(@SuppressWarnings("hiding") Order order) {
        var list = new LinkedList<T>();

        if (this.root != null) {
            this.root.traverseValue(list::add, order);
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public T next() {
                return list.poll();
            }
        };
    }

    public Iterator<TreeNode> nodeIterator() {
        return this.nodeIterator(this.order);
    }

    public Iterator<TreeNode>
            nodeIterator(@SuppressWarnings("hiding") Order order) {
        var list = new LinkedList<TreeNode>();

        if (this.root != null) {
            this.root.traverseNode(list::add, order);
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public TreeNode next() {
                return list.poll();
            }
        };
    }

    public Stream<TreeNode> nodeStream() {
        return this.nodeStream(this.order);
    }

    public Stream<TreeNode>
            nodeStream(@SuppressWarnings("hiding") Order order) {
        return StreamSupport.stream(Spliterators
            .spliterator(this.nodeIterator(order), this.size, 0), false);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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
        var array = a.length >= this.size ? a
            : (E[]) Array.newInstance(a.getClass().getComponentType(),
                this.size);

        if (this.root == null) {
            return array;
        }

        this.root.traverseOrdered(new Counter(),
            (j, t) -> array[j.getCount()] = (E) t, TreeNode::getValue,
            this.order);

        return array;
    }

    @Override
    public boolean add(@NonNull T e) {
        this.size++;

        if (this.root == null) {
            this.root = new TreeNode(e);
        } else {
            var node = this.root.insertChild(e);
            this.depth = Math.max(node.level, this.depth);
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(@NonNull Object o) {
        if (this.root == null) {
            return false;
        }

        var node = this.root.traverseMatch((T) o);
        if (node == null) { // not include
            return false;
        }

        if (this.size == 1) { // root only
            this.clear();
            return true;
        }

        if (node.left == null || node.right == null) {
            var child = node.left == null ? node.right : node.left;
            var parent = node.parent;

            if (child != null) {
                child.parent = parent;
            }

            if (node.isRoot()) {
                this.root = child;
            } else if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            var left = node.right.left;

            if (left == null) {
                node.right.left = node.left;
                node.left.parent = node.right;
            } else {
                while (left.left != null) {
                    left = left.left;
                }

                var newNode = node.replace(left.value);
                if (node.isRoot()) {
                    this.root = newNode;
                }

                left.parent.left = null; // delete
            }
        }

        this.size--;
        this.recalculateLevelsNDepth();
        return true;
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

    private void recalculateLevelsNDepth() {
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
}
