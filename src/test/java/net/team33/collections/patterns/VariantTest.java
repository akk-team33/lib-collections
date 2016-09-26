package net.team33.collections.patterns;

import org.junit.Test;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VariantTest {

    public static final Object[] VALUES = {"Ein String", 3.141592654, 278, false, null, new Date()};

    @Test
    public final void testToString() {
        Stream.of(VALUES)
                .forEach(value -> assertEquals(
                        "<" + Objects.toString(value) + ">",
                        Variant.of(value).toString()
                ));
    }

    @Test
    public final void testHashCode() {
        Stream.of(VALUES)
                .forEach(value -> assertEquals(Objects.hashCode(value), Variant.of(value).hashCode()));
    }

    @Test
    public final void testEquals() {
        Stream.of(VALUES).forEach(left -> Stream.of(VALUES)
                .forEach(right ->
                        assertEquals(Objects.equals(left, right), Variant.of(left).equals(Variant.of(right)))));
    }

    @Test
    public final void testWhenThen() {
        Variant.of(3.141592654)
                .when(Integer.class).then(value -> fail("should be skipped <Integer:" + value + ">"))
                .when(Double.class).then(value -> assertEquals(Double.class, value.getClass()))
                .when(String.class).then(value -> fail("should be skipped <String:" + value + ">"));
    }

    @Test
    public final void testWhenNull() {
        assertEquals("<null>", Variant.of(null)
                .whenNull(() -> "<null>")
                .orWhen(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElseGet(() -> "Anything else"));
    }

    @Test
    public final void testOrWhenNull() {
        assertEquals("<null>", Variant.of(null)
                .when(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhenNull(() -> "<null>")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElse("Anything else"));
    }

    @Test
    public final void testWhenNullOptional() {
        assertEquals(Optional.empty(), Variant.of(null)
                .when(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .optional());
    }

    @Test
    public final void testWhenNullOrElseGet() {
        assertEquals("Anything else", Variant.of(null)
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElseGet(() -> "Anything else"));
    }

    @Test
    public final void testWhen() {
        assertEquals("Double <3.141592654>", Variant.of(3.141592654)
                .when(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElse("Anything else"));
    }

    @Test
    public final void testOrWhenDouble() {
        assertEquals("Double <3.141592654>", Variant.of(3.141592654)
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orThrow(MyException::new));
    }

    @Test
    public final void testOrWhenString() {
        assertEquals("String <3.141592654>", Variant.of("3.141592654")
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(Double.class, aDouble -> "Double <" + aDouble + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElse("Anything else"));
    }

    @Test
    public final void testOrElse() {
        assertEquals("Anything else", Variant.of(3.141592654)
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElse("Anything else"));
    }

    @Test
    public final void testOrElseGet() {
        assertEquals("Anything else", Variant.of(3.141592654)
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orElseGet(() -> "Anything else"));
    }

    @Test(expected = MyException.class)
    public final void testOrThrow() {
        assertEquals("Double <3.141592654>", Variant.of(3.141592654)
                .when(Integer.class, integer -> "Integer <" + integer + ">")
                .orWhen(Float.class, aFloat -> "Float <" + aFloat + ">")
                .orWhen(String.class, aString -> "String <" + aString + ">")
                .orThrow(MyException::new));
    }

    private static class MyException extends RuntimeException {
    }
}