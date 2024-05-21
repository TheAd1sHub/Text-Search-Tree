package model.data.parsers;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public final class TextParser {

    public static List<String> getWords(String text) {
        List<String> words = Pattern.compile("\\b\\p{L}+\\b")
                                    .matcher(text)
                                    .results()
                                    .map(MatchResult::group)
                                    .toList();

        return words;
    }
}
