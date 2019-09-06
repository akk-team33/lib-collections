package de.team33.libs.collections.lists;

import de.team33.libs.collections.List;

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
