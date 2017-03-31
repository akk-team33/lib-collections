package net.team33.collections;

import java.util.stream.Stream;

public interface Collection<E> {

    /**
     * See {@link java.util.Collection#contains(Object)}
     */
    boolean contains(Object element);

    /**
     * See {@link java.util.Collection#containsAll(java.util.Collection)}
     */
    default boolean containsAll(final Stream<?> elements) {
        return elements.allMatch(this::contains);
    }

    /**
     * See {@link java.util.Collection#size()}
     */
    int size();

    /**
     * See {@link java.util.Collection#isEmpty()}
     */
    default boolean isEmpty() {
        return 0 == size();
    }

    @SuppressWarnings("unchecked") interface Mutable<E, C extends Collection<E>, M extends Mutable<E, C, M>> extends Collection<E> {

        M self();

        /**
         * See {@link java.util.Collection#add(Object)}
         */
        M add(E element);

        /**
         * See {@link java.util.Collection#addAll(java.util.Collection)}
         */
        default M addAll(final Stream<? extends E> elements) {
            elements.forEach(this::add);
            return self();
        }

        /**
         * See {@link java.util.Collection#remove(Object)}
         */
        M remove(Object element);

        default M removeAll(final Stream<?> elements) {
            elements.forEach(this::remove);
            return self();
        }

        M clear();
    }
}
