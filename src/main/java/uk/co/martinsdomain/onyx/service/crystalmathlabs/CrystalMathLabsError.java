package uk.co.martinsdomain.onyx.service.crystalmathlabs;

public enum CrystalMathLabsError {

    USER_NOT_IN_DATABASE(-1),
    INVALID_USERNAME(-2),
    DATABASE_ERROR(-3),
    SERVER_UNDER_LOAD(-4),
    UNKNOWN(-99);

    private final int errorId;

    private CrystalMathLabsError(final int errorId) {
        this.errorId = errorId;
    }

    public int getErrorId() {
        return errorId;
    }

    @Override
    public String toString() {
        return "CmlError{" +
                "errorId=" + errorId +
                '}';
    }
}
