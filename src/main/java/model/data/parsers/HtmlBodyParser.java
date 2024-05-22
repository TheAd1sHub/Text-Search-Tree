package model.data.parsers;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;

public final class HtmlBodyParser {

    public String getContents(String html) {

        StringBuilder result = new StringBuilder();

        Jsoup.parse(html)
                .body()
                .children()
                .forEach(element -> result.append(element).append("\n"));


        String resultWithNoTags = removeHtmlTags(result.toString());

        return resultWithNoTags;
    }

    public String getBody(String html) {
        String body = html.substring(
                html.indexOf("<body>") + "<body>".length(),
                html.indexOf("</body>" + "</body>".length() + 1)
        );

        return body;
    }

    public String getBody(URL url) throws IOException {

        return getBody(url.getContent().toString());
    }


    private String removeHtmlTags(String html) {
        StringBuilder result = new StringBuilder(html);

        int indexOfLastOpeningBracket = result.lastIndexOf("<");
        int indexOfLastClosedBracket = result.lastIndexOf(">");
        while (indexOfLastOpeningBracket != -1 && indexOfLastClosedBracket != -1) {
            String replacement = indexOfLastOpeningBracket == 0
                    || result.indexOf(" ", indexOfLastClosedBracket) != indexOfLastClosedBracket + 1
                    || result.indexOf(" ", indexOfLastOpeningBracket - 1) != indexOfLastClosedBracket - 1
                    ? ""
                    : " ";

            result.replace(indexOfLastOpeningBracket, indexOfLastClosedBracket + 1, replacement);

            indexOfLastOpeningBracket = result.lastIndexOf("<");
            indexOfLastClosedBracket = result.lastIndexOf(">");
        }

        while (result.substring(result.length() - 1).equals("\n")) {
            result.replace(result.length() - 1, result.length(), "");
        }

        // Removing new line characters in the beginning of the page
        while (result.substring(0, 1).equals("\n") || result.substring(0, 1).equals(" ")) {
            result.replace(0, 1, "");
        }

        // Removing new line characters in the end of the page
        int indexOfTwoNewLines;
        while ((indexOfTwoNewLines = result.lastIndexOf("\n\n")) != -1) {
            result.replace(indexOfTwoNewLines, indexOfTwoNewLines + 2, "\n");
        }

        // Removing spaces at the beginning of the line
        int indexOfSpaceAtLineBeginning;
        while ((indexOfSpaceAtLineBeginning = result.indexOf("\n ") + 1) > 0) {
            result.replace(indexOfSpaceAtLineBeginning, indexOfSpaceAtLineBeginning + 1, "");
        }

        // Removing spaces at the end of the line
        int indexOfSpaceAtLineEnd = result.indexOf(" \n");
        while ((indexOfSpaceAtLineEnd = result.indexOf(" \n")) > 0) {
            result.replace(indexOfSpaceAtLineEnd, indexOfSpaceAtLineEnd + 1, "");
        }

        return result.toString();
    }
}
