package model.domain;

import java.util.List;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치"),
    NONE(0, false, 0, "낙첨");
    private final int matchCount;
    private final boolean bonusRequired;
    private final int prize;
    private final String label;

    LottoRank(int matchCount, boolean bonusRequired, int prize, String label) {
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
        this.label = label;
    }

    public int prize() {
        return prize;
    }

    public static List<LottoRank> printOrder() {
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }

    public String formatLine(int count) {
        return String.format("%s (%,d원) - %d개", label, prize, count);
    }

    public static LottoRank of(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5) {
            return rankWhenFiveMatched(bonusMatch);
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    private static LottoRank rankWhenFiveMatched(boolean bonusMatch) {
        if (bonusMatch) {
            return SECOND;
        }
        return THIRD;
    }

}
