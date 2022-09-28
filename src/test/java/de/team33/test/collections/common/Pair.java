package de.team33.test.collections.common;

public class Pair<L, R> {

    private final L left;
    private final R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public final L getLeft() {
        return left;
    }

    public final R getRight() {
        return right;
    }
}
