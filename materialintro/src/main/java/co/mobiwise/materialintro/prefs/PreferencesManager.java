package co.mobiwise.materialintro.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mertsimsek on 29/01/16.
 */
public class PreferencesManager {

    private static final String PREFERENCES_NAME = "material_intro_preferences";

    private SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean isDisplayed(String id){
        return sharedPreferences.getBoolean(id, false);
    }

    public void setDisplayed(String id){
        sharedPreferences.edit().putBoolean(id,true).apply();
    }

    public void reset(String id){
        sharedPreferences.edit().putBoolean(id, false).apply();
    }

    public void resetAll(){
        sharedPreferences.edit().clear().apply();
    }
}
