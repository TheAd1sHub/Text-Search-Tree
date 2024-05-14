package debug.exceptions;

public class HTTPReadingSessionFailException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "An exception occurred during the HTTP data reading session.";

    private final Exception innerException;
    private final String message;


    public HTTPReadingSessionFailException(String message, Exception exceptionToEncapsulate, boolean hideCallStack) {
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

    public HTTPReadingSessionFailException(String message, Exception exceptionToEncapsulate) {
        this(message, exceptionToEncapsulate, false);
    }

    public HTTPReadingSessionFailException(Exception exceptionToEncapsulate, boolean hideCallStack) {
        this(DEFAULT_MESSAGE, exceptionToEncapsulate, hideCallStack);
    }

    public HTTPReadingSessionFailException(String message) {
        this(message, null);
    }

    public HTTPReadingSessionFailException(Exception exceptionToEncapsulate) {
        this(DEFAULT_MESSAGE, exceptionToEncapsulate);
    }

    public HTTPReadingSessionFailException() {
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
