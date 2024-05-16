package model.input.interpreters;

import java.io.IOException;
import java.util.InputMismatchException;

public final class IntegerInputInterpreter extends NumericInputInterpreter<Integer> {
    @Override
    public Integer interpret(String input) throws InputMismatchException, IOException {
        int result;

        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InputMismatchException("Cannot get correct value from the given input.");
        }

        return result;
    }
}
