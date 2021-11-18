package ar.edu.itba.rutinapp_mobile_app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ar.edu.itba.rutinapp_mobile_app.AppPreferences;
import ar.edu.itba.rutinapp_mobile_app.api.ApiClient;
import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.ApiService;
import ar.edu.itba.rutinapp_mobile_app.api.data.VerificationData;
import ar.edu.itba.rutinapp_mobile_app.api.model.CredentialsModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.ErrorModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

//public class UserViewModel extends RepositoryViewModel<UserRepository>
public class UserViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<UserModel> userData = new MutableLiveData<>();
    private MutableLiveData<TokenModel> token = new MutableLiveData<>();
    private MutableLiveData<Boolean> verified = new MutableLiveData<>();

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private MutableLiveData<ErrorModel> signInError = new MutableLiveData<>();
    private MutableLiveData<ErrorModel> signUpError = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private Application app;
    private ApiClient userApiService;

//    public UserViewModel(UserRepository repository) {
//        super(repository);
//    }

    public UserViewModel(@NonNull Application app) {
        super(app);
        userApiService = new ApiClient(app);
        this.app = app;
    }

    public void trySignIn(String username, String password) {
        CredentialsModel userCredentials = new CredentialsModel(username, password);
        this.loading.setValue(true);

        disposable.add(userApiService.login(userCredentials)
                .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<TokenModel>() {
                @Override
                public void onSuccess(@NonNull TokenModel authToken) {
                    token.setValue(authToken);
                    ApiService.setAuthToken(authToken.getToken());
                    AppPreferences myPreferences = new AppPreferences(app);
                    signInError.setValue(null);
                    loading.setValue(false);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    if(e instanceof HttpException) {
                        HttpException exception = (HttpException) e;
                        try {
                            Gson gson = new Gson();
                            ErrorModel error;
                            error = gson.fromJson(exception.response().errorBody().string(), new TypeToken<ErrorModel>(){
                            }.getType());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    e.printStackTrace();
                    loading.setValue(false);

                }
            })
        );
    }

    public void trySignUp(UserModel user) {
        loading.setValue(true);

        disposable.add(userApiService.register(user)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<UserModel>() {
                @Override
                public void onSuccess(@NonNull UserModel data) {
                    userData.setValue(data);
                    loading.setValue(false);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    if(e instanceof HttpException) {
                        HttpException exception = (HttpException) e;
                        try {
                            Gson gson = new Gson();
                            ErrorModel error;
                            error = gson.fromJson(exception.response().errorBody().string(), new TypeToken<ErrorModel>() {
                            }.getType());
                            signUpError.setValue(error);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    e.printStackTrace();
                    loading.setValue(false);
                }
            })
        );
    }

    public void userVerification(String code) {
        loading.setValue(true);
        disposable.add(userApiService.verifyEmail(new VerificationData(userData.getValue().getEmail(), code))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<Void>>() {

                    @Override
                    public void onSuccess(@NonNull ApiResponse<Void> voidResponse) {
                        verified.setValue(true);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        verified.setValue(false);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    public void setUserData() {
        disposable.add(userApiService.getCurrentUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    public void onSuccess(@NonNull UserModel info) {
                        userData.setValue(info);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    public void resendVerification(String email) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        loading.setValue(true);
        disposable.add(userApiService.resendVerification(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<Void>>() {

                    @Override
                    public void onSuccess(@NonNull ApiResponse<Void> voidResponse) {
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        loading.setValue(false);
                    }
                })
        );
    }

    public void logout() {
        disposable.add(userApiService.logout()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<Void>>() {
                    @Override
                    public void onSuccess(@NonNull ApiResponse<Void> voidResponse) {
                        new AppPreferences(app).setAuthToken(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    public MutableLiveData<UserModel> getUserData() { return userData; }

    public MutableLiveData<Boolean> getVerified() {
        return verified;
    }

    public MutableLiveData<TokenModel> getToken() {
        return token;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<ErrorModel> getSignInError() {
        return signInError;
    }

    public MutableLiveData<ErrorModel> getSignUpError() {
        return signUpError;
    }

    public void setSignInError(ErrorModel error) {
        signInError.setValue(error);
    }

    public void setSignUpError(ErrorModel error) { signUpError.setValue(error); }

//    public LiveData<Resource<Void>> logOut(){
//        return repository.logout();
//    }
}