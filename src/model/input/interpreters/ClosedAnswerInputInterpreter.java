package model.input.interpreters;

import java.io.IOException;
import java.util.InputMismatchException;

import model.AllowedValues;

public final class ClosedAnswerInputInterpreter extends RawInputInterpreter<Boolean> {

	public Boolean interpret(String input) throws InputMismatchException, IOException {
		String userAnswer = System.console().readLine();

		if (AllowedValues.getClosedQuestionsAnswersMap().containsKey(userAnswer)) {

			return AllowedValues.getClosedQuestionsAnswersMap().get(userAnswer);
		}

		throw new InputMismatchException("The given answer is not allowed.");
	}
	
}
