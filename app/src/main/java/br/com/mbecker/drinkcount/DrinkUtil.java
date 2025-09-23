package br.com.mbecker.drinkcount;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

public class DrinkUtil {
    public static void saveCounter(int value, String category, Activity activity) {
        SharedPreferences prefs = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("counter_" + category, value);
        editor.apply();
    }

    public static int loadCounter(String category, Activity activity) {
        SharedPreferences prefs = activity.getPreferences(Context.MODE_PRIVATE);
        return prefs.getInt("counter_" + category, 0);
    }
}
