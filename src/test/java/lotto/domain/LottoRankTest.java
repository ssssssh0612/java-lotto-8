package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static model.domain.LottoRank.*;

import model.domain.LottoRank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoRankTest {

    @Test
    void prize_상수가_기대값과_일치한다() {
        assertThat(FIRST.prize()).isEqualTo(2_000_000_000);
        assertThat(SECOND.prize()).isEqualTo(30_000_000);
        assertThat(THIRD.prize()).isEqualTo(1_500_000);
        assertThat(FOURTH.prize()).isEqualTo(50_000);
        assertThat(FIFTH.prize()).isEqualTo(5_000);
        assertThat(NONE.prize()).isEqualTo(0);
    }

    @Test
    void 보너스는_5개_일치_에서만_영향을_준다() {
        // given & then
        int[] counts = {6, 4, 3, 2, 1, 0};
        for (int count : counts) {
            assertThat(LottoRank.of(count, true)).
                    isSameAs(LottoRank.of(count, false));
        }

        // when
        assertThat(LottoRank.of(5, true)).isSameAs(SECOND);
        assertThat(LottoRank.of(5, false)).isSameAs(THIRD);
    }
}
