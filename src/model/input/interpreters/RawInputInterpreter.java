package model.input.interpreters;

import java.io.IOException;
import java.util.InputMismatchException;

public abstract class RawInputInterpreter<T> {

	public abstract T interpret(String input)
			throws InputMismatchException, IOException;
}
