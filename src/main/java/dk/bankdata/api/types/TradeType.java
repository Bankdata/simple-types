package dk.bankdata.api.types;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents different trade types.
 */
public enum TradeType {

    // Unknown number code for EMISSIONS and INTERNAL
    IMMEDIATE('S', 1), MARKET('M', 4), LIMIT('L', 5)/*, EMISSIONS('E'), INTERNAL('I')*/; 

    private char charCode;
    private int intCode;

    TradeType(char charCode, int intCode) {
        this.charCode = charCode;
        this.intCode = intCode;
    }

    /**
     * Maps from Host system character code to TradeType.
     * @param charCode Host system character code.
     * @return corresponding TradeType or empty Optional if no matching TradeType is found.
     */
    public static Optional<TradeType> byCharCode(char charCode) {
        return TradeType.stream()
            .filter(tradeType -> tradeType.getCharCode() == charCode)
            .findFirst();
    }
        
    /**
     * Maps from Host system integer code to TradeType.
     * @param intCode Host system integer code.
     * @return corresponding TradeType or empty Optional if no matching TradeType is found.
     */
    public static Optional<TradeType> byIntCode(int intCode) {
        return TradeType.stream()
            .filter(tradeType -> tradeType.getIntCode() == intCode)
            .findFirst();
    }

    public static Stream<TradeType> stream() {
        return Stream.of(TradeType.values());
    }

    public char getCharCode() {
        return this.charCode;
    }

    public int getIntCode() {
        return this.intCode;
    }
}