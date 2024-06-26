package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import ar.edu.itba.rutinapp_mobile_app.R;

public class WelcomeFragment extends Fragment {

    private String arg;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            arg = getArguments().getString("RoutineId");
        } else {
            arg = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootWelcome = inflater.inflate(R.layout.fragment_welcome, container, false);

        MaterialButton logInBtn = rootWelcome.findViewById(R.id.welcomeLoginButton);
        MaterialButton registerBtn = rootWelcome.findViewById(R.id.registerButton);

        logInBtn.setOnClickListener(view -> {
            NavController welcomeNavController = Navigation.findNavController(view);
            WelcomeFragmentDirections.ActionWelcomeFragmentToLoginFragment action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment();
            action.setRoutineId(arg);
            welcomeNavController.navigate(action);
        });

        registerBtn.setOnClickListener(view -> {
            NavController welcomeNavController = Navigation.findNavController(view);
            WelcomeFragmentDirections.ActionWelcomeFragmentToRegisterFragment action = WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment();
            action.setRoutineId(arg);
            welcomeNavController.navigate(action);
        });

        return rootWelcome;
    }
}