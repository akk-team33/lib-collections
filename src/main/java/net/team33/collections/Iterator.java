package net.team33.collections;

/**
 * The basic interface of any iterator class or interface in or based on this library.
 * <p>
 * It is mainly just a {@link java.util.Iterator} but the {@link #remove()}-method is specified to be not supported.
 * In particular to throw an {@link UnsupportedOperationException} in any case!
 */
public interface Iterator<E> extends java.util.Iterator<E> {

    /**
     * Proxies a common {@link java.util.Iterator} as an {@link Iterator} of this library.
     */
    static <E> Iterator<E> proxy(final java.util.Iterator<E> subject) {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return subject.hasNext();
            }

            @Override
            public E next() {
                return subject.next();
            }
        };
    }

    /**
     * As the default implementation in deed does, any implementation is REQUIRED (!) to throw an
     * {@link UnsupportedOperationException} in any case.
     * <p>
     * An implementation must not remove anything from anywhere at any time!
     *
     * @throws UnsupportedOperationException in any case.
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
