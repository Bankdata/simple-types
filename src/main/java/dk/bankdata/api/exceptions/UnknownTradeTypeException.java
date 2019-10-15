package dk.bankdata.api.exceptions;

public class UnknownTradeTypeException extends RuntimeException {

    private static final String charCodeMsgTemplate = "No trade types exists for char code '%c'";
    private static final String intCodeMsgTemplate = "No trade types exists for int code '%d'";

    public UnknownTradeTypeException(char tradeTypeCode) {
        super(String.format(charCodeMsgTemplate, tradeTypeCode));    
    }

    public UnknownTradeTypeException(int tradeTypeCode) {
        super(String.format(intCodeMsgTemplate, tradeTypeCode));    
    }
}