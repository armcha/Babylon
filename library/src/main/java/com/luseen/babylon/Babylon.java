package com.luseen.babylon;

import android.content.Context;

import java.util.Locale;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class Babylon {

    public static Babylon.Builder startInit(Context context) {
        return new Babylon.Builder(context);
    }

    public static void setCurrentLocale(Context context, Locale locale) {
        LocaleConfig localeConfig = LocaleConfig.getInstance();
        String newLocale = locale.getLanguage();
        boolean isLocaleChanged = Utils.isLocaleChanged(localeConfig.getCurrentLocale(), newLocale);
        if (isLocaleChanged) {
            localeConfig.setCurrentLocale(context, newLocale);
        }
    }

    public static void setStringReadyListener(StringReadyListener stringReadyListener) {
        LocaleConfig.getInstance().setStringReadyListener(stringReadyListener);
    }

    public static class Builder {

        private Context context;
        private String fileUrl;

        private Builder() {
        }

        private Builder(Context context) {
            this.context = context;
        }

        public Builder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public void init() {
            Context context = this.context.getApplicationContext();
            this.context = null; // Clear to prevent leaks.
            LocaleConfig.getInstance().init(context, fileUrl);
        }
    }
}
