package ar.edu.itba.rutinapp_mobile_app.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import ar.edu.itba.rutinapp_mobile_app.repository.Resource;
import ar.edu.itba.rutinapp_mobile_app.repository.UserRepository;

public class UserViewModel extends RepositoryViewModel<UserRepository> {
    // TODO: Implement the ViewModel
    private MutableLiveData<UserModel> userData = new MutableLiveData<>();

    public UserViewModel(UserRepository repository) {
        super(repository);
    }

    public MutableLiveData<UserModel> getUserData() {
        return userData;
    }

    public LiveData<Resource<Void>> logOut(){
        return repository.logout();
    }
}