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
        @ValueSource(ints = {1000, 12000, 3000, 18000})
        void 생성_성공_및_로또_개수_계산(int userInput) {
            // given & when
            PurchaseAmount amount = new PurchaseAmount(userInput);
            int expectedCount = userInput / LOTTO_PRICE;

            // then
            assertThat(amount.purchasableCount()).isEqualTo(expectedCount);
        }
    }

    @Nested
    class 예외_상황 {

        @ParameterizedTest(name = "[{index}] 1000원 배수 아님: \"{0}\"")
        @ValueSource(ints = {1300, 2500, 3500})
        void 천원으로_나누어_떨어지지_않으면_거부(int userInput) {
            assertThatThrownBy(() -> new PurchaseAmount(userInput))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 행동_로또_개수_반환 {

        @Test
        void 로또_개수_반환() {
            PurchaseAmount amount = new PurchaseAmount(100_000_000);
            assertThat(amount.purchasableCount()).isEqualTo(100000);
        }
    }
}
