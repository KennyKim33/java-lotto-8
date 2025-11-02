package lotto.domain;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    @Test
    void 정상적인_보너스_번호_생성() {
        int number = 7;

        BonusNumber bonusNumber = new BonusNumber(number);

        assertThat(bonusNumber.getBonusNumber())
                .isEqualTo(number);
    }

    @ParameterizedTest
    @ValueSource(ints = {47, 100, 0, -1})
    void 보너스_번호의_범위가_1보다_작거나_45보다_크면_예외가_발생한다(int number) {
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
    }
}