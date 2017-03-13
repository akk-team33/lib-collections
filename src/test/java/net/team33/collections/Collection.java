package net.team33.collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Collection<E> implements java.util.Collection<E> {

    @Override public int size() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean isEmpty() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean contains(final Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public Iterator<E> iterator() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public void forEach(final Consumer<? super E> action) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public Object[] toArray() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public <T> T[] toArray(final T[] a) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean add(final E e) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean remove(final Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean containsAll(final java.util.Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean addAll(final java.util.Collection<? extends E> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean removeAll(final java.util.Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public boolean retainAll(final java.util.Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public void clear() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public Stream<E> stream() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override public Stream<E> parallelStream() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
