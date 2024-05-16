package model.validators;

public abstract class Validator<T> {

    public abstract boolean isValid(T value);
}
