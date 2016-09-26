package net.team33.collections;

/**
 * An analogy to a {@link java.util.Collection} but designated to be IMMUTABLE.
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Returns the number of elements in this collection.
     */
    long size();

    /**
     * Returns {@code true} if this collection contains the specified {@code element}.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code ((element == null) ? (e == null) : element.equals(e))}.
     */
    boolean contains(Object element);

    /**
     * Returns {@code true} if this collection {@link #contains(Object)}
     * all of the elements in the other collection.
     */
    boolean containsAll(Iterable<?> elements);

    /**
     * Returns an {@link Iterator} over the elements in this collection.
     * <p>
     * There are no guarantees concerning the order in which the elements are returned,
     * unless this collection is an instance of some class that provides a guarantee.
     */
    Iterator<E> iterator();

    /**
     * Compares another object with this collection for equality.
     * <p>
     * While the {@code Collection} interface adds no stipulations to the general contract for the
     * {@code Object.equals}, programmers who implement the {@code Collection} interface "directly" (in other words,
     * create a class that is a {@code Collection} but is not a {@code Set} or a {@code List}) must exercise care if
     * they choose to override the {@code Object.equals}. It is not necessary to do so, and the simplest course of
     * action is to rely on {@code Object}'s implementation, but the implementor may wish to implement a
     * "value comparison" in place of the default "reference comparison." (The {@code List} and {@code Set} interfaces
     * mandate such value comparisons.)
     * <p>
     * The general contract for the {@code Object.equals} method states that equals must be symmetric (in other words,
     * {@code a.equals(b)} if and only if {@code b.equals(a)}).  The contracts for {@code List.equals} and
     * {@code Set.equals} state that lists are only equal to other lists, and sets to other sets. Thus, a custom
     * {@code equals} method for a collection class that implements neither the {@code List} nor {@code Set} interface
     * must return {@code false} when this collection is compared to any list or set.  (By the same logic, it is not
     * possible to write a class that correctly implements both the {@code Set} and {@code List} interfaces.)
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this collection. While the {@code Collection} interface adds no stipulations to
     * the general contract for the {@code Object.hashCode} method, programmers should take note that any class that
     * overrides the {@code Object.equals} method must also override the {@code Object.hashCode} method in order to
     * satisfy the general contract for the {@code Object.hashCode} method.
     * In particular, {@code c1.equals(c2)} implies that {@code c1.hashCode()==c2.hashCode()}.
     */
    int hashCode();

    Builder<E, ? extends Collection<E>, ? extends Builder<E, ?, ?>> rebuild();

    /**
     * A tool that is capable of building a {@link Collection}.
     */
    interface Builder<E, C extends Collection<E>, B extends Builder<E, C, B>> {

        /**
         * Builds and returns the intended {@code Collection}.
         */
        C build();

        /**
         * Ensures that the {@linkplain #build() resulting} collection contains the specified element
         * (unless it would be re-removed until {@linkplain #build() building}).
         * <p>
         * Implementations may place limitations on what elements may be added to the intended collection.
         * In particular, some builders will refuse to add {@code null} elements, and others will impose restrictions
         * on the type of elements that may be added. Implementation classes should clearly specify in their
         * documentation any restrictions on what elements may be added.
         * <p>
         * If a builder refuses to add a particular element for any reason other than that it already contains the
         * element, it <i>must</i> throw an exception. This preserves the invariant that a resulting collection always
         * will contain the specified element after this call returns.
         *
         * @return The builder itself.
         * @throws ClassCastException       if the class of the specified {@code element} prevents it from being
         *                                  added (may occur only if used raw or forced in a mismatched class context).
         * @throws NullPointerException     if the specified {@code element} is {@code null} and the {@code builder}
         *                                  or the intended {@code collection} does not permit {@code null} elements.
         * @throws IllegalArgumentException if some property of the {@code element} prevents it from being added to
         *                                  the {@code builder} or the intended {@code collection}.
         * @throws IllegalStateException    if the {@code element} cannot be added at this time due to the
         *                                  {@code builder}'s insertion restrictions (if any).
         */
        B add(E element);

        /**
         * Ensures that the {@linkplain #build() resulting} collection contains all of the specified elements
         * (unless they would be re-removed until {@linkplain #build() building}).
         *
         * @return The builder itself.
         * @throws ClassCastException       if the class of one of the specified {@code elements} prevents it from
         *                                  being added (may occur only if used raw or forced in a mismatched class
         *                                  context).
         * @throws NullPointerException     if one of the specified {@code elements} is {@code null} and the
         *                                  {@code builder} or the intended {@code collection} does not permit
         *                                  {@code null} elements.
         * @throws IllegalArgumentException if some property of one of the specified {@code elements} prevents it from
         *                                  being added to the {@code builder} or the intended {@code collection}.
         * @throws IllegalStateException    if the {@code element} cannot be added at this time due to the
         *                                  {@code builder}'s insertion restrictions (if any).
         * @throws IllegalStateException    if not all the elements can be added at
         *                                  this time due to insertion restrictions (if any).
         */
        B addAll(Iterable<? extends E> elements);

        /**
         * Ensures that the {@linkplain #build() resulting} collection does not contain the specified element
         * (unless it would be re-added until {@linkplain #build() building}).
         *
         * @return The builder itself.
         */
        B remove(Object element);

        /**
         * Ensures that the {@linkplain #build() resulting} collection does not contain any of the specified elements
         * (unless they would be re-added until {@linkplain #build() building}).
         *
         * @return The builder itself.
         */
        B removeAll(Iterable<?> elements);

        /**
         * Ensures that the {@linkplain #build() resulting} collection does not contain anything but those of the
         * specified elements, that are already contained (unless the others would be re-added until
         * {@linkplain #build() building}).
         *
         * @return The builder itself.
         */
        B retainAll(Iterable<?> elements);
    }
}

