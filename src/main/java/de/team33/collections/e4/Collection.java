package de.team33.collections.e4;

import java.util.Objects;
import java.util.function.IntFunction;

public interface Collection<E> extends Streamable<E> {

    /**
     * Returns the number of elements in this collection.
     */
    long size();

    /**
     * Determines whether this collection contains a specific element.
     * <p>
     * There is no factual reason to expect that this method could throw an exception.
     * Either a collection contains an element in question or it doesn't.
     * In principle, this also applies to elements that cannot be contained in the collection at all.
     * An implementation must behave accordingly.
     *
     * @return {@code true} if this collection contains the given element, otherwise {@code false}.
     */
    default boolean contains(final Object element) {
        return stream().anyMatch(entry -> Objects.equals(entry, element));
    }

    /**
     * Determines whether this collection contains all elements of another given aggregate.
     * <p>
     * For a non-{@code null} argument, there is no factual reason to expect this method to throw an exception.
     * Either a collection contains the elements in question or it doesn't.
     * In principle, this also applies to elements that cannot be contained in the collection at all.
     * An implementation must behave accordingly.
     *
     * @return {@code true} if this collection contains all elements of the given aggregate, otherwise {@code false}.
     * <p>
     * By definition, if the other aggregate is empty, this collection contains all elements of the other aggregate.
     *
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    default boolean containsAll(final Streamable<?> other) {
        return other.stream().allMatch(this::contains);
    }

    /**
     * Determines whether this collection contains any element of another given aggregate.
     * <p>
     * For a non-{@code null} argument, there is no factual reason to expect this method to throw an exception.
     * Either a collection contains the elements in question or it doesn't.
     * In principle, this also applies to elements that cannot be contained in the collection at all.
     * An implementation must behave accordingly.
     *
     * @return {@code true} if this collection contains any element of the given aggregate, otherwise {@code false}.
     * <p>
     * By definition, if the other aggregate is empty, this collection does not contain any element of the other
     * aggregate.
     *
     * @throws NullPointerException if {@code other} is {@code null}.
     */
    default boolean containsAny(final Streamable<?> other) {
        return other.stream().anyMatch(this::contains);
    }

    /**
     * Determines whether this and another collection contain the same elements.
     * Neither the order of the elements, any repetitions nor the sizes of the two collections are decisive.
     * <p>
     * An implementation must return a regular result even if (some of) the elements in question cannot be contained in
     * this or the other collection. For example, a {@code null} element must not result in a
     * {@link NullPointerException}.
     *
     * @return {@code true} if this and the other collection both contain the same elements, otherwise {@code false}.
     * <p>
     * By definition, if both collections are empty, they do contain the same elements.
     */
    default boolean accords(final Collection<?> other) {
        return containsAll(other) && other.containsAll(this);
    }

    default <T> T[] toArray(final IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }
}
