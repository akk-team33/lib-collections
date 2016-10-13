package net.team33.collections.tree;

import net.team33.collections.patterns.Fuzzy;

public class Element extends Fuzzy implements Node {

    private final Fuzzy backing;

    public Element(final Fuzzy backing) {
        this.backing = backing;
    }

    public static Element of(final Object core) {
        return new Element(Fuzzy.of(core));
    }

    @Override
    public final Object getSubject() {
        return backing.getSubject();
    }

    @Override
    public final <T, R> Conditional<T, R> when(final Class<T> assumed) {
        return backing.when(assumed);
    }
}
