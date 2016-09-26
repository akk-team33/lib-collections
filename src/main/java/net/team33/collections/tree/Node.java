package net.team33.collections.tree;

import java.util.function.Consumer;

public interface Node {

    default Node ifElement(final Consumer<Element> consumer) {
        return this;
    }

    default Node ifSequence(final Consumer<Sequence> consumer) {
        return this;
    }

    default Node ifStructure(final Consumer<Structure> consumer) {
        return this;
    }
}
