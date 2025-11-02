package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoGeneratorTest {

    private LottoNumberGenerator lottoNumberGenerator;
    private LottoGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        lottoNumberGenerator = new LottoNumberGenerator();
        lottoGenerator = new LottoGenerator(lottoNumberGenerator);
    }

    @Test
    void 로또를_여러_장_생성한다() {
        List<Lotto> lottos = lottoGenerator.generateMany(5);

        assertThat(lottos).hasSize(5);
    }

    @Test
    void 생성된_로또는_정렬되어_있다() {
        List<Lotto> lottos = lottoGenerator.generateMany(1);
        List<Integer> numbers = lottos.get(0).getNumbers();

        assertThat(numbers).isSorted();
    }

    @Test
    void 생성된_각_로또는_6개의_번호를_가진다() {
        List<Lotto> lottos = lottoGenerator.generateMany(3);

        assertThat(lottos).allMatch(lotto -> lotto.getNumbers().size() == 6);
    }
}