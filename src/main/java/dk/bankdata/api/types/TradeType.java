package dk.bankdata.api.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum TradeType {

    IMMEDIATE('S', 1), MARKET('M', 4), LIMIT('L', 5)/*, EMISSIONS('E'), INTERNAL('I')*/;

    private char charCode;
    private int numberCode;

    TradeType(char charCode, int numberCode) {
        this.charCode = charCode;
        this.numberCode = numberCode;
    }

    public static Optional<TradeType> byCharCode(char charCode) {
        return TradeType.stream()
            .filter(tradeType -> tradeType.getCharCode() == charCode)
            .findFirst();
    }

    public static Optional<TradeType> byNumberCode(int numberCode) {
        return TradeType.stream()
            .filter(tradeType -> tradeType.getNumberCode() == numberCode)
            .findFirst();
    }

    public static Stream<TradeType> stream() {
        return Stream.of(TradeType.values());
    }

    public char getCharCode() {
        return this.charCode;
    }

    public int getNumberCode() {
        return this.numberCode;
    }
}