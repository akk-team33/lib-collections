package net.team33.collections.tree;

import net.team33.collections.patterns.Mutable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NodeTest {

    private static final String NOT_EXPECTED_ELEMENT = "<%s> is not expected to be an Element";
    private static final String NOT_EXPECTED_SEQUENCE = "<%s> is not expected to be a Sequence";
    private static final String NOT_EXPECTED_COMPOSITION = "<%s> is not expected to be a Composition";
    private static final List<Node> DISCRETE = Collections.singletonList(Element.of(new Object()));
    private static final String UNSPECIFIED = "unspecified";


    @Test
    public final void test() {
        final Map<String, Object> map = new TreeMap<String, Object>() {{
            put("integer", 278);
            put("string", "wdlprmpf");
            put("list", new ArrayList<Object>() {
                {
                    add(279);
                    add("opösfg");
                    add(new TreeMap<String, Object>() {{
                        put("integer", 278);
                        put("string", "wdlprmpf");
                        put("list1", new ArrayList<Object>() {{
                            add(279);
                            add("opösfg");
                            add(new ArrayList<>());
                        }});
                    }});
                }
            });
            put("map", new TreeMap<String, Object>() {{
                put("integer", 278);
                put("string", "wdlprmpf");
                put("list", new ArrayList<Object>() {
                    {
                        add(280);
                        add("ztrmbvm");
                        add(new TreeMap<String, Object>() {{
                            put("integer", 277);
                            put("string", "ljhclk");
                            put("list1", new ArrayList<Object>() {{
                                add(276);
                                add("kmjhgiuz");
                                add(new ArrayList<>());
                            }});
                        }});
                    }
                });
            }});
        }};
//        Structure.parse ...
//        assertEquals(UNSPECIFIED, subject);
    }

    @Test
    public final void nothingReal() {
        final String subject = Mutable.of(UNSPECIFIED)
                .offer(mutable -> new Node() {
                }
                        .ifElement(node -> mutable.set("Element <" + node + ">"))
                        .ifSequence(node -> mutable.set("Sequence <" + node + ">"))
                        .ifStructure(node -> mutable.set("Composition <" + node + ">")))
                .get();
        assertEquals(UNSPECIFIED, subject);
    }

    @Test
    public final void emptySequence() {
        final List<Node> subject = Mutable.of(DISCRETE)
                .offer(mutable -> Sequence.of()
                        .ifElement(sequence -> fail(format(NOT_EXPECTED_ELEMENT, sequence)))
                        .ifStructure(sequence -> fail(format(NOT_EXPECTED_COMPOSITION, sequence)))
                        .ifSequence(sequence -> mutable.set(new ArrayList<>(sequence))))
                .get();
        assertEquals(Collections.emptyList(), subject);
    }
}