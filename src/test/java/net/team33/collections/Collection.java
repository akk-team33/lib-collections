package net.team33.collections;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public interface Collection<E> {

    /**
     * Returns the number of elements in this collection.
     *
     * @see java.util.Collection#size()
     */
    int size();

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @see java.util.Collection#isEmpty()
     */
    default boolean isEmpty() {
        return 0 == size();
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @see java.util.Collection#contains(Object)
     */
    boolean contains(final Object o);

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the other collection.
     *
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    default boolean containsAll(final Collection<?> other) {
        return containsAll(other.stream());
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the given stream.
     */
    default boolean containsAll(final Stream<?> other) {
        return other.allMatch(this::contains);
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @see java.lang.Iterable#forEach(Consumer)
     */
    default void forEach(final Consumer<? super E> action) {
        stream().forEach(action);
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     * <p>
     * The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @see java.util.Collection#toArray()
     */
    default Object[] toArray() {
        return stream().toArray();
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @throws ArrayStoreException if the runtime type of the array returned from the array generator is not a
     *                             supertype of the runtime type of every element in this Collection.
     * @see java.util.Collection#toArray(Object[])
     */
    default <T> T[] toArray(final IntFunction<T[]> generator) {
        return stream().toArray(generator);
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * @see java.util.Collection#stream()
     */
    Stream<E> stream();

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source. It is allowable for this method to return a sequential stream.
     *
     * @see java.util.Collection#parallelStream()
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

        @Override
        public final String toString() {
            return stream().map(Objects::toString).collect(joining(",", "[", "]"));
        }
    }
}
