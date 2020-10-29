package de.team33.test.collections.v1;

import de.team33.libs.collections.v1.Collector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CollectorTest {

    private final Random random = new Random();
    private final List<Integer> template;

    public CollectorTest() {
        final int size = 8 + random.nextInt(8);
        this.template = new ArrayList<>(size);
        Stream.generate(random::nextInt)
              .limit(size)
              .forEach(template::add);
    }

    /**
     * Ensures that {@link Collector#getSubject()} returns the {@link Collector#on(Collection) originally associated}
     * instance.
     */
    @Test
    public final void getSubject() {
        // Preconditions ...
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        final Collection<Integer> initial = new HashSet<>(template);
        final Collection<Integer> original = new HashSet<>(initial);
        // the original must be equivalent to the initial value ...
        assertEquals(initial, original);
        // ... but of course not identical ...
        assertNotSame(initial, original);
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // Even if the associated instance has been changed, ...
        final Collection<Integer> result = Collector.on(original)
                                                    .add(random.nextInt())
                                                    .remove(template.get(5))
                                                    .getSubject();
        // ... the result must be identical to the original ...
        assertSame(original, result);
        // ... but of course not equivalent to the initial value.
        assertNotEquals(initial, result);
    }

    /**
     * Ensures that {@link Collector#add(Object)} has the same effect as {@link Collection#add(Object)}.
     */
    @Test
    public final void add() {
        final LinkedList<Object> result = Collector.on(new LinkedList<>())
                                                   .add(template.get(0))
                                                   .add(template.get(1), template.get(2), template.get(3))
                                                   .add(template.get(4))
                                                   .add(template.get(5))
                                                   .getSubject();
        assertEquals(template.subList(0, 6), result);
    }

    /**
     * Ensures that {@link Collector#addAll(Stream)} has the same effect as {@link Collection#add(Object)}
     * for each element from a given stream.
     */
    @SuppressWarnings({"SimplifyStreamApiCallChains", "UseBulkOperation"})
    @Test
    public final void addAllStream() {
        final List<Integer> expected = new LinkedList<Integer>();
        template.stream()
                .forEach(expected::add);

        final List<Number> result = Collector.on(new LinkedList<Number>())
                                             .addAll(template.stream())
                                             .getSubject();
        assertEquals(expected, result);
    }

    /**
     * Ensures that {@link Collector#remove(Object) remove(null)} does not result in a {@link NullPointerException},
     * even if the {@link Collector#on(Collection) originally associated} instance cannot contain {@code null} elements.
     */
    @Test
    public final void removeNull() {
        final Collection<Integer> initial = new HashSet<>(asList(1, 2, 3));
        final Collection<Integer> subject = new TreeSet<>(initial);
        try {
            subject.remove(null);
            fail("subject.remove(null) should result in a NullPointerException");
        } catch (final NullPointerException ignored) {
            // is expected;
        }

        final Collection<Integer> result = Collector.on(subject)
                                                    .remove(null)
                                                    .getSubject();
        // should work - but do nothing ...
        assertEquals(initial, result);
    }

    /**
     * Ensures that {@link Collector#remove(Object) remove(any)} does not result in a {@link ClassCastException},
     * even if the {@link Collector#on(Collection) originally associated} instance cannot contain {@code any} element.
     */
    @Test
    public final void removeAny() {
        final Collection<Integer> initial = new HashSet<>(asList(1, 2, 3));
        final Collection<Integer> subject = new TreeSet<>(initial);
        try {
            subject.remove("a string");
            fail("subject.remove(\"a string\") should result in a ClassCastException");
        } catch (final ClassCastException ignored) {
            // is expected;
        }

        final Collection<Integer> result = Collector.on(subject)
                                                    .remove("a string")
                                                    .getSubject();
        // should work - but do nothing ...
        assertEquals(initial, result);
    }

    @Test
    public final void remove() {
        final Collection<Integer> subject = new ArrayList<>(asList(1, 1, 1, 1, 1, 1, 1));
        assertEquals(7, subject.size());
        assertTrue(subject.contains(1));

        // Should remove only one (the first?) element of value <1> ...
        subject.remove(1);
        assertEquals(6, subject.size());
        assertTrue(subject.contains(1));

        // Must remove all remaining elements of value <1> ...
        Collector.on(subject)
                 .remove(1);
        assertEquals(0, subject.size());
        assertFalse(subject.contains(1));
    }

    @Test
    public final void test() {
        fail("not yet implemented");
    }
}