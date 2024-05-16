package controller.userinput.readers;

import debug.logs.MainLogger;

import java.util.Scanner;

public final class ConsoleInputReader {
    private static final Scanner scanner = new Scanner(System.in);

    public String readLine() {

        String result = "";

        try {
            result = scanner.nextLine();
        } catch (RuntimeException ex) {
            MainLogger.logSevere(ex);
            // TODO: Add new RuntimeException child to throw when user input fails
        }

        return result;
    }

}
