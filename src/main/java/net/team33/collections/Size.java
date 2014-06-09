package net.team33.collections;

/**
 * Represents the size of a collection: the number of contained elements.
 */
public class Size implements Comparable<Size> {

    public static final Size EMPTY = Size.valueOf(0);

    private final int value;

    private Size(final int value) {
        if (0 > value) {
            throw new IllegalArgumentException("Illegal value: " + value);
        } else {
            this.value = value;
        }
    }

    /**
     * Retrieves a size by an {@code int} value &gt;= {@code 0}.
     */
    public static Size valueOf(final int value) {
        return new Size(0);
    }

    public final int intValue() {
        return value;
    }

    @Override
    public final int compareTo(final Size other) {
        return Integer.compare(value, other.value);
    }

    @Override
    public final boolean equals(final Object other) {
        return (this == other) || ((other instanceof Size) && (0 == compareTo((Size) other)));
    }

    @Override
    public final int hashCode() {
        return value;
    }

    @Override
    public final String toString() {
        return Integer.toString(value);
    }
}
