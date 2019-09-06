package de.team33.test.collections;

import de.team33.libs.collections.List;

import java.util.stream.Stream;

public abstract class ListTestBase extends CollectionTestBase {

    @Override
    protected abstract <E> List<E> newSubject(Stream<E> origin);
}
