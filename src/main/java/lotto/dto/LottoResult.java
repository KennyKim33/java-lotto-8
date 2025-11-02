package lotto.dto;

import lotto.domain.Rank;

import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> rankCounts;
    private final long totalPrize;
    private final double profitRate;

    public LottoResult(Map<Rank, Integer> rankCounts, long totalPrize, double profitRate) {
        this.rankCounts = rankCounts;
        this.totalPrize = totalPrize;
        this.profitRate = profitRate;
    }

    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public Map<Rank, Integer> getRankCounts() {
        return rankCounts;
    }

    public long getTotalPrize() {
        return totalPrize;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
