package com.advitos.machinedatacsvprinter.model;

import java.util.HashMap;
import java.util.Map;

public enum SWVersion {
    VERSION_3_6_2(362),
    VERSION_3_9_0(390),
    VERSION_3_12_0(3120);
    private static final Map<Integer, SWVersion> BY_VALUE = new HashMap<>();

    static {
        for (SWVersion version : values()) {
            BY_VALUE.put(version.versionValue, version);
        }
    }

    public final int versionValue;

    SWVersion(int versionValue) {
        this.versionValue = versionValue;
    }

    public static SWVersion valueOfVersion(int versionValue) {
        return BY_VALUE.get(versionValue);
    }

}
