package net.team33.collections.patterns.alt;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Variant {

    private final Object core;

    private Variant(final Object core) {
        this.core = core;
    }

    public static Variant of(final Object core) {
        return new Variant(core);
    }

    private static <R> Consequence<R> positive(final R result) {
        return new Consequence<R>() {
            @Override
            public Optional<R> optional() {
                return Optional.of(result);
            }

            @Override
            public <T> Function<Function<T, R>, Consequence<R>> orWhen(final Class<T> condition) {
                return function -> this;
            }

            @Override
            public Function<Supplier<R>, Consequence<R>> orWhenNull() {
                return function -> this;
            }
        };
    }

    public final <T> Variant when(final Class<T> coreClass, final Consumer<T> consumer) {
        if (coreClass.isInstance(core)) {
            consumer.accept(coreClass.cast(core));
        }
        return this;
    }

    public final <T, R> Function<Function<T, R>, Consequence<R>> when(final Class<T> condition) {
        return function -> {
            if (condition.isInstance(core)) {
                return positive(function.apply(condition.cast(core)));
            } else {
                return negative(this);
            }
        };
    }

    public final <R> Function<Supplier<R>, Consequence<R>> whenNull() {
        return supplier -> {
            if (null == core) {
                return positive(supplier.get());
            } else {
                return negative(this);
            }
        };
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(core);
    }

    @Override
    public final boolean equals(final Object obj) {
        return (this == obj) || ((obj instanceof Variant) && internalEquals((Variant) obj));
    }

    private boolean internalEquals(final Variant other) {
        return Objects.equals(core, other.core);
    }

    @Override
    public final String toString() {
        return new StringBuilder("<").append(core).append(">").toString();
    }

    private <R> Consequence<R> negative(final Variant variant) {
        return new Consequence<R>() {
            @Override
            public Optional<R> optional() {
                return Optional.empty();
            }

            @Override
            public <T> Function<Function<T, R>, Consequence<R>> orWhen(final Class<T> condition) {
                return variant.when(condition);
            }

            @Override
            public Function<Supplier<R>, Consequence<R>> orWhenNull() {
                return variant.whenNull();
            }
        };
    }

    public interface Condition<T, R> {

        Consequence<R> reply(Function<T, R> function);
    }

    public interface Consequence<R> {

        Optional<R> optional();

        <T> Function<Function<T, R>, Consequence<R>> orWhen(Class<T> coreClass);

        Function<Supplier<R>, Consequence<R>> orWhenNull();

        default R orElse(final R result) {
            return optional().orElse(result);
        }

        default R orElseGet(final Supplier<R> supplier) {
            return optional().orElseGet(supplier);
        }

        default <X extends Throwable> R orThrow(final Supplier<X> supplier) throws X {
            return optional().orElseThrow(supplier);
        }
    }
}
