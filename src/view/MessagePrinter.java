package view;

public final class MessagePrinter {
    private MessagePrinter() {
        ;
    }

    public static void printFileNameRequest() {
        System.out.print("State the name of the file\n> ");
    }

    public static void printElementToSearchRequest() {
        System.out.print("State the sought-for value\n> ");
    }
    
    public static void printIgnoreCaseToggleRequest() {
    	System.out.print("State the sought-for value\n> ");
    }
    
}
