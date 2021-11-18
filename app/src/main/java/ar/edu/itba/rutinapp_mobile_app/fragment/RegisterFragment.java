package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentRegisterBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class RegisterFragment extends Fragment {

    private TextInputLayout email;
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout confirmPassword;
    private TextView errorMsg;

    private FragmentRegisterBinding binding;
    private UserViewModel viewModel;
    private FrameLayout progressLoading;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(getLayoutInflater());

        email = binding.registerEmail;
        username = binding.registerUsername;
        password = binding.registerPassword;
        confirmPassword = binding.registerConfirmPassword;
        progressLoading = binding.loadingContainer2;

        View view = binding.getRoot();

        MaterialButton signUpBtn = binding.registerBtn;
        signUpBtn.setOnClickListener(v -> tryRegister());

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading != null) {
                if(isLoading) {
                    if(isLoading) {
                        progressLoading.setVisibility(View.VISIBLE);
                    } else {
                        progressLoading.setVisibility(View.GONE);
                    }
                }
            }
        });

        viewModel.getSignUpError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                if (error.getCode() == 2) {
                    errorMsg.setText(R.string.user_exists);
                    password.setError(" ");
                    username.setError(" ");
                    email.setError(" ");
                    new Handler().postDelayed(() -> {
                        password.setError(null);
                        username.setError(null);
                        email.setError(null);
                        errorMsg.setText("");
                    }, 5000);
                    viewModel.setSignUpError(null);
                } else {
                    errorMsg.setText(R.string.error_default);
                    new Handler().postDelayed(() -> {
                        errorMsg.setText("");
                    }, 3000);
                    viewModel.setSignUpError(null);
                }
            }
        });

        viewModel.getUserData().observe(getViewLifecycleOwner(), userData -> {
            if (userData != null) {
                Navigation.findNavController(getView()).navigate(RegisterFragmentDirections.actionRegisterFragmentToEmailVerificationFragment());
            }
        });
    }

    private void tryRegister() {
        if(!isEmailValid() | !isUsernameValid() | !isPasswordValid()) { return; }

        UserModel userData = new UserModel(username.getEditText().getText().toString(),
                password.getEditText().getText().toString(),
                "",
                "",
                "other",
                (long) 0,
                email.getEditText().getText().toString(),
                "",
                ""
        );

        viewModel.trySignUp(userData);
    }

    private boolean isEmailValid() {
        String toValidate = email.getEditText().getText().toString().trim();
        String checkEmail = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";

        if(toValidate.isEmpty()) {
            email.setError("Email cannot be empty!");
            return false;
        } else if(!toValidate.matches(checkEmail)) {
            email.setError("Invalid email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isUsernameValid() {
        String toValidate = username.getEditText().getText().toString().trim();
        // No puede tener espacios en el usuario
        // Máximo 20 caracteres
        String checkspaces = "^[a-zA-Z0-9\\-_]{1,20}$";

        if (toValidate.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (toValidate.length() > 20) {
            username.setError("Username is too large!");
            return false;
        } else if (!toValidate.matches(checkspaces)) {
            username.setError("No white spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isPasswordValid() {
        String toValidate = password.getEditText().getText().toString().trim();
        String checkPassword = confirmPassword.getEditText().getText().toString().trim();

        if (toValidate.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!toValidate.matches(checkPassword)) {
            password.setError("Password should contain at least 8 characters!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}