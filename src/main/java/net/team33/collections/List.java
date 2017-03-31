package net.team33.collections;

public interface List<E> extends Collection<E> {

    @SuppressWarnings("unchecked") interface Mutable<E, L extends List<E>, M extends Mutable<E, L, M>>
            extends Collection.Mutable<E, L, M>, List<E> {
    }
}
