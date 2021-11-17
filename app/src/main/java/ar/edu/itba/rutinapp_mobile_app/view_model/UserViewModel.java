package ar.edu.itba.rutinapp_mobile_app.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;

public class UserViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<UserModel> userData = new MutableLiveData<>();

    public MutableLiveData<UserModel> getUserData() {
        return userData;
    }
}