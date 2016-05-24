package net.team33.collections;

/**
 * The basic interface of any iterable class or interface in or based on this library.
 * <p>
 * It is mainly just a {@link java.lang.Iterable} but the {@link #iterator()}-method returns an {@link Iterator},
 * that by definition NEVER supports the {@link Iterator#remove()}-method.
 * <p>
 * In particular, any instance of {@link Iterable} is IMMUTABLE - BY DEFINITION.
 */
public interface Iterable<E> extends java.lang.Iterable<E> {

    /**
     * Returns an {@link Iterator} over elements of type {@code E}.
     *
     * @return an Iterator.
     */
    @Override
    Iterator<E> iterator();
}
