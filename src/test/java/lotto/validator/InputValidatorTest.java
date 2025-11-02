package lotto.validator;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class InputValidatorTest {

    private InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void 구매_금액이_공백이거나_null이면_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateAmountInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EMPTY.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123abc", "abc"})
    void 구매_금액이_숫자가_아니면_예외_발생() {
        assertThatThrownBy(() -> validator.validateAmountInput("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMERIC.getMessage());
    }

    @Test
    void 올바른_구매_금액_입력() {
        assertThatCode(() -> validator.validateAmountInput("1000"))
                .doesNotThrowAnyException();
    }

    // 당첨 번호 입력 검증
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void 당첨_번호가_비어있으면_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateWinningNumbersInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EMPTY.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {",1,2,3", "1,2,3,", "1,,2,3", "1, ,2,3", "1,2,a,4"})
    void 당첨_번호_형식이_잘못되면_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateWinningNumbersInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_FORMAT.getMessage());
    }

    @Test
    void 올바른_당첨_번호_입력() {
        assertThatCode(() -> validator.validateWinningNumbersInput("1,2,3,4,5,6"))
                .doesNotThrowAnyException();
    }

    // 보너스 번호 입력 검증
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void 보너스_번호가_비어있으면_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateBonusNumberInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EMPTY.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123abc", "abc"})
    void 보너스_번호가_숫자가_아니면_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateBonusNumberInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMERIC.getMessage());
    }

    @Test
    void 올바른_보너스_번호_입력() {
        assertThatCode(() -> validator.validateBonusNumberInput("7"))
                .doesNotThrowAnyException();
    }
}