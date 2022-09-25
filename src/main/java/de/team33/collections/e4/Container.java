package de.team33.collections.e4;


import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * An abstraction of {@link Streamable} types that (actually) contain a specific number of elements.
 * <p>
 * Such a container can contain at most {@link Integer#MAX_VALUE} elements.
 *
 * @param <E> The element type.
 */
public interface Container<E> extends Streamable<E> {

    static <E> Container<E> of(final Streamable<E> streamable) {
        return of(streamable, Streamable::stream, Util::sizeOf);
    }

    static <E, C extends Streamable<E>> Container<E> of(final C container, final ToIntFunction<C> sizing) {
        return of(container, Streamable::stream, sizing);
    }

    static <E, C> Container<E> of(final C container,
                                  final Function<? super C, ? extends Stream<E>> streaming,
                                  final ToIntFunction<C> sizing) {
        return new PlainContainer<>(container, streaming, sizing);
    }

    /**
     * Returns the number of elements in this container.
     */
    int size();
}
