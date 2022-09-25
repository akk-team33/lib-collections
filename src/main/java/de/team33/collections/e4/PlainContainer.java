package de.team33.collections.e4;

import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

class PlainContainer<E, C> implements Container<E> {

    private final C backing;
    private final Function<? super C, ? extends Stream<E>> streaming;
    private final ToIntFunction<? super C> sizing;

    PlainContainer(final C backing,
                   final Function<? super C, ? extends Stream<E>> streaming,
                   final ToIntFunction<? super C> sizing) {
        this.backing = backing;
        this.streaming = streaming;
        this.sizing = sizing;
    }

    @Override
    public final int size() {
        return sizing.applyAsInt(backing);
    }

    @Override
    public final Stream<E> stream() {
        return streaming.apply(backing);
    }
}
