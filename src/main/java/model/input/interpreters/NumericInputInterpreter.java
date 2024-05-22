package model.input.interpreters;

import model.input.exceptions.InvalidInputException;

public abstract class NumericInputInterpreter<T extends Number> extends RawInputInterpreter<Number> {

    @Override
    public abstract T interpret(String input) throws InvalidInputException;
}
