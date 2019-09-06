package de.team33.test.collections;

import de.team33.libs.collections.lists.ArrayList;

import java.util.stream.Stream;

public class ArrayListTest extends ListTestBase {

    @Override
    protected final <E> ArrayList<E> newSubject(final Stream<E> origin) {
        return new ArrayList<>(origin);
    }
}
