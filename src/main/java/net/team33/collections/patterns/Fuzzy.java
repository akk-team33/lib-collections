package net.team33.collections.patterns;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Fuzzy {

    public static Fuzzy of(final Object subject) {
        return (null == subject) ? absent() : present(subject);
    }

    private static Fuzzy present(final Object subject) {
        return new Fuzzy() {
            @Override
            public Object getSubject() {
                return subject;
            }

            public <T, R> Conditional<T, R> when(final Class<T> assumed) {
                return assumed.isInstance(subject) ? accept(this, assumed.cast(subject)) : reject(this);
            }
        };
    }

    private static Fuzzy absent() {
        return new Fuzzy() {
            @Override
            public Object getSubject() {
                return null;
            }

            public <T, R> Conditional<T, R> when(final Class<T> assumed) {
                return (null == assumed) ? accept(this, null) : reject(this);
            }
        };
    }

    private static <R> Result<R> positive(final R result) {
        return new Result<R>() {
            @Override
            public Optional<R> optional() {
                return Optional.of(result);
            }

            @Override
            public <TX> Condition<TX, R> orWhen(final Class<TX> assumed) {
                return function -> this;
            }
        };
    }

    private static <T, R> Conditional<T, R> accept(final Fuzzy fuzzy, final T subject) {
        return new Conditional<T, R>() {
            @Override
            public Fuzzy then(final Consumer<T> consumer) {
                consumer.accept(subject);
                return fuzzy;
            }

            @Override
            public Result<R> reply(final Function<T, R> function) {
                return positive(function.apply(subject));
            }
        };
    }

    private static <R> Result<R> negative(final Fuzzy fuzzy) {
        return new Result<R>() {
            @Override
            public Optional<R> optional() {
                return Optional.empty();
            }

            @Override
            public <TX> Condition<TX, R> orWhen(final Class<TX> assumed) {
                return fuzzy.when(assumed);
            }
        };
    }

    private static <T, R> Conditional<T, R> reject(final Fuzzy fuzzy) {
        return new Conditional<T, R>() {
            @Override
            public Fuzzy then(final Consumer consumer) {
                return fuzzy;
            }

            @Override
            public Result<R> reply(final Function function) {
                return negative(fuzzy);
            }
        };
    }

    public abstract Object getSubject();

    public abstract <T, R> Conditional<T, R> when(final Class<T> assumed);

    @Override
    public final int hashCode() {
        return Objects.hashCode(getSubject());
    }

    @Override
    public final boolean equals(final Object obj) {
        return (this == obj) || ((obj instanceof Fuzzy) && internalEquals((Fuzzy) obj));
    }

    private boolean internalEquals(final Fuzzy other) {
        return Objects.equals(getSubject(), other.getSubject());
    }

    @Override
    public final String toString() {
        return new StringBuilder("<").append(getSubject()).append(">").toString();
    }

    public interface Conditional<T, R> extends Condition<T, R> {

        Fuzzy then(Consumer<T> consumer);
    }

    public interface Condition<T, R> {

        Result<R> reply(Function<T, R> function);
    }

    public interface Result<R> {

        Optional<R> optional();

        <T> Condition<T, R> orWhen(Class<T> assumed);

        default R orElse(final R fallback) {
            return optional().orElse(fallback);
        }

        default R orElseGet(final Supplier<R> supplier) {
            return optional().orElseGet(supplier);
        }

        default <X extends Throwable> R orThrow(final Supplier<X> supplier) throws X {
            return optional().orElseThrow(supplier);
        }
    }
}
