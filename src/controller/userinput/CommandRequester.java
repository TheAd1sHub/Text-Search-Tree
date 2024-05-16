package controller.userinput;

import controller.userinput.readers.ConsoleInputReader;
import view.MessagePrinter;

public final class CommandRequester {
    private final String initialMessage;

    private final MessagePrinter printer;
    private final ConsoleInputReader reader;

    public CommandRequester(String initialMessage) {
        this.initialMessage = initialMessage;

        printer = new MessagePrinter(this.initialMessage, true);
        reader = new ConsoleInputReader();
    }

    public String requestCommand() {
        printer.print();
        String input = reader.readLine();

        return input;
    }
}
