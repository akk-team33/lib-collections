package de.team33.test.collections.legacy;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CollectionTest {

    private final Random random = new Random();
    private final Supplier<Integer> anySize = () -> random.nextInt(1000);
    private final Supplier<Long> anyLong = random::nextLong;

    @Test
    public void size() {
        stream(anySize, 0, 1).limit(100).forEach(size -> {
            final Collection<Long> subject = newMutable();
            stream(anyLong, 0L, null).distinct().limit(size).forEach(subject::add);
            assertEquals(size.intValue(), subject.size());
        });
    }

    @Test
    public void empty() {
        Stream.of(0, 1, 0, 2, 0, 7, 0, 13).forEach(size -> {
            final Collection<Long> subject = newMutable();
            stream(anyLong, 0L, null).limit(size).forEach(subject::add);
            assertEquals(0 == subject.size(), subject.isEmpty());
        });
    }

    @Test
    public void contains() {
        final Set<Long> includes = stream(anyLong, 0L, null).limit(100).collect(Collectors.toSet());
        final Set<Long> excludes = stream(anyLong, 0L, null).limit(100).collect(Collectors.toSet());
        final Collection<Long> subject = newMutable();
        includes.forEach(subject::add);
        includes.forEach(value -> assertTrue(subject.contains(value)));
        excludes.forEach(subject::remove);
        excludes.forEach(value -> assertFalse(subject.contains(value)));
    }

    @SafeVarargs
    private static <E> Stream<E> stream(final Supplier<E> supplier, final E... required) {
        return Stream.concat(Stream.of(required), Stream.generate(supplier));
    }

    private Collection<Long> newMutable() {
        return new HashSet<>();
    }
}
