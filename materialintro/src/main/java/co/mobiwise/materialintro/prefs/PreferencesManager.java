package co.mobiwise.materialintro.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mertsimsek on 29/01/16.
 */
public class PreferencesManager {

    private static final String PREFERENCES_NAME = "material_intro_preferences";

    private final SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Intros without a usage id are never persisted,
     * so they are never reported as displayed.
     */
    public boolean isDisplayed(String id){
        return id != null && sharedPreferences.getBoolean(id, false);
    }

    public void setDisplayed(String id){
        if (id == null) return;
        sharedPreferences.edit().putBoolean(id,true).apply();
    }

    public void reset(String id){
        if (id == null) return;
        sharedPreferences.edit().remove(id).apply();
    }

    public void resetAll(){
        sharedPreferences.edit().clear().apply();
    }
}
