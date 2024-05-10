package controller.userinput;

import java.io.IOException;
import java.util.InputMismatchException;

public abstract class BaseUserInputReader {
	public static String read() throws InputMismatchException {
		throw new InputMismatchException("The method is not implemented!");
	}
}
