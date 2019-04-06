package ru.job4j.tree;

import java.util.*;

/**
 * Класс простейшего дерева Tree.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int modCount = 0;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод, добавляющий элемент в набор.
     * @param parent - значение родительского элемента для вставляемого элемента.
     * @param child - значение вставляемого элемента.
     * @return true - если элемент был успешно добавлен в дерево, false - в противном случае.
     */
    @Override
    public boolean add(E parent, E child) {
        class Result {
            boolean result = false;
        }
        Result res = new Result();
        this.findBy(parent).ifPresent(
                x -> {
                    if (this.findBy(child).isEmpty()) {
                        x.add(new Node<>(child));
                        res.result = true;
                    }
                }
        );
        if (res.result) {
            modCount++;
        }
        return res.result;
    }

    /**
     * Метод, осуществляющий поиск элемента в дереве.
     * @param value - значение искомого элемента.
     * @return результаты поиска в виде контейнера Optional.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean res = true;
        for (Node<E> node : this) {
            if (node.leaves().size() > 2) {
                res = false;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return new Iterator<>() {
            private int currentMod = modCount;
            private Queue<Node<E>> data = new LinkedList<>(List.of(root));

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public Node<E> next() {
                if (this.currentMod < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> elem = data.poll();
                data.addAll(elem.leaves());
                return elem;
            }
        };
    }
}