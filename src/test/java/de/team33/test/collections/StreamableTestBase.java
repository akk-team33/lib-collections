package de.team33.test.collections;

import de.team33.libs.collections.Streamable;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public abstract class StreamableTestBase {

    protected abstract <E> Streamable<E> newSubject(Stream<E> origin);

    @Test
    public void stream() {
        new Series(25).forEach(step -> {
            final Stream<?> stream = step.subject.stream();
            assertNotNull("Streamable.stream() must not return <null>", stream);
            assertFalse("Streamable.stream() must not return a parallel stream!", stream.isParallel());
            final Set<?> result = stream.collect(toSet());
            assertEquals("Streamable.stream() must reflect all its original content", step.origin, result);
        });
    }

    @Test
    public void forEach() {
        new Series(30).forEach(step -> {
            final Set<Object> result = new HashSet<>();
            step.subject.forEach(result::add);
            assertEquals("Streamable.forEach() must reflect all the subject's original content", step.origin, result);
        });
    }

    class Series {

        private final Random random = new Random();
        private final Supplier<Integer> nextInt = () -> random.nextInt(1000);
        private final int limit;

        Series(final int limit) {
            this.limit = limit;
        }

        void forEach(Consumer<Step> consumer) {
            // For 20 different values definitely including 0 and 1 ...
            Stream.concat(Stream.of(0, 1), Stream.generate(nextInt))
                    .distinct().limit(limit).map(Step::new)
                    .forEach(consumer);
        }

        class Step {

            final Set<Long> origin;
            final Streamable<Long> subject;

            private Step(final int size) {
                this.origin = Stream.generate(random::nextLong).distinct().limit(size).collect(toSet());
                this.subject = newSubject(origin.stream());
            }
        }
    }
}
