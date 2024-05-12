package controller.userinput;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.AllowedValues;

public final class UserInputReader {
	private static final Scanner scanner = new Scanner(System.in);

	private UserInputReader() {
		;
	}
	
	public static boolean readAsBool() throws InputMismatchException {
        String userAnswer = scanner.nextLine();

		if (AllowedValues.getClosedQuestionsAnswersMap().containsKey(userAnswer)) {
        	
        	return AllowedValues.getClosedQuestionsAnswersMap().get(userAnswer);
        } 
        
        throw new InputMismatchException("The given answer is not allowed.");
	}
	
	public static String readAsString() throws InputMismatchException {
		String userInput = scanner.nextLine();
		
		return userInput;
	}
}
