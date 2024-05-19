package model.constants;

// TODO: Make this class read the required phrases from config .json, .xml or .lua file instead of storing them as literals
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

    public static final String readingSessionFailureNotificationText = "Attempt to receive data from the given source resulted in a failure.\n" +
                                                                "Please, try again later!";

    public static final String unknownErrorNotificationText = "Something went wrong whilst processing your request.\n" +
                                                                "Please, check the consistency of your input or try again later!";

}
