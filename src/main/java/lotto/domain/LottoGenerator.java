package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoGenerator(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<Lotto> generateMany(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generate())
                .toList();
    }

    private Lotto generate() {
        List<Integer> numbers = lottoNumberGenerator.generate();
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        return new Lotto(sortedNumbers);
    }
}
