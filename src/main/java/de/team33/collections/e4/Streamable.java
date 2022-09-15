package de.team33.collections.e4;

import java.util.stream.Stream;

/**
 * Abstracts aggregate types that can provide a stream of a specific element type.
 *
 * @param <E> The element type.
 */
@FunctionalInterface
public interface Streamable<E> {

    /**
     * Returns a sequential {@link Stream} with this aggregate as its source.
     */
    Stream<E> stream();
}
