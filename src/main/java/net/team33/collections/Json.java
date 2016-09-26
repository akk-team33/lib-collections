package net.team33.collections;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface Json {

    public abstract Json ifBoolean(Consumer<Boolean> consumer);

    public abstract Json ifNumber(Consumer<BigDecimal> consumer);

    public abstract Json ifString(Consumer<String> consumer);

    public abstract Json ifArray(Consumer<List<Json>> consumer);

    public abstract Json ifMap(Consumer<Map<String, Json>> consumer);

    public abstract Json ifNull(Runnable runnable);

    public static interface Builder {
    }
}
