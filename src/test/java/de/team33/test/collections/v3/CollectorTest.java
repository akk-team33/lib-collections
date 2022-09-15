package de.team33.test.collections.v3;

import de.team33.libs.collections.v3.Collector;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CollectorTest {

    private final Random random = new Random();

    @Test
    public final void add() {
        final List<Integer> expected = Stream.generate(random::nextInt)
                                             .limit(7)
                                             .collect(Collectors.toList());
        final List<Object> result = new Collector<Integer>()
                .add(expected.get(0))
                .add(expected.get(1), expected.get(2))
                .add(expected.get(3), expected.get(4), expected.get(5), expected.get(6))
                .build(ArrayList::new);
        assertEquals(expected, result);
    }

    @Test
    public final void addAllStream() {
        final List<Integer> expected = Stream.generate(random::nextInt)
                                             .limit(7)
                                             .collect(Collectors.toList());
        final List<Number> result = new Collector<Integer>()
                .addAll(expected.parallelStream())
                .build(ArrayList::new);
        assertEquals(expected, result);
    }

    @Test
    public final void addAllIterable() {
        final List<Integer> expected = Stream.generate(random::nextInt)
                                             .limit(7)
                                             .collect(Collectors.toList());
        //noinspection FunctionalExpressionCanBeFolded
        final List<Integer> result = new Collector<Integer>()
                .addAll(expected::iterator)
                .build(ArrayList::new);
        assertEquals(expected, result);
    }

    @Test
    public final void addAllCollection() {
        final List<Integer> expected = Stream.generate(random::nextInt)
                                             .limit(7)
                                             .collect(Collectors.toList());
        final List<Integer> result = new Collector<Integer>()
                .addAll(expected)
                .build(ArrayList::new);
        assertEquals(expected, result);
    }

    @Test
    public final void remove() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(7)
                                           .collect(Collectors.toList());
        final Integer toBeRemoved = origin.get(1);

        final List<Integer> expected = new ArrayList<>(origin);
        // force all elements to be at least duplicated ...
        expected.addAll(origin);
        // force all occurrences of <toBeRemoved> to be removed ...
        expected.removeAll(Collections.singletonList(toBeRemoved));

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .addAll(origin)
                .remove(toBeRemoved)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void removeAllStream() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(toBeRemoved.stream())
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void removeAllCollection() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(toBeRemoved)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void removeAllIterable() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        // force <toBeRemoved> being encapsulated as Iterable ...
        // noinspection FunctionalExpressionCanBeFolded
        final Iterable<Integer> iterable = toBeRemoved::iterator;
        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(iterable)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void retain() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(7)
                                           .collect(Collectors.toList());
        final Integer toBeRetained = origin.get(random.nextInt(7));

        final List<Integer> expected = new ArrayList<>(origin);
        // force all elements to be at least duplicated ...
        expected.addAll(origin);
        expected.retainAll(Collections.singletonList(toBeRetained));
        assertEquals("Precondition: <expected.size()> is expected to be 2", 2, expected.size());

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .addAll(origin)
                .retain(toBeRetained)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void retainAllStream() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(toBeRemoved.stream())
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void retainAllCollection() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(toBeRemoved)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }

    @Test
    public final void retainAllIterable() {
        final List<Integer> origin = Stream.generate(random::nextInt)
                                           .limit(27)
                                           .collect(Collectors.toList());
        final List<Integer> toBeRemoved = origin.stream()
                                                .filter(e -> random.nextBoolean())
                                                .collect(Collectors.toList());
        assertFalse("Precondition: <toBeRemoved> must not be empty", toBeRemoved.isEmpty());

        final List<Integer> expected = new ArrayList<>(origin);
        expected.removeAll(toBeRemoved);
        assertFalse("Precondition: <expected> must not be empty", expected.isEmpty());

        // force <toBeRemoved> being encapsulated as Iterable ...
        // noinspection FunctionalExpressionCanBeFolded
        final Iterable<Integer> iterable = toBeRemoved::iterator;
        final List<Integer> result = new Collector<Integer>(new LinkedList<>())
                .addAll(origin)
                .removeAll(iterable)
                .build(ArrayList::new);

        assertEquals(expected, result);
    }
}
