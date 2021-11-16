package ar.edu.itba.rutinapp_mobile_app.api;

import androidx.lifecycle.LiveData;

import ar.edu.itba.rutinapp_mobile_app.api.model.CredentialsModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiUserService {
    @POST("users/login")
    LiveData<TokenModel> login(@Body CredentialsModel credentials);

    @POST("users/logout")
    LiveData<ApiResponse<Void>> logout();

    @GET("users/current")
    LiveData<ApiResponse<UserModel>> getCurrentUser();

    @POST("users")
    LiveData<ApiResponse<UserModel>> register(@Body UserModel user);
    
}
