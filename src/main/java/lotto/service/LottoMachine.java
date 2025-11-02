package lotto.service;

import lotto.domain.*;
import lotto.dto.LottoResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> generateLottos(Bill bill) {
        return lottoGenerator.generateMany(bill.getTicketCount());
    }

    public LottoResult calculateResult(List<Lotto> tickets, WinningNumbers winningNumbers, Bill bill) {
        Map<Rank, Integer> rankCounts = countRanksByTickets(tickets, winningNumbers);
        long totalPrize = calculateTotalPrize(rankCounts);
        double profitRate = calculateProfitRate(totalPrize, bill.getAmount());

        return new LottoResult(rankCounts, totalPrize, profitRate);
    }

    private Map<Rank, Integer> countRanksByTickets(List<Lotto> tickets, WinningNumbers winningNumbers) {
        return tickets.stream()
                .map(winningNumbers::calculateRank)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }

    private long calculateTotalPrize(Map<Rank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    private double calculateProfitRate(long totalPrize, int purchaseAmount) {
        return (double) totalPrize / purchaseAmount * 100;
    }
}
