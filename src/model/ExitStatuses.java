package model;

public enum ExitStatuses {
    INVALID_INPUT(1),
    SUCCESS(0),
    DATA_READING_FAILURE(-1),
    NOT_IMPLEMENTED(-2),
    UNKNOWN_ERROR(-3);


    public final int code;

    private ExitStatuses(int code) {
        this.code = code;
    }
}
