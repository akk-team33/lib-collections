package net.team33.collections.tree.alt;

import java.util.Map;
import java.util.function.Consumer;

public interface NNode {

    static NNode from(Map<?, ?> map) {
        return null;
    }

    default NNode ifComposite(final Consumer<Composite> consumer) {
        return this;
    }
}
