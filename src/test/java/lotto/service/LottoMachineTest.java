package lotto.service;

import lotto.domain.*;
import lotto.dto.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(null);
    }

    @Test
    void 일등_당첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(2_000_000_000L);
        assertThat(result.getProfitRate()).isEqualTo(200_000_000.0);
    }

    @Test
    void 이등_당첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(30_000_000L);
        assertThat(result.getProfitRate()).isEqualTo(3_000_000.0);
    }

    @Test
    void 삼등_당첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(1_500_000L);
        assertThat(result.getProfitRate()).isEqualTo(150_000.0);
    }

    @Test
    void 사등_당첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(50_000L);
        assertThat(result.getProfitRate()).isEqualTo(5_000.0);
    }

    @Test
    void 오등_당첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(5_000L);
        assertThat(result.getProfitRate()).isEqualTo(500.0);
    }

    @Test
    void 낙첨시_결과를_정확히_계산한다() {
        Bill bill = new Bill(1000);
        Lotto ticket = new Lotto(List.of(1, 2, 8, 9, 10, 11));
        List<Lotto> tickets = List.of(ticket);

        Lotto winningLotto = new Lotto(List.of(3, 4, 5, 6, 7, 12));
        BonusNumber bonusNumber = new BonusNumber(13);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(1);
        assertThat(result.getTotalPrize()).isEqualTo(0L);
        assertThat(result.getProfitRate()).isEqualTo(0.0);
    }

    @Test
    void 여러_당첨_등수가_섞여있을_때_정확히_계산한다() {
        Bill bill = new Bill(5000);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        );

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.THIRD)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(1);
    }

    @Test
    void 총_상금을_정확히_계산한다() {
        Bill bill = new Bill(5000);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9))
        );

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        long expectedPrize = 2_000_000_000L + 30_000_000L + 1_500_000L + 50_000L;
        assertThat(result.getTotalPrize()).isEqualTo(expectedPrize);
    }

    @Test
    void 수익률을_정확히_계산한다() {
        Bill bill = new Bill(5000);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        double expectedRate = (double) 2_000_000_000L / 5000 * 100;
        assertThat(result.getProfitRate()).isEqualTo(expectedRate);
    }

    @Test
    void 같은_등수가_여러_개일_때_정확히_계산한다() {
        Bill bill = new Bill(3000);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 3, 11, 12, 13)),
                new Lotto(List.of(1, 2, 3, 14, 15, 16))
        );

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);

        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(3);
        assertThat(result.getTotalPrize()).isEqualTo(15_000L);
        assertThat(result.getProfitRate()).isEqualTo(500.0);
    }
}