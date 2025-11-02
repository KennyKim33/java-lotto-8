package lotto.domain;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 정상적인_로또_번호_생성() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.getNumbers())
                .hasSize(6)
                .containsExactlyElementsOf(numbers);
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumbers")
    void 로또_번호의_범위가_1보다_작거나_45보다_크면_예외가_발생한다(List<Integer> invalidNumbers) {
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
    }

    @Test
    void 보너스_번호를_포함하고_있는지_확인한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 5;

        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.containsBonusNumber(5))
                .isTrue();
    }

    private static Stream<List<Integer>> provideInvalidNumbers() {
        return Stream.of(
                List.of(0, 1, 2, 3, 4, 5),      // 0 포함
                List.of(1, 2, 3, 4, 5, 46),     // 46 포함
                List.of(-1, 1, 2, 3, 4, 5),     // -1 포함
                List.of(1, 2, 3, 4, 5, 100)    // 100 포함
        );
    }
}
