package de.team33.libs.collections;

public interface List<E> extends Collection<E> {

    interface Mutable<E> extends Collection.Mutable<E>, List<E> {
    }
}
