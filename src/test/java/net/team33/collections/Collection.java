package net.team33.collections;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public interface Collection<E> {

    /**
     * @return The size of the Collection, the number of contained elements.
     */
    int size();

    /**
     * Determines whether the Collection is empty.
     */
    default boolean isEmpty() {
        return 0 == size();
    }

    /**
     * Determines whether the Collection contains a specific element.
     */
    boolean contains(final Object o);

    /**
     * Determines whether the Collection contains all of some specific elements.
     */
    default boolean containsAll(final Collection<?> other) {
        return containsAll(other.stream());
    }

    /**
     * Determines whether the Collection contains all of some specific elements.
     */
    default boolean containsAll(final Stream<?> other) {
        return other.allMatch(this::contains);
    }

    default void forEach(final Consumer<? super E> action) {
        stream().forEach(action);
    }

    default Object[] toArray() {
        return stream().toArray();
    }

    default <T> T[] toArray(final IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }

    /**
     * @return The contained elements as a Stream.
     */
    Stream<E> stream();

    /**
     * @return The contained elements as a Stream. Probably a parallel Stream.
     */
    Stream<E> parallelStream();

    interface Builder<E, C extends Collection<E>, B extends Builder<E, C, B>> {

        default B add(final E... elements) {
            return addAll(Stream.of(elements));
        }

        default B addAll(final Collection<? extends E> elements) {
            return addAll(elements.stream());
        }

        B addAll(Stream<? extends E> elements);

        default B remove(final Object... elements) {
            return removeAll(Stream.of(elements));
        }

        default B removeAll(final Collection<?> elements) {
            return removeAll(elements.stream());
        }

        B removeAll(Stream<?> elements);

        default B retain(final Object... elements) {
            return retainAll(Stream.of(elements));
        }

        default B retainAll(final Collection<?> elements) {
            return retainAll(elements.stream());
        }

        B retainAll(Stream<?> elements);

        B clear();

        C build();
    }

    abstract class Base<E> implements Collection<E> {
    }
}
