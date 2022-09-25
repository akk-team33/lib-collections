package de.team33.collections.e4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;

class Util {

    static <E> Collector<E, List<E>, List<E>> listCollector() {
        return Collector.of(() -> new LinkedList<E>(), List::add, Util::addAll, Collections::unmodifiableList);
    }

    private static <E> List<E> addAll(final List<E> left, final List<E> right) {
        left.addAll(right);
        return left;
    }

    static int sizeOf(final Streamable<?> streamable) {
        return (int) streamable.stream().limit(Integer.MAX_VALUE).count();
    }
}
