package ar.edu.itba.rutinapp_mobile_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ar.edu.itba.rutinapp_mobile_app.AppPreferences;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.fragment.LoginFragment;
import ar.edu.itba.rutinapp_mobile_app.fragment.WelcomeFragment;
import ar.edu.itba.rutinapp_mobile_app.fragment.WelcomeFragmentDirections;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        AppPreferences preferences = new AppPreferences(this.getApplication());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("RoutineId");

        if(id != null) {
            NavController controller = Navigation.findNavController(this, R.id.nav_host_welcome);
            WelcomeFragmentDirections.ActionWelcomeFragmentToLoginFragment action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment();
            controller.navigate(action.setRoutineId(getIntent().getStringExtra("RoutineId")));
        }
    }
}
