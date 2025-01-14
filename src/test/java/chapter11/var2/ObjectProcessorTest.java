package chapter11.var2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectProcessorTest {

    @Test
    void testProcessObjects_SortsAndRemovesDuplicates() {
        List<ObjectProcessor.ObjectInfo> input = List.of(
                new ObjectProcessor.ObjectInfo("Apple", 103),
                new ObjectProcessor.ObjectInfo("Banana", 101),
                new ObjectProcessor.ObjectInfo("Apple", 102),
                new ObjectProcessor.ObjectInfo("Cherry", 104),
                new ObjectProcessor.ObjectInfo("Banana", 105)
        );

        List<ObjectProcessor.ObjectInfo> result = ObjectProcessor.processObjects(input);

        List<ObjectProcessor.ObjectInfo> expected = List.of(
                new ObjectProcessor.ObjectInfo("Banana", 101),
                new ObjectProcessor.ObjectInfo("Apple", 102),
                new ObjectProcessor.ObjectInfo("Cherry", 104)
        );

        assertEquals(expected.size(), result.size(), "Result size should match expected size");
        assertIterableEquals(expected, result, "Result should match the expected output");
    }

    @Test
    void testProcessObjects_EmptyInput() {
        List<ObjectProcessor.ObjectInfo> input = List.of();

        List<ObjectProcessor.ObjectInfo> result = ObjectProcessor.processObjects(input);

        assertTrue(result.isEmpty(), "Result should be empty for empty input");
    }

    @Test
    void testProcessObjects_AllUnique() {
        List<ObjectProcessor.ObjectInfo> input = List.of(
                new ObjectProcessor.ObjectInfo("Apple", 103),
                new ObjectProcessor.ObjectInfo("Banana", 101),
                new ObjectProcessor.ObjectInfo("Cherry", 104)
        );

        List<ObjectProcessor.ObjectInfo> result = ObjectProcessor.processObjects(input);

        assertEquals(input.size(), result.size(), "All unique objects should be preserved");
        assertIterableEquals(input.stream()
                        .sorted((a, b) -> Integer.compare(a.getCode(), b.getCode()))
                        .toList(),
                result,
                "Result should be sorted by code");
    }
}
