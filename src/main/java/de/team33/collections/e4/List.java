package de.team33.collections.e4;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List<E> implements Collection<E> {

    private final Object[] backing;

    public List(final Streamable<E> origin) {
        this.backing = origin.stream().toArray(Object[]::new);
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Stream<E> stream() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
