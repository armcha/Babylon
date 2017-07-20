package com.luseen.babylon;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.luseen.babylon.LocaleConfig.DIR_NAME;
import static com.luseen.babylon.LocaleConfig.PRIMARY_FILE_NAME;
import static com.luseen.babylon.LocaleConfig.TEMP_FILE_NAME;

/**
 * Created by Chatikyan on 14.07.2017.
 */

class FileHelper {

    private FileHelper() {
        throw new BabylonException("Private constructor cannot be accessed");
    }

    static boolean isFileExist(Context context, String locale) {
        File file = new File(context.getCacheDir().getParent() + DIR_NAME, locale + PRIMARY_FILE_NAME);
        return file.exists();
    }

    static File getLocaleFile(Context context, String locale) {
        return new File(context.getCacheDir().getParent() + DIR_NAME, locale + PRIMARY_FILE_NAME);
    }

    static File createPrimaryFile(Context context, File tempFile, String locale) {
        try {
            byte bytes[] = new byte[(int) tempFile.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tempFile));
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(bytes);
            File file = new File(context.getCacheDir().getParent() + DIR_NAME, locale + PRIMARY_FILE_NAME);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
            tempFile.delete();
            return file;
        } catch (Exception e) {
            Log.e("CreatePrimaryFile ", "Exception is - " + e.getMessage());
        }
        return null;
    }

    static File createTempFile(Context context, byte[] bytes) {
        try {
            File root = new File(context.getCacheDir().getParent() + DIR_NAME);
            if (!root.exists()) {
                root.mkdirs();
            }
            File tempFile = new File(context.getCacheDir().getParent() + DIR_NAME, TEMP_FILE_NAME);
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            outputStream.write(bytes);
            outputStream.close();
            return tempFile;
        } catch (Exception e) {
            Log.e("CreateTempFile ", "Exception is - " + e.getMessage());
        }

        return null;
    }
}
