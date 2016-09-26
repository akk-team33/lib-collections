package net.team33.collections.tree;

import net.team33.collections.patterns.Variant;

import java.util.function.Consumer;

public class Element extends Variant implements Node {

    private Element(final Object core) {
        super(core);
    }

    public static Element of(final Object core) {
        return new Element(core);
    }

    @Override
    public final Node ifElement(final Consumer<Element> consumer) {
        consumer.accept(this);
        return this;
    }
}
