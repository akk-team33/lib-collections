package net.team33.collections.tree.alt;

import com.google.gson.Gson;
import net.team33.collections.patterns.Mutable;
import net.team33.collections.tree.Element;
import net.team33.collections.tree.Node;
import net.team33.collections.tree.Sequence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NodeTest {

    private static final String NOT_EXPECTED_ELEMENT = "<%s> is not expected to be an Element";
    private static final String NOT_EXPECTED_SEQUENCE = "<%s> is not expected to be a Sequence";
    private static final String NOT_EXPECTED_COMPOSITION = "<%s> is not expected to be a Composition";
    private static final List<Node> DISCRETE = Collections.singletonList(Element.of(new Object()));
    private static final String UNSPECIFIED = "unspecified";

    private static final Map MAP = new Gson().fromJson("{" +
            "    'boolean' : false," +
            "    'integer' : 278," +
            "    'decimal' : 3.141592654," +
            "    'string' : 'a string'," +
            "    'array' : [" +
            "        false," +
            "        279," +
            "        4.141592654," +
            "        'a string in an array'," +
            "        ['a string in an array in an array']" +
            "        {" +
            "            'string' : 'a string in a structure in an array'," +
            "        }" +
            "    ]," +
            "    'structure' : {" +
            "        'boolean' : true," +
            "        'integer' : 280," +
            "        'decimal' : 5.141592654," +
            "        'string' : 'a string in a structure'," +
            "        'array' : ['a string in an array in a structure']" +
            "        'structure' : {" +
            "            'string' : 'a string in a structure in a structure'," +
            "        }" +
            "    }" +
            "}", Map.class);


    @Test
    public final void test() {
        final NNode node = NNode.from(MAP);
        node.ifComposite(composite -> {
            assertEquals(MAP, composite.toMap());
        });
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