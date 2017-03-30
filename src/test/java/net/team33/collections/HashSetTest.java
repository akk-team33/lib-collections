package net.team33.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

public class HashSetTest {

    private static final String A_STRING = "this ist a string";

    private static Stream<String> aStream() {
        return Stream.of("abc", "def", "ghi", "def", "ghi", "abc", "jkl");
    }

    private static Stream<String> anotherStream() {
        return Stream.of("jkl", "ghi", "abc", "def", "def", "ghi", "abc");
    }

    @Test
    public final void add() {
        final HashSet<String> set = new HashSet<String>()
                .add(A_STRING)
                .add(A_STRING);
        Assert.assertEquals(1, set.size());
        Assert.assertEquals(true, set.contains(A_STRING));
    }

    @Test
    public final void addAll() {
        final HashSet<String> set = new HashSet<String>()
                .addAll(aStream());
        Assert.assertEquals(4, set.size());
        Assert.assertEquals(true, set.containsAll(anotherStream()));
    }

    @Test
    public final void remove() {
        final HashSet<String> set = new HashSet<String>()
                .add(A_STRING)
                .add(A_STRING)
                .remove(A_STRING);
        Assert.assertEquals(0, set.size());
        Assert.assertEquals(false, set.contains(A_STRING));
    }

    @Test
    public final void removeAll() {
        final HashSet<String> set = new HashSet<String>()
                .addAll(aStream())
                .removeAll(anotherStream());
        Assert.assertEquals(0, set.size());
        Assert.assertEquals(true, set.isEmpty());
    }
}