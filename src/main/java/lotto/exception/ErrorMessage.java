package lotto.exception;

import lotto.LottoConfig;

public enum ErrorMessage {
    NOT_EMPTY("입력은 비어 있을 수 없습니다."),
    NOT_NUMERIC("숫자만 입력 가능합니다."),
    INVALID_LOTTO_FORMAT("로또 번호는 쉼표(,)로 구분하여 입력해주세요. (예: 1,2,3,4,5,6)"),
    INVALID_LOTTO_SIZE(String.format("로또 번호는 %d개 여야 합니다.",
            LottoConfig.LOTTO_SIZE)),
    INVALID_LOTTO_RANGE(String.format("로또 번호는 %d부터 %d사이의 숫자여야합니다."
            , LottoConfig.MIN_NUMBER, LottoConfig.MAX_NUMBER)),
    DUPLICATE_LOTTO_NUMBER("로또 번호는 중복되면 안됩니다."),
    BONUS_DUPLICATED_WITH_WINNING("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_AMOUNT_MINIMUM(String.format("구매 금액은 %,d원 이상이어야 합니다."
            , LottoConfig.TICKET_UNIT)),
    INVALID_AMOUNT_UNIT(String.format("구매 금액은 %,d원 단위로 입력해야 합니다.",
            LottoConfig.TICKET_UNIT));

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
