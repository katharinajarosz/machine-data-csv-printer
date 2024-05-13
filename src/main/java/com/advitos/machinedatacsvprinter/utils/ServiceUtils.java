package com.advitos.machinedatacsvprinter.utils;

import com.advitos.machinedatacsvprinter.model.SWVersion;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {

    public boolean matchFileVersion(String fileName) {

        String version = fileName.substring(38);
        String regex1 = "([3]{1}|[4-9]{1}|[0-9]{2,})\\.([7-9]{1}|[0-9]{2,})\\.\\d+_.*";
        String regex2 = "3\\.6\\.\\d+_t9([0-9][4-9]|[1-9][0-9]).*";

        if (version.matches(regex1)) {
            return true;
        }
        return version.matches(regex2);
    }

    public boolean matchVersion(String fileName) {

        String[] versionArray = fileName.split("_");
        String b = versionArray[7];
        String version = b.substring(7);
        String regex = "([3][.]([9]|[0-9]{2,})[.]\\d+?)|([4-9][.]\\d+?[.]\\d+?)";

        return version.matches(regex);
    }

    public SWVersion matchVersionEnum(String fileName) {

        String[] versionArray = fileName.split("_");
        String numberSeven = versionArray[7];
        String swVersion = numberSeven.substring(7);
        String[] versionElements = swVersion.split("\\.");

        int versionValue = Integer.parseInt(versionElements[0].concat(versionElements[1]).concat(versionElements[2]));
        int enumValue;
        if (versionValue >= 3120) {
            enumValue = 3120;
        } else if (versionValue >= 390) {
            enumValue = 390;
        } else enumValue = 362;

        return SWVersion.valueOfVersion(enumValue);
    }
}
