package com.luseen.babylon;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class LocaleConfig {

    static final String PRIMARY_FILE_NAME = "_string.xml";
    static final String TEMP_FILE_NAME = "temp.xml";
    static final String DIR_NAME = "/babylon/";

    private static volatile LocaleConfig Instance = null;

    private SparseArray<String> currentLocaleValues = new SparseArray<>();
    private String currentLocale = "en";// TODO: 14.07.2017

    static LocaleConfig getInstance() {
        LocaleConfig localInstance = Instance;
        if (localInstance == null) {
            synchronized (LocaleConfig.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new LocaleConfig();
                }
            }
        }
        return localInstance;
    }

    void init(final Context context, String fileUrl) {

        boolean isFileExist = FileHelper.isFileExist(context, currentLocale);
        Log.e("init ", "" + isFileExist);
        if (isFileExist) {
            File localeFile = FileHelper.getLocaleFile(context, currentLocale);
            currentLocaleValues = LocaleParser.parseFile(context, localeFile, true);
        }

        FileDownloader.downloadFile(fileUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Failed to download file: " + response);
                }
                File tempFile = FileHelper.createTempFile(context, response.body().bytes());
                String locale = LocaleParser.getLocale(tempFile);
                File primaryFile = FileHelper.createPrimaryFile(context, tempFile, locale);
                Log.e("onResponse ", "" + locale);

                // TODO: 14.07.2017 Add onReadyListener
                currentLocaleValues = LocaleParser.parseFile(context, primaryFile, true);
            }
        });
    }

    String getStringInternal(Context context, int res) {
        String value = currentLocaleValues.get(res);
        if (value == null) {
            value = context.getResources().getString(res);
        }
        return value;
    }

    String getCurrentLocale() {
        return currentLocale;
    }

    void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
        //currentLocaleValues = LocaleParser.parseFile(context, primaryFile, true);
    }
}
