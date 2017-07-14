package com.luseen.babylon;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class FileDownloader {

    private FileDownloader() {
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    static void downloadFile(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
