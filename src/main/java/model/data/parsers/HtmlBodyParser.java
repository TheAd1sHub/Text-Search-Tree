package model.data.parsers;

import org.jsoup.Jsoup;

public final class HtmlBodyParser {

    public String getContents(String html) {
        StringBuilder result = new StringBuilder();

        Jsoup.parse(html)
                .body()
                .children()
                .forEach(result::append);

        String resultWithNoTags = removeHtmlTags(result.toString());

        return resultWithNoTags;
    }

    private String removeHtmlTags(String html) {
        StringBuilder result = new StringBuilder(html);

        int indexOfLastOpeningBracket = result.lastIndexOf("<");
        int indexOfLastClosedBracket = result.lastIndexOf(">");
        while (indexOfLastOpeningBracket != -1 && indexOfLastClosedBracket != -1) {
            result.replace(indexOfLastOpeningBracket, indexOfLastClosedBracket, "");

            indexOfLastOpeningBracket = result.lastIndexOf("<");
            indexOfLastClosedBracket = result.lastIndexOf(">");
        }

        return result.toString();
    }
}
