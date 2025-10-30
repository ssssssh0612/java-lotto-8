package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import model.domain.PurchaseAmount;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)

class PurchaseAmountTest {

    private static final int LOTTO_PRICE = 1_000;

    @Nested
    class 정상적인_경우 {

        @ParameterizedTest(name = "[{index}] \"{0}\" -> 정수이며 1000원 배수")
        @ValueSource(strings = {"1000", "12000", "3000", "18000"})
        void 생성_성공_및_로또_개수_계산(String userInput) {
            PurchaseAmount amount = PurchaseAmount.from(userInput);

            int expected = Integer.parseInt(userInput) / LOTTO_PRICE;
            assertThat(amount.purchasableCount()).isEqualTo(expected);
        }
    }

    @Nested
    class 예외_상황 {

        @ParameterizedTest(name = "[{index}] 숫자가 아님: \"{0}\"")
        @ValueSource(strings = {"1,000", "1_000", "1!300", "abc", "", " "})
        void 숫자가_아닌_문자열은_거부(String raw) {
            assertThatThrownBy(() -> PurchaseAmount.from(raw))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "[{index}] 1000원 배수 아님: \"{0}\"")
        @ValueSource(strings = {"1300", "2500", "3500"})
        void 천원으로_나누어_떨어지지_않으면_거부(String raw) {
            assertThatThrownBy(() -> PurchaseAmount.from(raw))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "[{index}] 허용하는 숫자 범위에 들어가지 않음 \"{0}\"")
        @ValueSource(strings = {"900", "2200000000"})
        void 숫자_범위에_들어가지_않으면_거부(String raw) {
            assertThatThrownBy(() -> PurchaseAmount.from(raw))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 행동_로또_개수_반환 {

        @Test
        void 로또_개수_반환() {
            PurchaseAmount amount = PurchaseAmount.from("8000");
            assertThat(amount.purchasableCount()).isEqualTo(8);
        }
    }
}
