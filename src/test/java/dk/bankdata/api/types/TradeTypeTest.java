package dk.bankdata.api.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dk.bankdata.api.exceptions.UnknownTradeTypeException;

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
        public void shouldThrowException() {
            UnknownTradeTypeException exception = assertThrows(UnknownTradeTypeException.class, () -> TradeType.byCharCode('X'));
            assertTrue(exception.getMessage().endsWith("'X'"));

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
        public void shouldGetEmptyOptional() {
            UnknownTradeTypeException exception = assertThrows(UnknownTradeTypeException.class, () -> TradeType.byIntCode(9));
            assertTrue(exception.getMessage().endsWith("'9'"));
        }
    }
}
