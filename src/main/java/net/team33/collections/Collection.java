package net.team33.collections;

import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Collection<E> extends Iterable<E> {

    /**
     * Returns the number of elements in this collection.
     * <p/>
     * If {@code 0} the collection is empty.
     */
    Size size();

    /**
     * Returns true if this collection contains the specified element.
     * More formally, returns true if and only if this collection contains at least one element e
     * such that (o==null ? e==null : o.equals(e)).
     * <p/>
     * It doesn't matter if the collection can or cannot contain the element at all.
     *
     * @param element the element whose presence in this collection is to be tested, possibly {@code null}.
     */
    boolean contains(Object element);

    /**
     * Returns true if this collection contains all of the elements in another collection.
     *
     * @param elements the other collection to be checked for containment in this collection
     * @throws NullPointerException if the other collection is {@code null}.
     */
    boolean containsAll(Collection<?> elements) throws NullPointerException;

    /**
     * Returns a sequential Stream with this collection as its source.
     */
    Stream<E> stream();

    public class Builder<E, R> {

        private final java.util.Collection<E> backing;

        protected Builder(final java.util.Collection<? extends E> template) {
            this.backing = template;
        }

        public boolean add(final E e) {
            throw new Error("Not yet implemented");
        }

        public boolean remove(final Object o) {
            throw new Error("Not yet implemented");
        }

        public boolean addAll(final java.util.Collection<? extends E> c) {
            throw new Error("Not yet implemented");
        }

        public boolean removeAll(final java.util.Collection<?> c) {
            throw new Error("Not yet implemented");
        }

        public boolean removeIf(final Predicate<? super E> filter) {
            throw new Error("Not yet implemented");
        }

        public boolean retainAll(final java.util.Collection<?> c) {
            throw new Error("Not yet implemented");
        }

        public void clear() {
            throw new Error("Not yet implemented");
        }
    }
}
