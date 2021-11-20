package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.edu.itba.rutinapp_mobile_app.AppPreferences;
import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentSettingsBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    AppPreferences preferences;
    SwitchCompat darkModeSwitch;
    UserViewModel userViewModel;

    View view;

    private MainNavActivity main;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

//    public static SettingsFragment newInstance(String param1, String param2) {
//        SettingsFragment fragment = new SettingsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        preferences = new AppPreferences(getActivity().getApplication());
        binding.logout.setOnClickListener(v -> logout());
        darkModeSwitch = binding.darkModeSwitch;
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            darkModeSwitch.setChecked(true);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.switchToDarkMode(isChecked);
            restartApp();
        });

        main = (MainNavActivity) getActivity();

        main.showUpButton();
        main.setNavigationVisibility(false);
        return view;

    }

    private void logout() {
        userViewModel.logout();

        Intent intent = new Intent(getActivity(), WelcomeFragment.class);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        // Elimino la actividad del stack
        getActivity().finish();
    }

    public void restartApp() {
        Intent i = new Intent(getActivity(), MainNavActivity.class);
        getActivity().finish();
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        main.hideUpButton();
        super.onDestroyView();
    }
}