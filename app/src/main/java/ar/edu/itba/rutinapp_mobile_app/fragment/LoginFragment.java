package ar.edu.itba.rutinapp_mobile_app.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.activity.MainNavActivity;
import ar.edu.itba.rutinapp_mobile_app.databinding.LoginFragmentBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class LoginFragment extends Fragment {

    private TextInputLayout username;
    private TextInputLayout password;
    private TextView errorMsg;

    private UserViewModel viewModel;
    private FrameLayout loadingContainer;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LoginFragmentBinding binding = LoginFragmentBinding.inflate(getLayoutInflater());

        username = binding.loginUsername;
        password = binding.loginPassword;
        errorMsg = binding.loginErrorMsg;
        loadingContainer = binding.loadingContainer;

        Log.e("LOGIN", "EMPIEZO");

        View view = binding.getRoot();
        MaterialButton loginBtn = view.findViewById(R.id.login);

        loginBtn.setOnClickListener(v -> tryLogin());
        Log.e("LOGIN", "A VER");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    loadingContainer.setVisibility(View.VISIBLE);
                } else {
                    loadingContainer.setVisibility(View.GONE);
                }
            }
        });

    }

    private void tryLogin() {
        Log.e("LOGIN", "Intento de login");
        if(!isUsernameValid() || !isPasswordValid()) { return; }

        viewModel.trySignIn(username.getEditText().getText().toString(), password.getEditText().getText().toString());

        viewModel.getSignInError().observe(getViewLifecycleOwner(), errorModel -> {
            if(errorModel != null) {
                switch (errorModel.getCode()) {
                    case 4:
                        errorMsg.setText("Invalid username or password, try again.");
                        password.setError(" ");
                        username.setError(" ");
                        new Handler().postDelayed(() -> {
                            password.setError(null);
                            username.setError(null);
                            errorMsg.setText("");
                        }, 3000);
                        viewModel.setSignInError(null);
                        break;
                    case 8:
                        errorMsg.setText("Your account is not verified, redirecting to verification screen.");
                        new Handler().postDelayed(() -> Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginFragmentToEmailVerificationFragment()), 3000);
                        viewModel.setUserData();
                        viewModel.setSignInError(null);
                        break;
                    default:
                        errorMsg.setText("Something went wrong, try again.");
                        new Handler().postDelayed(() -> errorMsg.setText(""), 3000);
                        break;
                }
            }
        });

        Log.e("LOGIN", "no saltaron errores");

        viewModel.getToken().observe(getViewLifecycleOwner(), authToken -> {
            if (authToken != null) {
                Intent intent = new Intent(getActivity(), MainNavActivity.class);
                Bundle aux = getArguments();
                if (aux.get("RoutineId") != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("routineId", Integer.parseInt(aux.getString("RoutineId")));
                    Log.e("LOGIN", "Quiero ir a HOME");
                    new NavDeepLinkBuilder(getActivity()).setComponentName(MainNavActivity.class).setGraph(R.navigation.nav_graph).setArguments(bundle).createTaskStackBuilder().startActivities();
                } else {
                    startActivity(intent);
                }
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                getActivity().finish();
            }
        });
    }

    // User validation
    private boolean isUsernameValid() {
        String toValidate = username.getEditText().getText().toString().trim();

        if(toValidate.isEmpty()) {
            username.setError("Please enter username");
            return false;
        } else if(toValidate.length() > 20) {
            username.setError("Username too long");
            return false;
        }
        username.setError(null);
        username.setErrorEnabled(false);
        return true;
    }

    private boolean isPasswordValid() {
        String toValidate = password.getEditText().getText().toString().trim();

        if(toValidate.isEmpty()) {
            username.setError("Please enter password");
            return false;
        }

        password.setError(null);
        return true;
    }

}