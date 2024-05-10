package controller.userinput;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.AllowedValues;

public final class IgnoreCaseAnswerInputReader extends BaseClosedQuestionAnswerInputReader {
	private static Scanner scanner = new Scanner(System.in);

	private IgnoreCaseAnswerInputReader() {
		;
	}
	
	public static boolean readAsBool() throws InputMismatchException {		
        String userAnswer = scanner.nextLine();
        
        if (AllowedValues.getClosedQuestionsAnswersMap().containsKey(userAnswer)) {
        	
        	return AllowedValues.getClosedQuestionsAnswersMap().get(userAnswer);
        } 
        
        throw new InputMismatchException("The given answer is not allowed.");
	}
	
	public static String read() throws InputMismatchException {
		Boolean userAnswer = readAsBool();
		
		return userAnswer.toString();
	}
}
