package ar.edu.itba.rutinapp_mobile_app.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.ActivityMainBinding;

public class MainNavActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNav.setOnClickListener(view -> {
            binding.bottomNav.setEnabled(false);

            NavHostFragment navHostFragment = (NavHostFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigationView);
            NavController navController = navHostFragment.getNavController();

//            HomeFragmentDirections.ActionHomeFragmentToSecondaryFragment action = HomeFragmentDirections.actionHomeFragmentToSecondaryFragment(10);
//            navController.navigate(action);
        });

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
        bottomNavigationView = findViewById(R.id.bottomNav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigationView);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());
    }

    public void setNavigationVisibility(boolean b) {
        if (b) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
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
