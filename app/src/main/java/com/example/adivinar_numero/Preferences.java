package com.example.adivinar_numero;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String FILE_PREFERENCES = "preferences";
    public static final String KEY_ATTEMPTS = "attempts";

    //guardar intentos
    public static void saveAttempts (Context context, int attempts) {
        SharedPreferences sp = context.getSharedPreferences(FILE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_ATTEMPTS, attempts);
        editor.commit();
    }

    public static int loadAttempts(Context context) {
        int attempts = 0;
        SharedPreferences sp = context.getSharedPreferences(FILE_PREFERENCES, Context.MODE_PRIVATE);
        attempts = sp.getInt(KEY_ATTEMPTS, 0);
        return attempts;
    }
}
