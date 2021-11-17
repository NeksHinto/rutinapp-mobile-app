package ar.edu.itba.rutinapp_mobile_app.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.LoginFragmentBinding;

public class LoginFragment extends Fragment {

    private TextInputLayout username;
    private TextInputLayout password;
    private TextView error;

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

        return inflater.inflate(R.layout.login_fragment, container, false);
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