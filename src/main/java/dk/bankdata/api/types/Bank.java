package dk.bankdata.api.types;

import java.util.HashMap;
import java.util.Map;

public enum Bank {
    SYDBANK(1, 8079),
    NORDFYNSBANK(8, 6868),
    SKJERNBANK(9, 7780),
    DJURSLANDSBANK(13, 7320),
    KREDITBANKEN(15, 7930),
    ALMBRANDBANK(23, 7681),
    RINGKJOBINGLANDBOBANK(24, 7670),
    SPKS(42, 522),
    JYSKEBANK(51, 7858);

    private static final Map<Integer, Bank> ID = new HashMap<>();
    private static final Map<Integer, Bank> REGNO = new HashMap<>();

    static {
        for (Bank bank : values()) {
            ID.put(bank.id, bank);
            REGNO.put(bank.mainRegNo, bank);
        }
    }

    private int id;
    private int mainRegNo;

    Bank(int id, int mainRegNo) {
        this.id = id;
        this.mainRegNo = mainRegNo;
    }

    public static Bank byId(int id) {
        return ID.get(id);
    }

    public static Bank byMainRegNo(int mainRegNo) {
        return REGNO.get(mainRegNo);
    }

    public int getId() {
        return id;
    }

    public int getMainRegNo() {
        return mainRegNo;
    }
}
