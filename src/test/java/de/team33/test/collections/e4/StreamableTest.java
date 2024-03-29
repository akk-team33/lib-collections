package de.team33.test.collections.e4;

import de.team33.collections.e4.Streamable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class StreamableTest {

    private static final List<Object> OBJECTS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final Streamable<?> subject = OBJECTS::stream;

    @Test
    final void stream() {
        assertNotSame(subject.stream(), subject.stream());
        assertEquals(subject.stream().collect(Collectors.toList()), subject.stream().collect(Collectors.toList()));
    }
}
