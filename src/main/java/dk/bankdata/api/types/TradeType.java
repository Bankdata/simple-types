package dk.bankdata.api.types;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Represents different trade types.
 */
public enum TradeType {

    // Unknown int code for EMISSIONS('E') and INTERNAL('I')
    IMMEDIATE('S', 1), MARKET('M', 4), LIMIT('L', 5); 

    private static final Map<Integer, TradeType> INT_CODE = new HashMap<>();
    private static final Map<Character, TradeType> CHAR_CODE = new HashMap<>();

    static {
        for (TradeType tradeType : values()) {
            INT_CODE.put(tradeType.intCode, tradeType);
            CHAR_CODE.put(tradeType.charCode, tradeType);
        }
    }

    private char charCode;
    private int intCode;

    TradeType(char charCode, int intCode) {
        this.charCode = charCode;
        this.intCode = intCode;
    }

    /**
     * Maps from Host system character code to TradeType.
     * @param charCode Host system character code.
     * @return corresponding TradeType or null if not exist.
     */
    public static TradeType byCharCode(char charCode) {
        return CHAR_CODE.get(charCode);
    }
        
    /**
     * Maps from Host system integer code to TradeType.
     * @param intCode Host system integer code.
     * @return corresponding TradeType or null if not exist.
     */
    public static TradeType byIntCode(int intCode) {
        return INT_CODE.get(intCode);
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
