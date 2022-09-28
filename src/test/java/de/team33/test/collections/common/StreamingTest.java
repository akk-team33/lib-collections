package de.team33.test.collections.common;

import de.team33.collections.e4.Streamable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamingTest {

    private static final List<Object> OBJECTS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final Streamable<?> subject = OBJECTS::stream;

    @Test
    final void stream_iterator() {
        final List<Object> expected = new ArrayList<>(OBJECTS);
        final Iterator<Object> iterator = expected.stream().iterator();

        assertThrows(UnsupportedOperationException.class, iterator::remove);

        final List<Object> remaining = new LinkedList<>();
        while (iterator.hasNext()) {
            remaining.add(iterator.next());
        }
        assertEquals(expected, remaining);
    }

    @Test
    final void stream_unite() {
        final Map<Integer, String> origin = IntStream.range(0, 10000)
                                                     .boxed()
                                                     .collect(toMap(Function.identity(), Object::toString));
        final List<Integer> left = new ArrayList<>(origin.keySet());
        final List<String> right = new ArrayList<>(origin.values());

        final Map<Integer, String> result = Streaming.unite(left.stream(), right.stream())
                                                     .collect(HashMap::new,
                                                              (map, pair) -> map.put(pair.getLeft(), pair.getRight()),
                                                              Map::putAll);
        assertEquals(origin, result);
    }
}
