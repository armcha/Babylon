package com.luseen.babylon;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class Babylon {

    public static Babylon.Builder startInit(Context context) {
        return new Babylon.Builder(context);
    }

    public static String getStringById(Context context, int resId) {
        return LocaleConfig.getInstance().getStringInternal(context, resId);
    }

    public static void onConfigurationChanged(Configuration newConfig) {
        // TODO: 14.07.2017 getLocale
        LocaleConfig localeConfig = LocaleConfig.getInstance();
        String newLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            newLocale = newConfig.getLocales().get(0).getLanguage();
        } else {
            newLocale = newConfig.locale.getLanguage();
        }
        boolean isLocaleChanged = Utils.isLocaleChanged(localeConfig.getCurrentLocale(), newLocale);
        if (isLocaleChanged) {
            localeConfig.setCurrentLocale(newLocale);
        }
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
