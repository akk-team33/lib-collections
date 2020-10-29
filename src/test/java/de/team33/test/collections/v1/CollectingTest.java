package de.team33.test.collections.v1;

import de.team33.libs.collections.v1.Collecting;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
public class CollectingTest {

    public static final String STRAIGHT_NOT_FAILED = "straight call did not fail -> test is not significant";
    public static final String NULL = null;
    public static final int NOT_A_STRING = 278;

    @SuppressWarnings("MultipleVariablesInDeclaration")
    private String sample1, sample2, sample3;
    @SuppressWarnings("MultipleVariablesInDeclaration")
    private List<String> samples, duplicated, samplesAndNull;
    private List<Object> samplesAndIncompatibleType;

    @Before
    public final void before() {
        sample1 = UUID.randomUUID().toString();
        sample2 = UUID.randomUUID().toString();
        sample3 = UUID.randomUUID().toString();
        samples = unmodifiableList(asList(sample1, sample2, sample3));
        samplesAndNull = asList(sample1, NULL, sample2, NULL, sample3);
        samplesAndIncompatibleType = asList(sample1, sample2, NOT_A_STRING, sample3);
        duplicated = unmodifiableList(asList(sample1, sample2, sample3, sample1, sample3, sample2));
    }

    @Test
    public final void addSingle() {
        assertTrue(Collecting.add(new TreeSet<>(), sample1).contains(sample1));
    }

    @Test(expected = NullPointerException.class)
    public final void addNullSingle() {
        fail("should fail but was " + Collecting.add(null, sample1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void addSingleUnsupported() {
        fail("should fail but was " + Collecting.add(unmodifiableSet(new TreeSet<>()), sample1));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = ClassCastException.class)
    public final void addSingleClassCast() {
        final Set integers = new TreeSet<>(Integer::compareTo);
        fail("should fail but was " + Collecting.add(integers, sample1));
    }

    @Test(expected = NullPointerException.class)
    public final void addSingleNull() {
        assertTrue("should work", Collecting.add(new ArrayList<>(), NULL).contains(NULL));
        fail("should fail but was " + Collecting.add(new TreeSet<>(), NULL));
    }

    @Test
    public final void addArray() {
        assertTrue(Collecting.add(new TreeSet<>(), sample1, sample2, sample3).containsAll(samples));
    }

    @Test
    public final void addAll() {
        assertTrue(Collecting.addAll(new TreeSet<>(), samples).containsAll(samples));
    }

    @Test
    public final void clear() {
        assertTrue(Collecting.clear(new ArrayList<>(samples)).isEmpty());
    }

    @Test
    public final void remove() {
        assertFalse(Collecting.remove(new LinkedList<>(duplicated), sample2).contains(sample2));
    }

    @Test
    public final void removeNull() {
        final TreeSet<String> subject = new TreeSet<>(samples);
        try {
            subject.remove(null);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final NullPointerException ignored) {
            assertFalse(
                    Collecting.contains(
                            Collecting.remove(subject, NULL),
                            NULL
                    )
            );
        }
    }

    @Test
    public final void removeIncompatibleType() {
        final TreeSet<String> subject = new TreeSet<>(samples);
        try {
            //noinspection SuspiciousMethodCalls
            subject.remove(NOT_A_STRING);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final ClassCastException ignored) {
            assertFalse(
                    Collecting.contains(
                            Collecting.remove(subject, NOT_A_STRING),
                            NOT_A_STRING
                    )
            );
        }
    }

    @Test
    public final void removeArray() {
        assertTrue(Collecting.remove(new ArrayList<>(duplicated), sample1, sample3, sample2).isEmpty());
    }

    @Test
    public final void testRemoveAll() {
        assertTrue(Collecting.removeAll(new ArrayList<>(duplicated), samples).isEmpty());
    }

    @Test
    public final void removeAllNull() {
        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            new ArrayList<>(samplesAndNull).removeAll(treeSet);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final NullPointerException ignored) {
            assertEquals(
                    asList(NULL, NULL),
                    Collecting.removeAll(
                            new ArrayList<>(samplesAndNull),
                            treeSet)
            );
        }
    }

    @Test
    public final void removeAllIncompatibleType() {
        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            new ArrayList<>(samplesAndIncompatibleType).removeAll(treeSet);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final ClassCastException ignored) {
            assertEquals(
                    singletonList(NOT_A_STRING),
                    Collecting.removeAll(
                            new ArrayList<>(samplesAndIncompatibleType),
                            treeSet)
            );
        }
    }

    @Test
    public final void retainArray() {
        assertEquals(
                asList(sample1, sample2, sample1, sample2),
                Collecting.retain(new ArrayList<>(duplicated), sample1, sample2));
    }

    @Test
    public final void retainAll() {
        assertEquals(
                asList(sample1, sample1),
                Collecting.retainAll(new ArrayList<>(duplicated), singleton(sample1)));
    }

    @Test
    public final void retainAllNull() {
        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            new ArrayList<>(samplesAndNull).retainAll(treeSet);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final NullPointerException ignored) {
            assertEquals(
                    samples,
                    Collecting.retainAll(
                            new ArrayList<>(samplesAndNull),
                            treeSet)
            );
        }
    }

    @Test
    public final void retainAllIncompatibleType() {
        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            new ArrayList<>(samplesAndIncompatibleType).retainAll(treeSet);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final ClassCastException ignored) {
            assertEquals(
                    samples,
                    Collecting.retainAll(
                            new ArrayList<>(samplesAndIncompatibleType),
                            treeSet)
            );
        }
    }

    @Test
    public final void contains() {
        assertTrue(Collecting.contains(samples, sample1));
        assertTrue(Collecting.contains(samples, sample2));
        assertTrue(Collecting.contains(samples, sample3));

        assertFalse(Collecting.contains(samples, UUID.randomUUID().toString()));
        assertFalse(Collecting.contains(samples, NOT_A_STRING));
        assertFalse(Collecting.contains(samples, NULL));

        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            treeSet.contains(NULL);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final NullPointerException ignored) {
            assertFalse(Collecting.contains(treeSet, NULL));
        }
        try {
            //noinspection SuspiciousMethodCalls
            treeSet.contains(NOT_A_STRING);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final ClassCastException ignored) {
            assertFalse(Collecting.contains(treeSet, NOT_A_STRING));
        }
    }

    @Test
    public final void containsArray() {
        assertTrue(Collecting.contains(samples, sample1, sample2, sample3));
        assertFalse(Collecting.contains(samples, UUID.randomUUID().toString(), NOT_A_STRING, NULL));
    }

    @Test
    public final void testContainsAll() {
        assertTrue(Collecting.containsAll(samplesAndNull, samples));
        assertFalse(Collecting.containsAll(samples, samplesAndIncompatibleType));

        final Collection<String> treeSet = new TreeSet<>(samples);
        try {
            treeSet.containsAll(samplesAndNull);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final NullPointerException ignored) {
            assertFalse(Collecting.containsAll(treeSet, samplesAndNull));
        }
        try {
            treeSet.containsAll(samplesAndIncompatibleType);
            fail(STRAIGHT_NOT_FAILED);
        } catch (final ClassCastException ignored) {
            assertFalse(Collecting.contains(treeSet, samplesAndIncompatibleType));
        }
    }
}