package model.data.parsers;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public final class TextParserTest {

    @Test
    @DisplayName("No results parsing in empty string")
    public void testNoResultsParsingInEmptyText() {
        final String emptyText = "";
        final List<String> emptyWordsFoundList = new ArrayList<>();

        final List<String> emptyTextSearchResult = TextParser.getWords(emptyText);

        assertEquals(emptyWordsFoundList, emptyTextSearchResult);
    }

    @Test
    @DisplayName("No results parsing in string with no words")
    public void testNoResultsParsingInSymbolOnlyText() {
        final String symbolOnlyText = "$%@#*!@#(!#&(!#(!$&(!$&";
        final List<String> emptyWordsFoundList = new ArrayList<>();

        final List<String> symbolsTextSearchResult = TextParser.getWords(symbolOnlyText);

        assertEquals(emptyWordsFoundList, symbolsTextSearchResult);
    }

    @Test
    @DisplayName("Parsing of English text #1")
    public void testEnglishTextParsing() {
        final String englishText = """
Silent night, holy night
All is calm, all is bright
Round yon Virgin, Mother and Child
Holy Infant so tender and mild
Sleep in heavenly peace
Sleep in heavenly peace
""";
        final List<String> expectedParsingResults = List.of(
                "Silent", "night", "holy", "night",
                "All", "is", "calm", "all", "is", "bright",
                "Round", "yon", "Virgin", "Mother", "and", "Child",
                "Holy", "Infant", "so", "tender", "and", "mild",
                "Sleep", "in", "heavenly", "peace",
                "Sleep", "in", "heavenly", "peace"
        );

        final List<String> cyrillicTextParsingResult = TextParser.getWords(englishText);

        assertEquals(expectedParsingResults, cyrillicTextParsingResult);
    }

    @Test
    @DisplayName("Parsing of English text #2")
    public void testEnglishTextParsing2() {
        final String englishText = """
THE Java® programming language is a general-purpose, concurrent, class-based, object-oriented language.
It is designed to be simple enough that many programmers can achieve fluency in the language.
""";
        final List<String> expectedParsingResults = List.of(
                "THE", "Java", "programming", "language", "is", "a", "general", "purpose", "concurrent", "class", "based", "object", "oriented", "language",
                "It", "is", "designed", "to", "be", "simple", "enough", "that", "many", "programmers", "can", "achieve", "fluency", "in", "the", "language"
        );

        final List<String> cyrillicTextParsingResult = TextParser.getWords(englishText);

        assertEquals(expectedParsingResults, cyrillicTextParsingResult);
    }

    @Test
    @DisplayName("Parsing of Cyrillic text #1")
    public void testCyrillicTextParsing() {
        final String cyrillicText = """
Вставай, проклятьем заклеймённый,
Весь мир голодных и рабов!
Кипит наш разум возмущённый
И смертный бой вести готов.
""";
        final List<String> expectedParsingResults = List.of(
                "Вставай", "проклятьем", "заклеймённый",
                "Весь", "мир", "голодных", "и", "рабов",
                "Кипит", "наш", "разум", "возмущённый",
                "И", "смертный", "бой", "вести", "готов"
        );

        final List<String> cyrillicTextParsingResult = TextParser.getWords(cyrillicText);

        assertEquals(expectedParsingResults, cyrillicTextParsingResult);
    }

    @Test
    @DisplayName("Parsing of Cyrillic text #2")
    public void testCyrillicTextParsing2() {
        final String cyrillicText = """
Марш, вперед, железными рядами
В бой за родину, за наш народ!
Только вера двигает горами,
Только смелость города берет!
""";
        final List<String> expectedParsingResults = List.of(
                "Марш", "вперед", "железными", "рядами",
                "В", "бой", "за", "родину", "за", "наш", "народ",
                "Только", "вера", "двигает", "горами",
                "Только", "смелость", "города", "берет"
        );

        final List<String> cyrillicTextParsingResult = TextParser.getWords(cyrillicText);

        assertEquals(expectedParsingResults, cyrillicTextParsingResult);
    }
}
