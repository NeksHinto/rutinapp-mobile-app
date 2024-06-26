package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.databinding.ActivityMainBinding;
import ar.edu.itba.rutinapp_mobile_app.databinding.ProfileFragmentBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private UserViewModel userViewModel;
    private ProfileFragmentBinding binding;

    private TextView username;
    private TextView lastName;
    private ImageView profilePic;
    private View view;

    private MainNavActivity main;



    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = ProfileFragmentBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        username = binding.userName;
        lastName = binding.lastName;
        profilePic = binding.profileImage;

        binding.settingProfile.setOnClickListener(view -> {

            NavHostFragment.findNavController(this).navigate(R.id.action_profileFragment_to_settings_fragment);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        seeProfile();
    }

    private void seeProfile() {
        userViewModel.getUserData().observe(getViewLifecycleOwner(), userInfo -> {
            if (userInfo != null) {
                if (!userInfo.getAvatarUrl().equals("")) {

                }
            }
        });
    }
/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

 */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.app_bar_settings).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.app_bar_settings) {
            settings();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void settings() {
        Navigation.findNavController(view).navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment);
    }
    
 */
}