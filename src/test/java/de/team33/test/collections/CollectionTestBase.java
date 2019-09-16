package de.team33.test.collections;

import de.team33.libs.collections.Collection;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public abstract class CollectionTestBase extends StreamableTestBase {

    @Override
    protected abstract <E> Collection<E> newSubject(Stream<E> origin);

    @Test
    public void size() {
        new Series<>(this::newSubject, 30).forEach(step -> {
            assertEquals("subject.size() must be equal to origin.size()", step.origin.size(), step.subject.size());
        });
    }
}
