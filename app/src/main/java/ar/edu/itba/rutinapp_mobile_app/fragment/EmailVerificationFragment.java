package ar.edu.itba.rutinapp_mobile_app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import ar.edu.itba.rutinapp_mobile_app.R;
import ar.edu.itba.rutinapp_mobile_app.databinding.FragmentEmailVerificationBinding;
import ar.edu.itba.rutinapp_mobile_app.view_model.UserViewModel;

public class EmailVerificationFragment extends Fragment {

    private UserViewModel viewModel;
    private TextInputLayout email;
    private TextInputLayout code;
    private MaterialButton verifyButton;
    private MaterialButton resendCodeButton;

    private FragmentEmailVerificationBinding binding;
    private FrameLayout progressLoading;

    public EmailVerificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEmailVerificationBinding.inflate(getLayoutInflater());

        this.email = binding.verificationEmail;
        this.code = binding.verificationCode;
        this.verifyButton = binding.verifyButton;
        this.resendCodeButton = binding.resendButton;

        this.progressLoading = binding.loadingContainer3;

        View view = binding.getRoot();

        verifyButton.setOnClickListener(v -> tryVerify(view));
        resendCodeButton.setOnClickListener(v -> resendCode());

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    progressLoading.setVisibility(View.VISIBLE);
                } else {
                    progressLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void tryVerify(View view) {
        Log.e("EMAIL", "estoy por verificar");
        this.viewModel.userVerification(code.getEditText().getText().toString());
        this.viewModel.getVerified().observe(getViewLifecycleOwner(), verified -> {
            if (verified) {
                Log.e("EMAIL", "verificado");
                Bundle b = getArguments();

                NavController navController = Navigation.findNavController(view);
                EmailVerificationFragmentDirections.ActionEmailVerificationFragmentToWelcomeFragment action = EmailVerificationFragmentDirections.actionEmailVerificationFragmentToWelcomeFragment();
                if(b != null) {
                    navController.navigate(action);
                } else {
                    navController.navigate(action);
                }
            }
            Log.e("EMAIL", "No se verificó bien");
        });
    }

    private void resendCode() {
        if (!isEmailValid()) {
            return;
        }
        viewModel.resendVerification(email.getEditText().getText().toString());
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
}