package model.input.interpreters;

import model.input.exceptions.InvalidInputException;

public final class IntegerInputInterpreter extends NumericInputInterpreter<Integer> {
    @Override
    public Integer interpret(String input) throws InvalidInputException {
        int result;

        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Cannot get correct value from the given input.");
        }

        return result;
    }
}
