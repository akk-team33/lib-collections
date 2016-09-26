package net.team33.collections.tree;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

public class Sequence extends AbstractList<Node> implements Node {

    private final List<Node> backing;

    private Sequence(final Builder builder) {
        this.backing = unmodifiableList(new ArrayList<>(builder.backing));
    }

    public static Sequence of(final Node... nodes) {
        return builder().addAll(Stream.of(nodes)).build();
    }

    public static Sequence of(final Collection<? extends Node> nodes) {
        return builder().addAll(nodes).build();
    }

    public static <E> Builder builder() {
        return new Builder(Collections.emptyList());
    }

    @Override
    public final Node ifSequence(final Consumer<Sequence> consumer) {
        consumer.accept(this);
        return this;
    }

    @Override
    public final Node get(final int index) {
        return backing.get(index);
    }

    @Override
    public final int size() {
        return backing.size();
    }

    public static class Builder {
        private final List<Node> backing;

        private Builder(final Collection<? extends Node> template) {
            this.backing = new ArrayList<>(template);
        }

        public final Builder addAll(final Collection<? extends Node> nodes) {
            backing.addAll(nodes);
            return this;
        }

        public final Builder addAll(final Stream<? extends Node> nodes) {
            nodes.forEach(backing::add);
            return this;
        }

        public final Sequence build() {
            return new Sequence(this);
        }
    }
}
