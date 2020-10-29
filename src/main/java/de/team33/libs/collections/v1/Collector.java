package de.team33.libs.collections.v1;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.singleton;
import static java.util.Objects.requireNonNull;

/**
 * A tool class that is used to manipulate collections in a builder style.
 *
 * @param <E> The supported element type
 * @param <C> The supported type of {@link Collection}
 */
public class Collector<E, C extends Collection<E>> {

    private final C subject;

    public Collector(final C subject) throws NullPointerException {
        this.subject = requireNonNull(subject);
    }

    public static <E, C extends Collection<E>> Collector<E, C> on(final C subject) throws NullPointerException {
        return new Collector<>(subject);
    }

    /**
     * @see Collection#add(Object)
     */
    public final Collector<E, C> add(final E element) {
        subject.add(element);
        return this;
    }

    /**
     * @see Collection#addAll(Collection)
     */
    @SafeVarargs
    public final Collector<E, C> add(final E element, final E ... more) {
        return add(element).addAll(Arrays.asList(more));
    }

    /**
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> addAll(final Collection<? extends E> elements) {
        subject.addAll(elements);
        return this;
    }

    /**
     * Adds all
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> addAll(final Stream<? extends E> elements) {
        elements.sequential()
                .forEach(subject::add);
        return this;
    }

    /**
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> addAll(final Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            // addAll(Collection) may be more efficient than add(E) for each element
            // noinspection unchecked
            return addAll((Collection<? extends E>)elements);
        }
        elements.forEach(subject::add);
        return this;
    }

    /**
     * @see Collection#remove(Object)
     */
    public final Collector<E, C> remove(final Object element) {
        try {
            subject.removeAll(singleton(element));
        } catch (final NullPointerException | ClassCastException ignored) {
            // --> <subject> can not contain <element>
            // --> <subject> simply does not contain <element>
            // --> Nothing to do!
        }
        return this;
    }

    /**
     * @see Collection#removeAll(Collection)
     */
    public final Collector<E, C> removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#removeIf(Predicate)
     */
    public final Collector<E, C> removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#retainAll(Collection)
     */
    public final Collector<E, C> retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * @see Collection#addAll(Collection)
     */
    public final Collector<E, C> clear() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public final C getSubject() {
        return subject;
    }
}
