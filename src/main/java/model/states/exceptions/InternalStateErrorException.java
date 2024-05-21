package model.states.exceptions;

import org.jetbrains.annotations.NotNull;

public final class InternalStateErrorException extends RuntimeException {

    private final RuntimeException internalException;

    public InternalStateErrorException(@NotNull RuntimeException internalException) {
        this(internalException, false);
    }

    public InternalStateErrorException(@NotNull RuntimeException internalException, boolean preserveCallStack) {
        if (preserveCallStack) {
            this.internalException = internalException;
        } else {
            this.internalException = new RuntimeException(internalException);
        }
    }


    public RuntimeException getInternalException() {

        return internalException;
    }
}
