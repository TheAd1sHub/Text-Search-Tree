package debug.exceptions;

public class ReadingSessionFailException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "An exception occurred during the data reading session.";

    private final Exception innerException;
    private final String message;


    public ReadingSessionFailException(String message, Exception exceptionToEncapsulate, boolean hideCallStack) {
        super();

        this.message = message;

        if (exceptionToEncapsulate == null) {
            innerException = null;

            return;
        }

        if (!hideCallStack) {
            innerException = exceptionToEncapsulate;

            return;
        }

        innerException = new RuntimeException(
                exceptionToEncapsulate.getMessage(),
                exceptionToEncapsulate.getCause()
        );
    }

    public ReadingSessionFailException(String message, Exception exceptionToEncapsulate) {
        this(message, exceptionToEncapsulate, false);
    }

    public ReadingSessionFailException(Exception exceptionToEncapsulate, boolean hideCallStack) {
        this(DEFAULT_MESSAGE, exceptionToEncapsulate, hideCallStack);
    }

    public ReadingSessionFailException(String message) {
        this(message, null);
    }

    public ReadingSessionFailException(Exception exceptionToEncapsulate) {
        this(DEFAULT_MESSAGE, exceptionToEncapsulate);
    }

    public ReadingSessionFailException() {
        this(DEFAULT_MESSAGE);
    }


    public boolean containsInnerExceptions() {
        return innerException != null;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public String getMessage() {
        return message;
    }
}
