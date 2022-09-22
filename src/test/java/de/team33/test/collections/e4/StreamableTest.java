package de.team33.test.collections.e4;

import de.team33.collections.e4.Streamable;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamableTest {

    private static final Object[] OBJECTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Test
    final void stream() {
        final Streamable<Object> subject = () -> Stream.of(OBJECTS);
        final Object[] result = subject.stream().toArray(Object[]::new);
        assertArrayEquals(OBJECTS, result);
    }
}
