package model;

public final class MessageTexts {

    public static final String initialMenuText = """
Welcome to TextParser!
Please, state where do you want to get the text from:
1. Local file
2. File in the web
3. Web Page contents
4. Web Page contents (Raw)
""";

    public static final String localFilePathRequestText = "Please, state the position of the text file.";

    public static final String invalidInputNotificationText = "Unfortunately, we cannot interpret your input.\n" +
                                                                "Please, check its consistency and try again.";
}
