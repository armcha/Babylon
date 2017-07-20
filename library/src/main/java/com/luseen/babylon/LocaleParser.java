package com.luseen.babylon;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Chatikyan on 14.07.2017.
 */

public class LocaleParser {

    private LocaleParser() {
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    static SparseArray<String> parseFile(Context context, File file, boolean preserveEscapes) {

        FileInputStream stream = null;
        try {
            if (!file.exists()) {
                return new SparseArray<>();
            }
            SparseArray<String> stringMap = new SparseArray<>();
            XmlPullParser parser = Xml.newPullParser();
            stream = new FileInputStream(file);
            parser.setInput(stream, "UTF-8");
            int eventType = parser.getEventType();
            String name = null;
            String value = null;
            String attrName = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    name = parser.getName();
                    int c = parser.getAttributeCount();
                    if (c > 0) {
                        attrName = parser.getAttributeValue(0);
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (attrName != null) {
                        value = parser.getText();
                        if (value != null) {
                            value = value.trim();
                            if (preserveEscapes) {
                                value = value.replace("<", "&lt;")
                                        .replace(">", "&gt;")
                                        .replace("'", "\\'")
                                        .replace("& ", "&amp; ");
                            } else {
                                value = value.replace("\\n", "\n")
                                        .replace("\\", "")
                                        .replace("&lt;", "<");
                            }
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    value = null;
                    attrName = null;
                    name = null;
                }
                if (name != null && name.equals("string") && value != null && attrName != null && value.length() != 0 && attrName.length() != 0) {
                    // int key = stringValues.get(attrName);
                   // Log.e("getLocaleFileStrings ", "" + attrName);

                    int resId = context.getResources().getIdentifier(attrName, "string", context.getPackageName());
                    stringMap.put(resId, value);
                    //Log.e("getLocaleFileStrings ", "" + resId + ".-------- value " + value);

                    name = null;
                    value = null;
                    attrName = null;
                }
                eventType = parser.next();
            }
            return stringMap;
        } catch (Exception e) {
            Log.e("ParseFile ", "Exception is - " + e.getMessage());
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ParseFile ", "Exception is - " + e.getMessage());
                }
            }
        }
        return new SparseArray<>();
    }

    static String getLocale(File file) {
        FileInputStream stream = null;
        try {
            if (!file.exists()) {
                return "hy";
            }
            XmlPullParser parser = Xml.newPullParser();
            stream = new FileInputStream(file);
            parser.setInput(stream, "UTF-8");
            int eventType = parser.getEventType();
            String name = null;
            String value = null;
            String attrName = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    name = parser.getName();
                    int c = parser.getAttributeCount();
                    if (c > 0) {
                        attrName = parser.getAttributeValue(0);
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (attrName != null) {
                        value = parser.getText();
                        if (value != null) {
                            value = value.trim();
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    value = null;
                    attrName = null;
                    name = null;
                }
                if (name != null && name.equals("string") && value != null && attrName != null && value.length() != 0 && attrName.length() != 0) {
                    if (attrName.equalsIgnoreCase("locale"))
                        return value;

                    name = null;
                    value = null;
                    attrName = null;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ParseFile ", "Exception is - " + e.getMessage());
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ParseFile ", "Exception is - " + e.getMessage());
                }
            }
        }
        return "hy";
    }
}
