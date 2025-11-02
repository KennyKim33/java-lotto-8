package lotto.domain;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorTest {

    @RepeatedTest(10)
    void 범위_내에_중복되지_않은_정수를_반환한다() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        List<Integer> generated = lottoNumberGenerator.generate();

        assertThat(generated).hasSize(6);
        assertThat(generated).allMatch(number -> number >= 1 && number <= 45);
        assertThat(generated).doesNotHaveDuplicates();
    }
}