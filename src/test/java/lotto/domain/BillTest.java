package lotto.domain;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BillTest {

    @Test
    void 정상적인_구매_입력_생성() {
        int amount = 5000;

        Bill bill = new Bill(amount);

        assertThat(bill.getAmount()).isEqualTo(amount);
        assertThat(bill.getTicketCount()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {30, 0, 900, 999})
    void 구매_금액이_최소_금액보다_작으면_예외_발생한다(int amount) {
        assertThatThrownBy(() -> new Bill(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_AMOUNT_MINIMUM.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 2300, 9999})
    void 구매_금액이_1000원_단위가_아니면_예외_발생한다(int amount) {
        assertThatThrownBy(() -> new Bill(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_AMOUNT_UNIT.getMessage());
    }
}