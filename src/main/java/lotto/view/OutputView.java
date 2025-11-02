package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.LottoResult;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String PURCHASED_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS = "\n당첨 통계\n---";
    private static final String RANK_FORMAT = "%s (%s원) - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

    public void printPurchasedTickets(List<Lotto> tickets, int count) {
        System.out.println(String.format(PURCHASED_MESSAGE, count));
        for (Lotto ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }
    }

    public void printResult(LottoResult result) {
        System.out.println(WINNING_STATISTICS);
        printRankStatistics(result);
        printProfitRate(result.getProfitRate());
    }

    private void printRankStatistics(LottoResult result) {
        printRank(Rank.FIFTH, result);
        printRank(Rank.FOURTH, result);
        printRank(Rank.THIRD, result);
        printRank(Rank.SECOND, result);
        printRank(Rank.FIRST, result);
    }

    private void printRank(Rank rank, LottoResult result) {
        int count = result.getCountByRank(rank);
        String prize = MONEY_FORMAT.format(rank.getPrize());
        System.out.println(String.format(RANK_FORMAT, rank.getDescription(), prize, count));
    }

    private void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }
}
