package view;

public final class MessagePrinter {
    private final String INPUT_INDICATOR = "> ";

    private final String message;
    private final boolean isInputRequest;

    public MessagePrinter(String message, boolean isInputRequest) {
        this.message = message;
        this.isInputRequest = isInputRequest;
    }

    public void print() {
        System.out.println(message);

        if (isInputRequest) {
            System.out.print(INPUT_INDICATOR);
        }
    }

    @Deprecated
    public static void printFileNameRequest() {
        System.out.print("State the name of the file\n> ");
    }

    @Deprecated
    public static void printElementToSearchRequest() {
        System.out.print("State the sought-for value\n> ");
    }

    @Deprecated
    public static void printIgnoreCaseToggleRequest() {
    	System.out.print("Ignore case while searching?\n> ");
    }

    @Deprecated
    public static void printUrlRequest() {
        System.out.print("State the URL of the file\n> ");
    }
}
