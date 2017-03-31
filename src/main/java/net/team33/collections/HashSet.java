package net.team33.collections;

public class HashSet<E> implements Set.Mutable<E, HashSet<E>, HashSet<E>> {

    private final java.util.HashSet<E> backing = new java.util.HashSet<E>();

    @Override public final HashSet<E> self() {
        return this;
    }

    @Override public final boolean contains(final Object element) {
        return backing.contains(element);
    }

    @Override public final int size() {
        return backing.size();
    }

    @Override public final HashSet<E> add(final E element) {
        backing.add(element);
        return this;
    }

    @Override public final HashSet<E> remove(final Object element) {
        backing.remove(element);
        return this;
    }

    @Override public final HashSet<E> clear() {
        backing.clear();
        return this;
    }
}
