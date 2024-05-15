package controller.userinput;

import java.util.InputMismatchException;

import model.AllowedValues;

@Deprecated
public abstract class BaseClosedQuestionAnswerInputReader extends BaseUserInputReader {
	public static boolean readAsBool() throws InputMismatchException {
        String userAnswer = System.console().readLine();
        
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
