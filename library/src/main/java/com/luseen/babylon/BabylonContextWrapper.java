package com.luseen.babylon;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

/**
 * Created by Chatikyan on 20.07.2017.
 */

public class BabylonContextWrapper extends ContextWrapper {

    private LayoutInflater inflater;
    private Resources resources;

    public static BabylonContextWrapper wrap(Context base) {
        return new BabylonContextWrapper(base);
    }

    public BabylonContextWrapper(Context base) {
        super(base);
        Resources baseRes = base.getResources();
        resources = new BabylonResources(baseRes.getAssets(), baseRes.getDisplayMetrics(), baseRes.getConfiguration());
    }

    @Override
    public Resources getResources() {
        return resources;
    }

    @Override
    public Object getSystemService(String name) {
        if (name.equals(LAYOUT_INFLATER_SERVICE)) {
            if (inflater == null)
                inflater = LayoutInflater.from(this.getBaseContext()).cloneInContext(this);
            return inflater;
        }
        return super.getSystemService(name);
    }
}
