package lotto.domain;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import model.domain.BonusNumber;
import model.domain.Lotto;
import model.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BonusNumberTest {

    private static final String ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1~45 사이입니다";
    private static final String ERR_MSG_BONUS_NUMBER_DUPLICATION = "[ERROR] 보너스 번호가 당첨번호와 중복됩니다";
    @Nested
    class 정상적인_경우 {

        @Test
        void 유효범위_내_중복아님_생성성공() {
            // given
            WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            // when
            BonusNumber bonus = new BonusNumber(7, winning);

            // then
            assertThat(bonus).isNotNull();
        }

        @ParameterizedTest(name = "[{index}] 경계값 OK: {0}")
        @ValueSource(ints = {1, 45})
        void 경계값_1과_45_허용(int value) {
            // given
            WinningNumbers winning = new WinningNumbers(List.of(2, 3, 4, 5, 6, 7));

            // when & then
            assertThatCode(() -> new BonusNumber(value, winning))
                    .doesNotThrowAnyException();
        }

        @Test
        void isContainedIn_로또에_보너스번호가_있으면_true() {
            // given
            WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonus = new BonusNumber(7, winning);

            // when
            Lotto lottoWith = new Lotto(List.of(7, 11, 12, 13, 14, 15));

            // then
            assertThat(bonus.isContainedIn(lottoWith)).isTrue();
        }

        @Test
        void isContainedIn_로또에_보너스번호가_없으면_false() {
            // given
            WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonus = new BonusNumber(7, winning);

            // when
            Lotto lottoWithout = new Lotto(List.of(8, 9, 10, 11, 12, 13));

            // then
            assertThat(bonus.isContainedIn(lottoWithout)).isFalse();
        }
    }

    @Nested
    class 예외_처리 {
        @ParameterizedTest(name = "[{index}] 범위밖 → 예외: {0}")
        @ValueSource(ints = {0, 46, -1, 100})
        void 범위밖이면_예외(int value) {
            WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            assertThatThrownBy(() -> new BonusNumber(value, winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERR_MSG_LOTTO_NUMBER_OUT_OF_RANGE);
        }


        @Test
        void 당첨번호와_중복이면_예외() {
            // given
            WinningNumbers winning = new WinningNumbers(List.of(1, 2, 7, 8, 9, 10));

            // when & then
            assertThatThrownBy(() -> new BonusNumber(7, winning))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERR_MSG_BONUS_NUMBER_DUPLICATION);
        }
    }
}
