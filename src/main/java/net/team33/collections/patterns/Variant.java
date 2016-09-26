package net.team33.collections.patterns;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Variant {

    private final Object core;

    protected Variant(final Object core) {
        this.core = core;
    }

    public static Variant of(final Object core) {
        return new Variant(core);
    }

    private static <R> Supposed<R> actual(final R result) {
        return () -> Optional.of(result);
    }

    private static <R> Supposed<R> fictive(final Variant variant) {
        return new Supposed<R>() {
            @Override
            public <T> Supposed<R> orWhen(final Class<T> coreClass, final Function<T, R> function) {
                return variant.when(coreClass, function);
            }

            @Override
            public Supposed<R> orWhenNull(final Supplier<R> supplier) {
                return variant.whenNull(supplier);
            }

            @Override
            public Optional<R> optional() {
                return Optional.empty();
            }
        };
    }

    private static <T> Progress<T> accept(final Variant variant, final T core) {
        return consumer -> {
            consumer.accept(core);
            return variant;
        };
    }

    private static <T> Progress<T> reject(final Variant variant) {
        return consumer -> variant;
    }

    public final <T, R> Supposed<R> when(final Class<T> coreClass, final Function<T, R> function) {
        return coreClass.isInstance(core)
                ? actual(function.apply(coreClass.cast(core)))
                : fictive(this);
    }

    public final <R> Supposed<R> whenNull(final Supplier<R> supplier) {
        return (null == core)
                ? actual(supplier.get())
                : fictive(this);
    }

    public final <T> Progress<T> when(final Class<T> coreClass) {
        return coreClass.isInstance(core)
                ? accept(this, coreClass.cast(core))
                : reject(this);
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

    public interface Progress<T> {

        Variant then(Consumer<T> consumer);
    }

    public interface Supposed<R> {

        Optional<R> optional();

        default <T> Supposed<R> orWhen(final Class<T> coreClass, final Function<T, R> function) {
            return this;
        }

        default Supposed<R> orWhenNull(final Supplier<R> supplier) {
            return this;
        }

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
