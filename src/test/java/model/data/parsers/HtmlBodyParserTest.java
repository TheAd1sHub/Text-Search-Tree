package model.data.parsers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class HtmlBodyParserTest {

    private static HtmlBodyParser parser;

    @BeforeAll
    public static void init() {
        parser = new HtmlBodyParser();
    }


    @Test
    @DisplayName("Html page contents parsing")
    public void testHtmlPageContentsParsing() {
        final String html = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
   <h1>Test text</h1>
   <p>Hello world!</p>
</body>
</html>
""";

        final String expectedParsingResult = "Test text Hello world!";

        final String parsingResult = parser.getContents(html);

        assertEquals(expectedParsingResult, parsingResult);

    }
}
