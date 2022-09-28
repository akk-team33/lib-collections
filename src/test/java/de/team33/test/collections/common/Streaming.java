package de.team33.test.collections.common;

import com.google.common.collect.Streams;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Streaming {

    public static <L, R> Stream<Pair<L, R>> unite(final Stream<L> left, final Stream<R> right) {
        return Streams.zip(left, right, Pair::new);
        //return unite(left.iterator(), right.iterator());
        //return unite(left.spliterator(), right.spliterator());
    }

    private static <L, R> Stream<Pair<L, R>> unite(final Spliterator<L> left, final Spliterator<R> right) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    private static <L, R> Stream<Pair<L, R>> unite(final Iterator<L> left, final Iterator<R> right) {
        if (left.hasNext() || right.hasNext()) {
            final Stream<Pair<L, R>> head = head(left, right);
            final Stream<Pair<L, R>> tail = unite(left, right);
            return Stream.concat(head, tail);
        } else
            return Stream.empty();
    }

    private static <L, R> Stream<Pair<L, R>> head(final Iterator<L> left, final Iterator<R> right) {
        final Pair<L, R> result;
        if (left.hasNext()) {
            if (right.hasNext()) {
                result = new Pair<>(left.next(), right.next());
            } else {
                result = new Pair<>(left.next(), null);
            }
        } else {
            if (right.hasNext()) {
                result = new Pair<>(null, right.next());
            } else {
                throw new IllegalArgumentException("both arguments are empty");
            }
        }
        return Stream.of(result);
    }
}
