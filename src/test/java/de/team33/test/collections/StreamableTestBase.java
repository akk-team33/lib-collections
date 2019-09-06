package de.team33.test.collections;

import de.team33.libs.collections.Streamable;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public abstract class StreamableTestBase {

    protected abstract <E> Streamable<E> newSubject(Stream<E> origin);

    @Test
    public void stream() {
        final Random random = new Random();
        final Supplier<Integer> nextInt = () -> random.nextInt(100);
        Stream.concat(Stream.of(0, 1), Stream.generate(nextInt)).distinct().limit(20).forEach(size -> {
            final Set<Long> origin = Stream.generate(random::nextLong).distinct().limit(size).collect(toSet());
            final Streamable<?> subject = newSubject(origin.stream());
            final Stream<?> stream = subject.stream();
            assertNotNull("Streamable.stream() must not return <null>", stream);
            assertFalse("Streamable.stream() must not return a parallel stream!", stream.isParallel());
            final Set<?> result = stream.collect(toSet());
            assertEquals("Streamable.stream() must reflect all its original content", origin, result);
        });
    }

    @Test
    public void forEach() {
        new Operation().run(op -> {
            final Set<Object> result = new HashSet<>();
            op.subject.forEach(result::add);
            assertEquals("Streamable.forEach() must reflect all the subject's original content", op.origin, result);
        });
    }

    private class Operation {

        private final Random random = new Random();
        private final Supplier<Integer> nextInt = () -> random.nextInt(100);

        private Set<Long> origin;
        private Streamable<Long> subject;

        private void run(Consumer<Operation> consumer) {
            Stream.concat(Stream.of(0, 1), Stream.generate(nextInt)).distinct().limit(20).forEach(size -> {
                this.origin = Stream.generate(random::nextLong).distinct().limit(size).collect(toSet());
                this.subject = newSubject(origin.stream());
                consumer.accept(this);
            });
        }
    }
}
