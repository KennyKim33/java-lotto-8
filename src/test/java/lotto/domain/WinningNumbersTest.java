package lotto.domain;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumbersTest {

    @Test
    void 당첨_번호_정상_생성() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        WinningNumbers winningNumbers = new WinningNumbers(lotto, bonusNumber);

        assertThat(winningNumbers.getLotto()).isEqualTo(lotto);
        assertThat(winningNumbers.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외_발생() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(6);

        assertThatThrownBy(() -> new WinningNumbers(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.BONUS_DUPLICATED_WITH_WINNING.getMessage());
    }
}