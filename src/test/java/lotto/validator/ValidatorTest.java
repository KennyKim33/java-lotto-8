package lotto.validator;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {
    private final Validator validator = input -> {};

    @Test
    void 비어있지_않은_문자열에_대해서는_예외_발생하지_않는다() {
        assertThatCode(() -> validator.validateNotEmpty("test"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void 빈_문자열_또는_null_입력시_예외_발생(String input) {
        assertThatThrownBy(() -> validator.validateNotEmpty(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EMPTY.getMessage());
    }

}