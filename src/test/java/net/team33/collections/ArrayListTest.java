package net.team33.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

public class ArrayListTest {

    public static final String[] OTHER_STRINGS = {"jkl", "ghi", "abc", "def", "def", "ghi", "abc"};
    //private static final Random random = Random.builder().build();
    private static final String A_STRING;
    private static final String[] SOME_STRINGS = {"abc", "def", "ghi", "def", "ghi", "abc", "jkl"};

    static {
        A_STRING = "this ist a string";
    }

    @Test
    public final void add() {
        final ArrayList<String> list = new ArrayList<String>()
                .add(A_STRING)
                .add(A_STRING);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(true, list.contains(A_STRING));
    }

    @Test
    public final void addAll() {
        final ArrayList<String> list = new ArrayList<String>()
                .addAll(Stream.of(SOME_STRINGS));
        Assert.assertEquals(SOME_STRINGS.length, list.size());
        Assert.assertEquals(true, list.containsAll(Stream.of(OTHER_STRINGS)));
    }

    @Test
    public final void remove() {
        final ArrayList<String> list = new ArrayList<String>()
                .add(A_STRING)
                .add(A_STRING)
                .remove(A_STRING);
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(false, list.contains(A_STRING));
    }

    @Test
    public final void removeAll() {
        final ArrayList<String> list = new ArrayList<String>()
                .addAll(Stream.of(SOME_STRINGS))
                .removeAll(Stream.of(OTHER_STRINGS));
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(true, list.isEmpty());
    }

    @Test
    public final void clear() {
        final ArrayList<String> list = new ArrayList<String>()
                .addAll(Stream.of(SOME_STRINGS))
                .clear();
        Assert.assertEquals(0, list.size());
        Assert.assertEquals(true, list.isEmpty());
    }
}