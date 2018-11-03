package com.ericmcconkie.watchface.settings;

public enum SettingType {
    TACH (0),TOP (1),DATE (2);
    private final int value;
    SettingType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SettingType getSettingType(int val) {
        for (SettingType l : SettingType.values()) {
            if (l.value == val) return l;
        }
        throw new IllegalArgumentException("SettingType not found!");
    }
}
