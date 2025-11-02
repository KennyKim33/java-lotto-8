package lotto.domain;

import lotto.exception.ErrorMessage;

public class WinningNumbers {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(Lotto lotto, BonusNumber bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.containsBonusNumber(bonusNumber.getNumber())) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_DUPLICATED_WITH_WINNING.getMessage());
        }
    }

}
