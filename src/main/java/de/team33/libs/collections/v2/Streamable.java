package de.team33.libs.collections.v2;

import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Streamable<E> {

    Stream<E> stream();

    default void forEach(final Consumer<? super E> action) {
        stream().forEach(action);
    }
}
