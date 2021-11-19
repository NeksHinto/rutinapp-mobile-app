package ar.edu.itba.rutinapp_mobile_app.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.navigation.NavigationView;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.ActivityMainBinding;
import ar.edu.itba.rutinapp_mobile_app.fragment.HomeFragmentDirections;

public class MainNavActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private NavigationView navigationView;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationView = findViewById(R.id.navigationView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home_fragment, R.id.searchRoutine_fragment,
                R.id.profile_fragment, R.id.favourite_fragment, R.id.settings_fragment)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment_content);
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
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
        NavController navController = Navigation.findNavController(this, R.id.navigationView);
        return navController.navigateUp();
    }

    public void setUpBottomNavigation() {
        navigationView = findViewById(R.id.buttonNav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigationView);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(navigationView,
                navHostFragment.getNavController());
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

        return true;
    }

    public void showUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideUpButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
