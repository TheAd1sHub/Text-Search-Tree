package model.logs;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Implement logging into file
public final class MainLogger {
    private static final String logFileName = constructLogFilename();

    private static final Logger logger = Logger.getLogger("General logger");
    private static FileHandler handler;


    private MainLogger() {
        ;
    }

    static {
        try {
            handler = new FileHandler(logFileName, true);
        } catch(IOException ex) {
            logWarning(
                    "Failed to initialize FileHandler for logging purposes.\n" +
                    "Logs for this session will only be printed in console.\n" +
                    "Exception feedback: '" + ex.getMessage() + "'"
            );
        }
    }


    public static String getLogFileName() {
        return logFileName;
    }

    public static void logMessage(String message) {
        logger.log(Level.INFO, "Message:\n" + message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, "Warning. Attention required:\n" + message);
    }

    public static void logSevere(Throwable ex) {
        logger.log(Level.SEVERE, "A severe exception occurred!\n", ex);
    }


    private static String constructLogFilename() {
        return LocalDate.now() + ".log";
    }

}