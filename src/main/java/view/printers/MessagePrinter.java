package view.printers;

public final class MessagePrinter implements Printer<String> {

    private final String inputRequestIndication = "> ";

    @Override
    public void print(String message) {
        print(message, false);
    }

    public void print(String message, boolean isInputRequest) {
        System.out.println(message);

        if (isInputRequest) {
            printInputRequest();
        }
    }

    public void printInputRequest() {
        System.out.print(inputRequestIndication);
    }
}
