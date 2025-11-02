package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.LottoConfig;

import java.util.List;

public class LottoNumberGenerator {

    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoConfig.MIN_NUMBER,
                LottoConfig.MAX_NUMBER,
                LottoConfig.LOTTO_SIZE
        );
    }
}
