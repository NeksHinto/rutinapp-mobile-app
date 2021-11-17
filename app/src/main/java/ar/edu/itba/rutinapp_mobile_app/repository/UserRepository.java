package ar.edu.itba.rutinapp_mobile_app.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import ar.edu.itba.rutinapp_mobile_app.App;
import ar.edu.itba.rutinapp_mobile_app.api.ApiClient;
import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.ApiService;
import ar.edu.itba.rutinapp_mobile_app.api.ApiUserService;
import ar.edu.itba.rutinapp_mobile_app.api.data.VerificationData;
import ar.edu.itba.rutinapp_mobile_app.api.model.CredentialsModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;

public class UserRepository {
    private final ApiUserService api;

    public UserRepository(App app) {
        this.api = ApiClient.create(app, ApiUserService.class);
    }

    public LiveData<Resource<TokenModel>> login(CredentialsModel credentials) {
        return new NetworkBoundResource<TokenModel, TokenModel>() {
            @NonNull
            @Override
            protected LiveData<ApiResponse<TokenModel>> createCall() {
                return api.login(credentials);
            }
        }.asLiveData();
    }

    public LiveData<Resource<Void>> logout() {
        return new NetworkBoundResource<Void, Void>()
        {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return api.logout();
            }
        }.asLiveData();
    }

    public LiveData<Resource<UserModel>> register(CredentialsModel credentials) {
        return new NetworkBoundResource<UserModel, UserModel>()
        {
            @NonNull
            @Override
            protected LiveData<ApiResponse<UserModel>> createCall() {
                return api.register(credentials);
            }
        }.asLiveData();
    }

    public LiveData<Resource<Void>> verifyEmail(VerificationData data) {
        return new NetworkBoundResource<Void, Void>()
        {
            @NonNull
            @Override
            protected LiveData<ApiResponse<Void>> createCall() {
                return api.verifyEmail(data);
            }
        }.asLiveData();
    }

    public LiveData<Resource<UserModel>> getCurrentUser() {
        return new NetworkBoundResource<UserModel, UserModel>()
        {
            @NonNull
            @Override
            protected LiveData<ApiResponse<UserModel>> createCall() {
                return api.getCurrentUser();
            }
        }.asLiveData();
    }


}
