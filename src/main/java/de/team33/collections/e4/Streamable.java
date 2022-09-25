package de.team33.collections.e4;

import java.util.stream.Stream;

/**
 * An abstraction of types that (actually or virtually) contain elements of a specific type and that can provide a
 * {@link Stream} for processing those elements.
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
