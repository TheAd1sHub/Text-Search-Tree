package model.data.formatters;

import static org.junit.jupiter.api.Assertions.*;

import model.core.searchtree.SearchResultData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public final class SearchResultDataFormatterTest {

    private static SearchResultDataFormatter formatter;

    @BeforeAll
    public static void init() {
        formatter = new SearchResultDataFormatter();
    }


    @Test
    @DisplayName("Throws IllegalArgumentException with null parameter given to print()")
    public void testPrintNullParameterException() {
        assertThrows(IllegalArgumentException.class,
                () -> formatter.format(null)
        );
    }

    @Test
    @DisplayName("Throws IllegalArgumentException with null parameter given to printAll()")
    public void testMassPrintNullParameterException() {
        assertThrows(IllegalArgumentException.class,
                () -> formatter.formatAll(null)
        );
    }

    @Test
    @DisplayName("Correctly formats SearchResultData with print() method")
    public void testSingleSearchResultFormatting() {
        final SearchResultData data1 = new SearchResultData("Test", 13),
                               data2 = new SearchResultData("Testing", 72),
                               data3 = new SearchResultData("Tested", 1);

        final String expectedFormattingResult1 = "Found \"Test\" at #13;",
                     expectedFormattingResult2 = "Found \"Testing\" at #72;",
                     expectedFormattingResult3 = "Found \"Tested\" at #1;";

        assertEquals(expectedFormattingResult1, formatter.format(data1));
        assertEquals(expectedFormattingResult2, formatter.format(data2));
        assertEquals(expectedFormattingResult3, formatter.format(data3));
    }

    @Test
    @DisplayName("Correctly formats SearchResultData with printAll() method")
    public void testSingleSearchResultFormatting2() {
        final List<SearchResultData> dataSet1 = new ArrayList<>() {{
            add(new SearchResultData("One", 1));
            add(new SearchResultData("Two", 2));
            add(new SearchResultData("Three", 3));
        }};

        final Set<SearchResultData> dataSet2 = new LinkedHashSet<>(
                List.of(
                new SearchResultData("Whatever", 103),
                new SearchResultData("Foo", 98),
                new SearchResultData("Bar", 80)
                )
        );


        final String expectedFormattingResult1 = """
Found "One" at #1;
Found "Two" at #2;
Found "Three" at #3;
""",
                     expectedFormattingResult2 = """
Found "Whatever" at #103;
Found "Foo" at #98;
Found "Bar" at #80;
""";

        assertEquals(expectedFormattingResult1, formatter.formatAll(dataSet1));
        assertEquals(expectedFormattingResult2, formatter.formatAll(dataSet2));
    }

}
