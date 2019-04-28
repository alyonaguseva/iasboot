package ru.rushydro.vniig.ias;

/**
 * Created by yazik on 21.08.2017.
 */
public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty() && !str.equalsIgnoreCase("null");
    }

}
