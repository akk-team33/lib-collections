package net.team33.collections;

public interface Set<E> extends Collection<E> {

    @SuppressWarnings("unchecked") interface Mutable<E, S extends Set<E>, M extends Mutable<E, S, M>>
            extends Collection.Mutable<E, S, M>, Set<E> {
    }
}
