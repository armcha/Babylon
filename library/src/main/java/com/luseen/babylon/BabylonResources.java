package com.luseen.babylon;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Chatikyan on 20.07.2017.
 */

public class BabylonResources extends Resources {

    private LocaleConfig localeConfig = LocaleConfig.getInstance();

    // TODO: 20.07.2017 public constructor is deprecated, we need to do something, maybe createConfigurationContext will help us
    BabylonResources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
    }

    @NonNull
    @Override
    public String getString(int id) throws NotFoundException {
        return this.getText(id).toString();
    }

    @NonNull
    @Override
    public CharSequence getText(int id) throws NotFoundException {
        String valueFromMap = localeConfig.getValueFromMap(id);
        if (valueFromMap == null)
            return super.getText(id);
        return valueFromMap;
    }

    @Override
    public CharSequence getText(int id, CharSequence def) {
        return this.getText(id);
    }

    @NonNull
    @Override
    public String getString(int id, Object... formatArgs) throws NotFoundException {
        return this.getText(id).toString();
    }
}
