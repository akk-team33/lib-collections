package net.team33.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class IteratorTest {

    public static final List<Integer> SOME_INTEGERS = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    public void noProxy() throws Exception {
        final List<?> sample = new ArrayList<>(SOME_INTEGERS);
        final java.util.Iterator<?> iterator = sample.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        iterator.remove();
        assertEquals(SOME_INTEGERS.subList(1, SOME_INTEGERS.size()), sample);
    }

    @Test
    public void proxy() throws Exception {
        final List<?> sample = new ArrayList<>(SOME_INTEGERS);
        final Iterator<?> iterator = Iterator.proxy(sample.iterator());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        try {
            iterator.remove();
            fail("remove didn't fail");
        } catch (final UnsupportedOperationException e) {
            assertEquals(SOME_INTEGERS, sample);
        }
    }
}