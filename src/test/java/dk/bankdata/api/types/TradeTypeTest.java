package dk.bankdata.api.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TradeTypeTest {

    @Nested
    @DisplayName("byCharCode")
    class ByCharCode {

        @Test
        public void shouldGetTradeType() {
            assertEquals(TradeType.IMMEDIATE, TradeType.byCharCode('S'));
            assertEquals(TradeType.MARKET, TradeType.byCharCode('M'));
            assertEquals(TradeType.LIMIT, TradeType.byCharCode('L'));
        }

        @Test
        public void shouldReturnNullIfNotExists() {
            assertEquals(null, TradeType.byCharCode('X'));
        }
    }

    @Nested
    @DisplayName("byNumberCode")
    class ByNumberCodBe {

        @Test
        public void shouldGetTradeType() {
            assertEquals(TradeType.IMMEDIATE, TradeType.byIntCode(1));
            assertEquals(TradeType.MARKET, TradeType.byIntCode(4));
            assertEquals(TradeType.LIMIT, TradeType.byIntCode(5));
        }

        @Test
        public void shouldReturnNullIfNotExists() {
            assertEquals(null, TradeType.byIntCode('0'));
        }
    }
}
