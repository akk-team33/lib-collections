package de.team33.libs.collections.v3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collector<E> {

    private final Collection<E> backing;

    public Collector() {
        this(new LinkedList<>());
    }

    public Collector(final Collection<E> backing) {
        this.backing = backing;
    }

    public static <E> Collector<E> copy(final Collector<E> origin) {
        return new Collector<>(new ArrayList<>(origin.backing));
    }

    public final Collector<E> add(final E element) {
        backing.add(element);
        return this;
    }

    @SafeVarargs
    public final Collector<E> add(final E element0, final E element1, final E ... more) {
        return addAll(Stream.concat(Stream.of(element0, element1), Stream.of(more)));
    }

    public final Collector<E> addAll(final Stream<? extends E> elements) {
        elements.sequential().forEach(backing::add);
        return this;
    }

    public final Collector<E> addAll(final Collection<? extends E> elements) {
        backing.addAll(elements);
        return this;
    }

    public final Collector<E> addAll(final Iterable<? extends E> elements) {
        if (elements instanceof Collection<?>) {
            //noinspection unchecked
            backing.addAll((Collection<? extends E>) elements);
        } else {
            elements.forEach(backing::add);
        }
        return this;
    }

    private static void remove(final Collection<?> collection, final Object element) {
        // noinspection MethodCallInLoopCondition, StatementWithEmptyBody
        while (collection.remove(element));
    }

    public final Collector<E> remove(final Object element) {
        remove(backing, element);
        return this;
    }

    @SafeVarargs
    public final Collector<E> remove(final Object element0, final Object element1, final Object ... more) {
        return removeAll(Stream.concat(Stream.of(element0, element1), Stream.of(more)));
    }

    public final Collector<E> removeAll(final Stream<?> elements) {
        return removeAll(elements.collect(Collectors.toSet()));
    }

    public final Collector<E> removeAll(final Iterable<?> elements) {
        if (elements instanceof Collection<?>) {
            //noinspection SuspiciousMethodCalls
            backing.removeAll((Collection<?>) elements);
        } else {
            elements.forEach(element -> remove(backing, element));
        }
        return this;
    }

    @SafeVarargs
    public final Collector<E> retain(final E ... elements) {
        return retainAll(Stream.of(elements));
    }

    public final Collector<E> retainAll(final Stream<? extends E> elements) {
        return removeAll(copy(this).removeAll(elements).backing);
    }

    public final Collector<E> retainAll(final Iterable<?> elements) {
        return removeAll(copy(this).removeAll(elements).backing);
    }

    public final Collector<E> clear() {
        backing.clear();
        return this;
    }

    public final <R extends Collection<? super E>> R build(final Function<Collection<E>, R> newCollection) {
        return newCollection.apply(backing);
    }

    public final List<E> toList() {
        return build(ArrayList::new);
    }

    public final Set<E> toSet() {
        return build(HashSet::new);
    }

    public final SortedSet<E> toSortedSet() {
        return build(TreeSet::new);
    }
}
