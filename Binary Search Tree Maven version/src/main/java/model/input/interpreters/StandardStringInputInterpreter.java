package model.input.interpreters;

import java.util.InputMismatchException;

public final class StandardStringInputInterpreter extends RawInputInterpreter<String> {

	@Override
	public String interpret(String input) {
		if (input == null || input.isEmpty()) {
			throw new InputMismatchException("The given input is null or empty.");
		}
        
        return input;
	}
	
}
