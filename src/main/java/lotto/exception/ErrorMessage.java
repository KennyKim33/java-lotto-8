package lotto.exception;

public enum ErrorMessage {
    NOT_EMPTY("입력은 비어 있을 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
