package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();

    public E getValue() {
        return value;
    }

    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node<?>)) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return Objects.equals(children, node.children)
                && Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, value);
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
