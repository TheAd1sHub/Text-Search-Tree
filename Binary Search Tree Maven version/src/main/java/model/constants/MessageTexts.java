package model.constants;

public final class MessageTexts {

    // Main menu
    public static final String initialMenuText = """
Welcome to TextParser!
Please, state where do you want to get the text from:
1. Local file
2. File in the web
3. Web Page contents
4. Web Page contents (Raw)
""";

    // Additional data request
    public static final String localFilePathRequestText = "Please, state the path to the text file.";

    public static final String webPageUrlAddressRequestText = "Please, state the URL of the source website.";


    public static final String soughtForTokenRequestText = "State the sought-for word.";


    // Exceptions Notifications
    public static final String invalidInputNotificationText = "Unfortunately, we cannot interpret your input.\n" +
                                                                "Please, check its consistency and try again.";
}
