package net.team33.collections;

public class ArrayList<E> implements List.Mutable<E, ArrayList<E>, ArrayList<E>> {

    private final java.util.ArrayList<E> backing = new java.util.ArrayList<E>(0);

    @Override public final ArrayList<E> self() {
        return this;
    }

    @Override public final boolean contains(final Object element) {
        return backing.contains(element);
    }

    @Override public final int size() {
        return backing.size();
    }

    @Override public final ArrayList<E> add(final E element) {
        backing.add(element);
        return this;
    }

    @Override public final ArrayList<E> remove(final Object element) {
        while (backing.remove(element)) ;
        return this;
    }

    @Override public final ArrayList<E> clear() {
        backing.clear();
        return this;
    }
}
