package ar.edu.itba.rutinapp_mobile_app.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import ar.edu.itba.rutinapp_mobile_app.AppPreferences;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.ActivityMainBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class MainNavActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;
    private UserViewModel userViewModel;
    ActivityMainBinding binding;
    DrawerLayout drawer;
    Toolbar toolbar;
    AppPreferences preferences;
    private BottomNavigationView bottomNavigationView;
    private UserViewModel userviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new AppPreferences(this.getApplication());
        setAppMode();

        setContentView(R.layout.activity_main);

        setUpBottomNavigation();

//        setSupportActionBar(findViewById(R.id.main_toolbar));

        userviewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userviewModel.setUserData();

//        preferences = new AppPreferences(this.getApplication());


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
/*        setContentView(R.layout.activity_main_nav);
        Log.e("HOME", "binding");

        setSupportActionBar(findViewById(R.id.Toolbar_menu));
//        setSupportActionBar(binding.appBarMain.ToolbarMenu);
        Log.e("HOME", "seteo action bar");

        //toolbar = findViewById(R.id.Toolbar_menu);
        //setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawer = findViewById(R.id.main);
        navigationView = findViewById(R.id.navigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home_fragment, R.id.searchRoutine_fragment,
                R.id.profile_fragment, R.id.favourite_fragment, R.id.settings_fragment)
                .setOpenableLayout(drawer)
                .build();

//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        userViewModel.setUserData();
/*
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.header_title, R.string.header_title);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.main);


        NavController navController = Navigation.findNavController(this, R.id.nav_fragment_content);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

*/
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


    public void setUpBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainNavFragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());
    }

    public void setAppMode() {
        if (preferences.loadNightModeState())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.navigationView);
        return navController.navigateUp();
//        NavController navController = Navigation.findNavController(this, R.id.nav_fragment_content);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
    }

    public void setNavigationVisibility(boolean b) {
        if (b) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        ActionBar actionBar = getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setDisplayShowTitleEnabled(false);
//        getMenuInflater().inflate(R.menu.appbar_menu, menu);
//
//        return true;
//    }

    public void showUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    @Override
//    public void onBackPressed() {
//        if(drawer.isDrawerOpen(GravityCompat.START)){
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        else{
//            super.onBackPressed();
//        }
//    }
}
