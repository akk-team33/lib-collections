package net.team33.collections;

import java.util.stream.Stream;

public abstract class List<E> implements Collection<E> {

    private static class Builder<E, C extends List<E>, B extends Builder<E, C, B>> implements Collection.Builder<E, C, B> {
        @Override public B addAll(final Stream<? extends E> elements) {
            throw new UnsupportedOperationException("not yet implemented");
        }

        @Override public B removeAll(final Stream<?> elements) {
            throw new UnsupportedOperationException("not yet implemented");
        }

        @Override public B retainAll(final Stream<?> elements) {
            throw new UnsupportedOperationException("not yet implemented");
        }

        @Override public B clear() {
            throw new UnsupportedOperationException("not yet implemented");
        }

        @Override public C build() {
            throw new UnsupportedOperationException("not yet implemented");
        }
    }
}
