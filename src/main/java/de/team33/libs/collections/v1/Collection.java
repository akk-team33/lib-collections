package de.team33.libs.collections.v1;

import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface Collection<E> extends Streamable<E> {

    /**
     * Returns the number of elements in this collection.
     */
    long size();

    /**
     * Returns {@code true} if this collection contains no elements.
     */
    default boolean isEmpty() {
        return (0 == size());
    }

    /**
     * Returns {@code true} if this collection contains the specified element {@code o}.
     * More formally, returns {@code true} if and only if this collection contains at least one element {@code e}
     * such that {@code (o==null ? e==null : o.equals(e))}.
     */
    default boolean contains(final Object o) {
        return stream().anyMatch(e -> Objects.equals(e, o));
    }

    /**
     * Returns {@code true} if this collection contains all of the elements
     * in the specified collection.
     *
     * @see #contains(Object)
     */
    default boolean containsAll(final Streamable<?> other) {
        return other.stream().allMatch(this::contains);
    }

    /**
     * <p>Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.</p>
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.</p>
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.</p>
     */
    default Object[] toArray() {
        return stream().toArray();
    }

    /**
     * Returns an array containing the elements of this stream, using the provided generator function to allocate
     * the returned array, as well as any additional arrays that might be required for a partitioned execution
     * or for resizing.
     *
     * @throws ArrayStoreException if the runtime type of the array returned from the array generator
     *                             is not a supertype of the runtime type of every element in this stream.
     */
    default <T> T[] toArray(final IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }

    interface Mutable<E> extends Collection<E> {

        /**
         * Returns an immutable copy of this mutable collection.
         */
        Collection<E> immutable();

        /**
         * <p>Ensures that this collection contains the specified element.</p>
         *
         * <p>If a collection refuses to add a particular element for any reason other than that it already contains
         * the element, it <i>must</i> throw an exception. This preserves the invariant that a collection always
         * contains the specified element after this call returns.</p>
         *
         * @return {@code this} (builder pattern).
         *
         * @throws NullPointerException if the specified element is null and this
         *         collection does not permit null elements
         * @throws IllegalArgumentException if some property of the element
         *         prevents it from being added to this collection
         * @throws ClassCastException if the class of the specified element
         *         prevents it from being added to this collection
         * @throws IllegalStateException if the element cannot be added at this
         *         time due to insertion restrictions
         */
        Mutable<E> add(E e);

        /**
         * Adds all of the elements in the specified collection to this collection.
         *
         * @return {@code this} (builder pattern).
         * @throws NullPointerException     if the specified collection contains a
         *                                  null element and this collection does not permit null elements,
         *                                  or if the specified collection is null
         * @throws IllegalArgumentException if some property of an element of the
         *                                  specified collection prevents it from being added to this
         *                                  collection
         * @throws ClassCastException       if the class of an element of the specified
         *                                  collection prevents it from being added to this collection
         * @throws IllegalStateException    if not all the elements can be added at
         *                                  this time due to insertion restrictions
         * @see #add(Object)
         */
        default Mutable<E> addAll(final Streamable<? extends E> other) {
            other.forEach(this::add);
            return this;
        }

        /**
         * Removes all instances of the specified element from this collection.
         *
         * @return {@code this} (builder pattern).
         */
        Mutable<E> remove(Object o);

        /**
         * Removes all of this collection's elements that are also contained in the specified collection.
         * After this call returns, this collection will contain no elements in common with the specified collection.
         *
         * @return {@code this} (builder pattern).
         *
         * @see #remove(Object)
         */
        default Mutable<E> removeAll(final Streamable<?> other) {
            other.forEach(this::remove);
            return this;
        }

        /**
         * Removes all of the elements of this collection that satisfy the given
         * predicate.  Errors or runtime exceptions thrown during iteration or by
         * the predicate are relayed to the caller.
         *
         * @return {@code this} (builder pattern).
         *
         * @throws NullPointerException if the specified filter is null.
         */
        default Mutable<E> removeIf(final Predicate<? super E> filter) {
            immutable().stream().filter(filter).forEach(this::remove);
            return this;
        }

        /**
         * Retains only the elements in this collection that are contained in the
         * specified collection.  In other words, removes from
         * this collection all of its elements that are not contained in the
         * specified collection.
         *
         * @return {@code this} (builder pattern).
         * @see #remove(Object)
         */
        default Mutable<E> retainAll(final Collection<?> other) {
            return removeIf(e -> !other.contains(e));
        }

        /**
         * Removes all of the elements from this collection.
         * The collection will be empty after this method returns.
         */
        default Mutable<E> clear() {
            return removeIf(e -> true);
        }
    }
}
