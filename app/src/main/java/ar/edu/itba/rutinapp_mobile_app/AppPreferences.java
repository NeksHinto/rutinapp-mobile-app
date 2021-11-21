package ar.edu.itba.rutinapp_mobile_app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String PREFERENCES_NAME = "my-preferences";
    private final String AUTH_TOKEN = "auth_token";
    private final SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTH_TOKEN, token);
        editor.apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString(AUTH_TOKEN, null);
    }

    public void switchToDarkMode(Boolean state){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode",state);
        editor.apply();
    }

    public Boolean loadNightModeState(){
        return sharedPreferences.getBoolean("NightMode",false);
    }
}
