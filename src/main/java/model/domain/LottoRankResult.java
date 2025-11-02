package model.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class LottoRankResult {
    private static final String LN = System.lineSeparator();
    private static final String TITLE_WINNING_STATISTICS = "당첨 통계";
    private static final String TITLE_SEPARATOR = "---";
    private static final String YIELD_LINE_FORMAT = "총 수익률은 %.1f%%입니다.";
    private static final int LOTTO_UNIT_PRICE = 1_000;
    private static final long SUM_START_NUM = 0L;
    private static final int COUNT_START_NUM = 0;
    private static final double PERCENT_DIVISOR = 100.0;
    private final EnumMap<LottoRank, Integer> counts;
    private final int purchasedCount;

    private LottoRankResult(List<LottoRank> ranks, int purchasedCount) {
        counts = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            counts.put(lottoRank, COUNT_START_NUM);
        }

        for (LottoRank lottoRank : ranks) {
            counts.put(lottoRank, counts.get(lottoRank) + 1);
        }
        this.purchasedCount = purchasedCount;
    }

    public static LottoRankResult of(List<LottoRank> ranks, int purchasedCount) {
        return new LottoRankResult(ranks, purchasedCount);
    }

    public long totalPrize() {
        long sum = SUM_START_NUM;
        for (Entry<LottoRank, Integer> entry : counts.entrySet()) {
            LottoRank rank = entry.getKey();
            int cnt = entry.getValue();
            sum += (long) rank.prize() * cnt;
        }
        return sum;
    }

    public long totalSpent() {
        return (long) purchasedCount * LOTTO_UNIT_PRICE;
    }

    public double yieldPercent() {
        long spent = totalSpent();
        return (totalPrize() * PERCENT_DIVISOR) / spent;
    }

    public int countOf(LottoRank rank) {
        return counts.get(rank);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(LN)
                .append(TITLE_WINNING_STATISTICS).append(LN)
                .append(TITLE_SEPARATOR).append(LN);
        for (LottoRank rank : LottoRank.printOrder()) {
            sb.append(rank.formatLine(countOf(rank))).append(LN);
        }
        return sb.append(String.format(YIELD_LINE_FORMAT, yieldPercent()))
                .toString();
    }
}
