package lotto.domain;

import lotto.LottoConfig;
import lotto.exception.ErrorMessage;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    public int getBonusNumber() {
        return number;
    }

    private void validate(int number) {
        if (number < LottoConfig.MIN_NUMBER || number > LottoConfig.MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
        }
    }
}
