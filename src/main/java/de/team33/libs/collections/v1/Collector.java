package de.team33.libs.collections.v1;

import java.util.Collection;
import java.util.function.Predicate;

public class Collector<E, C extends Collection<E>> {



    /**
     * @see Collection#add(Object)
     */
    public final Collector<E, C> add(final E e) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#remove(Object)
     */
    public final Collector<E, C> remove(final Object o) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> addAll(final Collection<? extends E> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#removeAll(Collection)
     */
    public final Collector<E, C> removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#removeIf(Predicate)
     */
    public final Collector<E, C> removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#retainAll(Collection)
     */
    public final Collector<E, C> retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> clear() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
