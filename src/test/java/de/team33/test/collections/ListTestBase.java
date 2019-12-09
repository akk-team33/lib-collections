package de.team33.test.collections;

import de.team33.libs.collections.v1.List;

import java.util.stream.Stream;

public abstract class ListTestBase extends CollectionTestBase {

    @Override
    protected abstract <E> List<E> newSubject(Stream<E> origin);
}
