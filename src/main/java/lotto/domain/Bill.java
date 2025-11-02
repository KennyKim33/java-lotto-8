package lotto.domain;

import lotto.LottoConfig;
import lotto.exception.ErrorMessage;

public class Bill {
    private final int amount;

    public Bill(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getTicketCount() {
        return amount / LottoConfig.TICKET_UNIT;
    }

    private void validate(int amount) {
        validateMinAmount(amount);
        validateUnit(amount);
    }

    private void validateMinAmount(int amount) {
        if (amount < LottoConfig.TICKET_UNIT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_MINIMUM.getMessage());
        }
    }

    private void validateUnit(int amount) {
        if (amount % LottoConfig.TICKET_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
        }
    }
}
