package de.team33.libs.collections.v1.lists;

import de.team33.libs.collections.v1.List;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class ArrayList<T> implements List<T> {

    private final java.util.ArrayList<T> backing;

    public ArrayList(final Stream<T> origin) {
        this.backing = origin.collect(toCollection(java.util.ArrayList::new));
    }

    @Override
    public final long size() {
        return backing.size();
    }

    @Override
    public final Stream<T> stream() {
        return backing.stream();
    }
}
