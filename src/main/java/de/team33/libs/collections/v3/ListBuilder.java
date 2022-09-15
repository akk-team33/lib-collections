package de.team33.libs.collections.v3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ListBuilder<E> {

    private final List<E> backing;

    public ListBuilder(final List<E> backing) {
        this.backing = backing;
    }

    public ListBuilder() {
        this(new LinkedList<>());
    }

    public final ListBuilder<E> add(final E element) {
        backing.add(element);
        return this;
    }

    public final ListBuilder<E> add(final E element0, final E element1, final E ... more) {
        return addAll(Stream.concat(Stream.of(element0, element1), Stream.of(more)));
    }

    public final ListBuilder<E> addAll(final Stream<? extends E> elements) {
        elements.sequential().forEach(backing::add);
        return this;
    }

    public final ListBuilder<E> addAll(final Collection<? extends E> elements) {
        backing.addAll(elements);
        return this;
    }

    public final ListBuilder<E> addAll(final Iterable<? extends E> elements) {
        if (elements instanceof Collection<?>) {
            //noinspection unchecked
            return addAll((Collection<? extends E>) elements);
        }
        elements.forEach(backing::add);
        return this;
    }

    public final List<E> build() {
        return new ArrayList<>(backing);
    }
}
