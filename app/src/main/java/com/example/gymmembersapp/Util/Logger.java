package com.example.gymmembersapp.Util;

import android.util.Log;

public class Logger {
    private static final String ERROR_TAG = "Tag_Error";
    private static final String DEBUG_TAG = "Tag_Debug";

    public static void logError(Throwable throwable){
        Log.e(ERROR_TAG, throwable.getMessage());
    }

    public static void logDebug(String log){
        Log.d(DEBUG_TAG, log);
    }
}
