package net.team33.collections;

import java.util.function.Consumer;

public interface Iterable<E> {

    /**
     * Performs a specified action for each contained element until all elements have been processed or the action
     * throws an exception. Unless otherwise specified by the implementing class, actions are performed in the order
     * of iteration (if an iteration order is specified). Exceptions thrown by the action are relayed to the caller.
     *
     * @param action The action to be performed for each element.
     * @throws NullPointerException if the specified action is null
     */
    void forEach(Consumer<? super E> action) throws NullPointerException;
}
