package br.senai.sp.cotia.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {
    public static String getSimboloJog1(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simbolo_jog_1","X");
    }

    public static String getSimboloJog2(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("simbolo_jog_2","O");
    }

    public static String numeroRodadas(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("numRodadas","O");
    }

    public void salvarSimboloJog1(String simbolo, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simb_jog_1",simbolo);
        editor.commit();
    }

}
