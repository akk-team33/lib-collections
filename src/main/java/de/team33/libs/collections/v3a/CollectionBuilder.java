package de.team33.libs.collections.v3a;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionBuilder<E, C extends Collection<E>, B extends CollectionBuilder<E, C, B>> {

    public final B add(final E element) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B addAll(final Collection<? extends E> elements) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B remove(final Object element) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B removeAll(final Collection<?> elements) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B retainAll(final Collection<?> elements) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final B clear() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final <R extends Collection<? super E>> R build(final Function<Collection<E>, R> builder) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
