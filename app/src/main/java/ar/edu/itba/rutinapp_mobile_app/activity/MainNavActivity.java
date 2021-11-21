package ar.edu.itba.rutinapp_mobile_app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import ar.edu.itba.rutinapp_mobile_app.AppPreferences;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.ActivityMainBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class MainNavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;
    private UserViewModel userViewModel;
    ActivityMainBinding binding;
    DrawerLayout drawer;
    Toolbar toolbar;
    AppPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = new AppPreferences(this.getApplication());

        Log.e("HOME", "Intento de crear HOME");

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        Log.e("HOME", "binding");

        setSupportActionBar(findViewById(R.id.Toolbar_menu));
        Log.e("HOME", "seteo action bar");

        //toolbar = findViewById(R.id.Toolbar_menu);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.main);
        navigationView = findViewById(R.id.navigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home_fragment, R.id.searchRoutine_fragment,
                R.id.profile_fragment, R.id.favourite_fragment, R.id.settings_fragment)
                .setOpenableLayout(drawer)
                .build();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setUserData();
/*
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.header_title, R.string.header_title);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.main);

*/
        NavController navController = Navigation.findNavController(this, R.id.nav_fragment_content);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


//        binding.buttonNav.setOnClickListener(view -> {
//            binding.buttonNav.setEnabled(false);
//
//            NavHostFragment navHostFragment = (NavHostFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.navigationView);
//            NavController navController = navHostFragment.getNavController();
//
//            HomeFragmentDirections.ActionHomeFragmentToSecondaryFragment action = HomeFragmentDirections.actionHomeFragmentToSecondaryFragment(10);
//            navController.navigate(action);
//        });

//        binding.to.setNavigationOnClickListener {
//            // Handle navigation icon press
//        }
//

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_fragment_content);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setNavigationVisibility(boolean b) {
        if (b) {
            navigationView.setVisibility(View.VISIBLE);
        } else {
            navigationView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        getMenuInflater().inflate(R.menu.appbar_menu, menu);

        return true;
    }

    public void showUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
