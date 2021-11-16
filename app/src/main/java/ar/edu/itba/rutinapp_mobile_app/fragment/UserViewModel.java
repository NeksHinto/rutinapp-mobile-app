package ar.edu.itba.rutinapp_mobile_app.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;

public class UserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<UserModel> userInfo = new MutableLiveData<>();

    public MutableLiveData<UserModel> getUserInfo() {
        return userInfo;
    }
}