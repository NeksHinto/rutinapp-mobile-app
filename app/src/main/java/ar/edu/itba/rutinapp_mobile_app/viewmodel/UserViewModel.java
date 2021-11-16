package ar.edu.itba.rutinapp_mobile_app.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import ar.edu.itba.rutinapp_mobile_app.api.ApiClient;
import ar.edu.itba.rutinapp_mobile_app.api.model.ErrorModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;

public class UserViewModel {

    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    private MutableLiveData<TokenModel> token = new MutableLiveData<>();
    private MutableLiveData<Boolean> verified = new MutableLiveData<>();

    private MutableLiveData<ErrorModel> loginError = new MutableLiveData<>();
    private MutableLiveData<ErrorModel> registerError = new MutableLiveData<>();

    private ApiClient userService;
    private Application app;
}
