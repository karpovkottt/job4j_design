package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    public void grow() {
        int capacity = container.length;
        if (container.length == 0) {
             capacity = 1;
        }
        container = Arrays.copyOf(container, capacity * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        if (size == container.length) {
            grow();
        }
        T oldValue = container[index];
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeValue = container[index];
        System.arraycopy(container, index + 1, container, index, size-- - index - 1);
        modCount++;
        return removeValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int point;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
