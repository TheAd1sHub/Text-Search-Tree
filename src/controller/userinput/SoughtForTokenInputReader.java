package controller.userinput;

import java.util.InputMismatchException;

import view.MessagePrinter;

public final class SoughtForTokenInputReader extends BaseUserInputReader {

	public static String read(boolean validate) throws InputMismatchException {
		String givenToken = read();
		
		if (!validate) {
			return givenToken;
		}
		
		if (givenToken == null || givenToken.isEmpty()) {
			throw new InputMismatchException("The given input is null or empty.");
		}
		
		return givenToken;
	}
	
	public static String read() {
		MessagePrinter.printElementToSearchRequest();
        String givenToken = System.console().readLine();
        
        return givenToken;
	}
	
}
