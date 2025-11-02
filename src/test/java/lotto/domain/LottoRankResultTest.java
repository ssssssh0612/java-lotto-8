package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static model.domain.LottoRank.*;

import java.util.List;
import model.domain.LottoRank;
import model.domain.LottoRankResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRankResultTest {

    @Test
    @DisplayName("단일 FIFTH 1개 + 구매 8개 → 총상금 5,000 / 총지출 8,000 / 수익률 62.5%")
    void 수익률_5등_1개_8장구매면_62_5퍼센트가_된다() {
        // given
        List<LottoRank> ranks = List.of(FIFTH);
        int purchasedCount = 8;

        // when
        LottoRankResult result = LottoRankResult.of(ranks, purchasedCount);

        // then
        assertThat(result.totalPrize()).isEqualTo(5_000);
        assertThat(result.totalSpent()).isEqualTo(8_000);
        assertThat(result.yieldPercent()).isEqualTo(62.5);
    }

    @Test
    @DisplayName("구매 개수에 따라 총지출이 1,000원 단위로 계산된다")
    void 구매_개수에_따른_총지출_1000원_단위로_계산() {
        assertThat(LottoRankResult.of(List.of(), 0).totalSpent()).isEqualTo(0);
        assertThat(LottoRankResult.of(List.of(), 1).totalSpent()).isEqualTo(1_000);
        assertThat(LottoRankResult.of(List.of(), 10).totalSpent()).isEqualTo(10_000);
    }

    @Test
    @DisplayName("총상금은 각 랭크의 (상금 × 개수)의 합이다")
    void 총상금은_각_랭크의_상금_X_개수의_합이다() {
        List<LottoRank> ranks = List.of(
                FIRST,
                SECOND,
                THIRD,
                FOURTH, FOURTH,
                FIFTH, FIFTH, FIFTH
        );
        long expected =
                2_000_000_000L +
                        30_000_000L +
                        1_500_000L +
                        2L * 50_000L +
                        3L * 5_000L;

        LottoRankResult result = LottoRankResult.of(ranks, ranks.size());
        assertThat(result.totalPrize()).isEqualTo(expected);
    }
}
