package model.constants;

import debug.logging.MainLogger;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Make this class read the required phrases from config .json, .xml or .lua file instead of storing them as literals
public final class MessageTexts {

    static {
        loadLocalisationDataFromJson(Path.of("C:\\Users\\yusere\\Documents\\Java Projects\\Text Search Tree\\src\\main\\java\\model\\constants\\message_texts.json"));
    }


    // Additional data request
    private static String localFilePathRequestText = "Please, state the path to the text file.";

    private static String webPageUrlAddressRequestText = "Please, state the URL of the source website.";

    private static String soughtForTokenRequestText = "State the sought-for word.";


    // Exceptions Notifications
    private static String invalidInputNotificationText = "Unfortunately, we cannot interpret your input.\n" +
                                                                "Please, check its consistency and try again.";

    private static String readingSessionFailureNotificationText = "Attempt to receive data from the given source resulted in a failure.\n" +
                                                                "Please, try again later!";

    private static String unknownErrorNotificationText = "Something went wrong whilst processing your request.\n" +
                                                                "Please, check the consistency of your input or try again later!";


    public static String getLocalFilePathRequestText() {
        return localFilePathRequestText;
    }

    public static String getWebPageUrlAddressRequestText() {
        return webPageUrlAddressRequestText;
    }

    public static String getSoughtForTokenRequestText() {
        return soughtForTokenRequestText;
    }


    public static String getInvalidInputNotificationText() {
        return invalidInputNotificationText;
    }

    public static String getReadingSessionFailureNotificationText() {
        return readingSessionFailureNotificationText;
    }

    public static String getUnknownErrorNotificationText() {
        return unknownErrorNotificationText;
    }


    private MessageTexts() {
        ;
    }


    private static void loadLocalisationDataFromJson(Path pathToCfgFile) {
        try {
            StringBuilder bufferedJson = new StringBuilder();

            Files.readAllLines(pathToCfgFile)
                    .forEach(bufferedJson::append);

            JSONObject configObject = new JSONObject(bufferedJson.toString());

            Class<MessageTexts> clazz = MessageTexts.class;
            for (Field field : clazz.getDeclaredFields()) {
                if (!configObject.has(field.getName())) {
                    MainLogger.logWarning(
                            "The value for '" + field.getName()  +
                                    "' field was not found in " + pathToCfgFile + " config file."
                    );

                    continue;
                }

                String fieldValue = configObject.getString(field.getName());
                field.set(null, fieldValue);
            }

        } catch (IOException e) {

            throw new RuntimeException("Unable to read message texts from the config file." , e);
        } catch (IllegalAccessException e) {

            throw new RuntimeException("Unable to set the values to fields of the class due to access restriction.", e);
        }
    }
}
