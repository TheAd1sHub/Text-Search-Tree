package model.input.interpreters;

import java.io.IOException;
import java.util.InputMismatchException;

public abstract class NumericInputInterpreter<T extends Number> extends RawInputInterpreter<Number> {

    @Override
    public abstract T interpret(String input) throws InputMismatchException, IOException;
}
