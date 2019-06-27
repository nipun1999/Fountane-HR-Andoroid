package com.fountane.www.fountanehrapp.Utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        if (str != null && str.length() > 0) {
            return false;
        }
        return true;
    }
}
