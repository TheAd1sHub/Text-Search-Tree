package model.data.parsers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class FileNameParserTest {

    private static FileNameParser parser;

    @BeforeAll
    public static void init() {
        parser = new FileNameParser();
    }

    @Test
    @DisplayName("No extension files extension parsing")
    public void testExtensionParsingOfFileWithNoExtension() {
        final String fileName1 = "NoExtensionFile",
                     fileName2 = "OneMoreFileWithoutExtension",
                     fileName3 = "BashScript";

        final String expectedParsingResult = "";

        final String parsedExtension1 = parser.getExtension(fileName1),
                     parsedExtension2 = parser.getExtension(fileName2),
                     parsedExtension3 = parser.getExtension(fileName3);

        assertEquals(expectedParsingResult, parsedExtension1);
        assertEquals(expectedParsingResult, parsedExtension2);
        assertEquals(expectedParsingResult, parsedExtension3);
    }

    @Test
    @DisplayName("No extension files name parsing")
    public void testNameParsingOfFileWithNoExtension() {
        final String fileName1 = "NoExtensionFile",
                     fileName2 = "OneMoreFileWithoutExtension",
                     fileName3 = "BashScript";

        final String parsedName1 = parser.getName(fileName1),
                     parsedName2 = parser.getName(fileName2),
                     parsedName3 = parser.getName(fileName3);

        assertEquals(fileName1, parsedName1);
        assertEquals(fileName2, parsedName2);
        assertEquals(fileName3, parsedName3);
    }

    @Test
    @DisplayName("Normal files extension parsing")
    public void testExtensionParsingOfNormalFile() {
        final String fileName1 = "test.file.txt",
                     fileName2 = "ILoveJava.cs",
                     fileName3 = "Remove System32.ps1";

        final String expectedParsingResult1 = ".txt",
                     expectedParsingResult2 = ".cs",
                     expectedParsingResult3 = ".ps1";

        final String parsedExtension1 = parser.getExtension(fileName1),
                     parsedExtension2 = parser.getExtension(fileName2),
                     parsedExtension3 = parser.getExtension(fileName3);

        assertEquals(expectedParsingResult1, parsedExtension1);
        assertEquals(expectedParsingResult2, parsedExtension2);
        assertEquals(expectedParsingResult3, parsedExtension3);
    }

    @Test
    @DisplayName("Normal files name parsing")
    public void testNameParsingOfNormalFile() {
        final String fileName1 = "test.file.txt",
                     fileName2 = "ILoveJava.cs",
                     fileName3 = "Remove System32.ps1";

        final String expectedParsingResult1 = "test.file",
                     expectedParsingResult2 = "ILoveJava",
                     expectedParsingResult3 = "Remove System32";

        final String parsedName1 = parser.getName(fileName1),
                     parsedName2 = parser.getName(fileName2),
                     parsedName3 = parser.getName(fileName3);

        assertEquals(expectedParsingResult1, parsedName1);
        assertEquals(expectedParsingResult2, parsedName2);
        assertEquals(expectedParsingResult3, parsedName3);
    }
}
