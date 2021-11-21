package ar.edu.itba.rutinapp_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavDeepLinkBuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.activity.WelcomeActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppPreferences preferences = new AppPreferences(this.getApplication());
        if (preferences.loadNightModeState())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new Handler().postDelayed(() -> {
            Intent appLinkIntent = getIntent();
            Uri appLinkData = appLinkIntent.getData();
            if (appLinkData != null) { // cuando inicio la aplicacion desde un link
                String routineId = appLinkData.getLastPathSegment();
                newActivity(preferences, routineId);
            } else { //Cuando inicio la aplicacion normalmente
                Intent intent;
                if (preferences.getAuthToken() != null) {
                    intent = new Intent(FirstActivity.this, MainNavActivity.class);
                } else {
                    intent = new Intent(FirstActivity.this, WelcomeActivity.class);
                }
                startActivity(intent);
            }
        }, 3000);

    }

    private void newActivity(AppPreferences preferences, String routineId) {
        Bundle bundle = new Bundle();
        bundle.putInt("routineId", Integer.parseInt(routineId));
        if (preferences.getAuthToken() != null) {
            new NavDeepLinkBuilder(this).setComponentName(MainNavActivity.class).setGraph(R.navigation.nav_graph).setDestination(R.id.searchRoutine_fragment).setArguments(bundle).createTaskStackBuilder().startActivities();
            return;
        }
        Intent auxIntent = new Intent(FirstActivity.this, WelcomeActivity.class);
        putAndStart(routineId, auxIntent);
    }

    private void putAndStart(String routineId, Intent intent) {
        intent.putExtra("RoutineId", routineId);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish(); //elimino la actividad del stack
    }
}