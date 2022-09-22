package de.team33.collections.e4;

import java.util.function.IntFunction;

public interface Collection<E> extends Streamable<E> {

    /**
     * Returns the number of elements in this collection.
     */
    long size();

    /**
     * Determines whether this collection contains a specific element.
     * <p>
     * An implementation must return a regular result even if the element in question cannot be contained in this
     * collection. For example, a {@code null} argument must not result in a {@link NullPointerException}.
     *
     * @return {@code true} if this collection contains the given element, otherwise {@code false}.
     */
    boolean contains(Object element);

    /**
     * Determines whether this collection contains all elements of another given aggregate.
     * <p>
     * An implementation must return a regular result even if (some of) the elements in question cannot be contained in
     * this collection. For example, a {@code null} element must not result in a {@link NullPointerException}.
     *
     * @return {@code true} if this collection contains all elements of the given aggregate, otherwise {@code false}.
     * <p>
     * By definition, if the other aggregate is empty, this collection contains all elements of the other aggregate.
     */
    default boolean containsAll(final Streamable<?> other) {
        return other.stream().allMatch(this::contains);
    }

    /**
     * Determines whether this collection contains any element of another given aggregate.
     * <p>
     * An implementation must return a regular result even if (some of) the elements in question cannot be contained in
     * this collection. For example, a {@code null} element must not result in a {@link NullPointerException}.
     *
     * @return {@code true} if this collection contains any element of the given aggregate, otherwise {@code false}.
     * <p>
     * By definition, if the other aggregate is empty, this collection does not contain any element of the other
     * aggregate.
     */
    default boolean containsAny(final Streamable<?> other) {
        return other.stream().anyMatch(this::contains);
    }

    /**
     * Determines whether this and another collection contain the same elements.
     * Neither the order of the elements, any repetitions nor the size of the two collections is decisive.
     * <p>
     * An implementation must return a regular result even if (some of) the elements in question cannot be contained in
     * this or the other collection. For example, a {@code null} element must not result in a
     * {@link NullPointerException}.
     *
     * @return {@code true} if this and the other collection both contain the same elements, otherwise {@code false}.
     * <p>
     * By definition, if both collections are empty, they do contain the same elements.
     */
    default boolean isCoherent(final Collection<?> other) {
        return containsAll(other) && other.containsAll(this);
    }

    default <T> T[] toArray(final IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }
}
