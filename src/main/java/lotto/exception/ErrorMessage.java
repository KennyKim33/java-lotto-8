package lotto.exception;

public enum ErrorMessage {
    NOT_EMPTY("입력은 비어 있을 수 없습니다."),
    NOT_NUMERIC("숫자만 입력 가능합니다."),
    INVALID_LOTTO_FORMAT("로또 번호는 쉼표(,)로 구분하여 입력해주세요. (예: 1,2,3,4,5,6)"),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다.");
    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
