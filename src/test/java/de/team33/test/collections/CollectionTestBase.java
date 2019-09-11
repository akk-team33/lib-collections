package de.team33.test.collections;

import de.team33.libs.collections.Collection;
import de.team33.libs.collections.Streamable;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public abstract class CollectionTestBase extends StreamableTestBase {

    @Override
    protected abstract <E> Collection<E> newSubject(Stream<E> origin);

    @Test
    public void size() {
        // strongly TODO
        new Series(30).forEach(op -> {
            final List<Object> result = op.subject.stream().collect(Collectors.toList());
            assertEquals("subject.size() must be equal to origin.size()", op.origin.size(), result.size());
        });
    }
}
