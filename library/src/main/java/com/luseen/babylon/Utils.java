package com.luseen.babylon;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class Utils {

    private Utils() {
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    static boolean isLocaleChanged(String currentLocale,String newLocale){
        return !currentLocale.equals(newLocale);
    }
}
