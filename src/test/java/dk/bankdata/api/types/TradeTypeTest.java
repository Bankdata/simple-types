package dk.bankdata.api.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TradeTypeTest {

    @Nested
    @DisplayName("byCharCode")
    class ByCharCode {

        @Test
        public void shouldGetTradeType() {
            assertEquals(TradeType.IMMEDIATE, TradeType.byCharCode('S').get());
            assertEquals(TradeType.MARKET, TradeType.byCharCode('M').get());
            assertEquals(TradeType.LIMIT, TradeType.byCharCode('L').get());
        }

        @Test
        public void shouldGetEmptyOptional() {
            assertFalse(TradeType.byCharCode('X').isPresent());
            assertFalse(TradeType.byCharCode('s').isPresent());
        }
    }

    @Nested
    @DisplayName("byNumberCode")
    class ByNumberCodBe {

        @Test
        public void shouldGetTradeType() {
            assertEquals(TradeType.IMMEDIATE, TradeType.byNumberCode(1).get());
            assertEquals(TradeType.MARKET, TradeType.byNumberCode(4).get());
            assertEquals(TradeType.LIMIT, TradeType.byNumberCode(5).get());
        }

        @Test
        public void shouldGetEmptyOptional() {
            assertFalse(TradeType.byNumberCode(9).isPresent());
        }
    }
}
