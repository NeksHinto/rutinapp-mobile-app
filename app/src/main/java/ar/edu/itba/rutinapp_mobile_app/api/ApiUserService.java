package ar.edu.itba.rutinapp_mobile_app.api;

import androidx.lifecycle.LiveData;

import java.util.Map;

import ar.edu.itba.rutinapp_mobile_app.api.data.VerificationData;
import ar.edu.itba.rutinapp_mobile_app.api.model.CredentialsModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiUserService {
    @POST("users/login")
    Single<TokenModel> login(@Body CredentialsModel credentials);

    @POST("users/logout")
    Single<ApiResponse<Void>> logout();

    @GET("users/current")
    Single<UserModel> getCurrentUser();

    @POST("users")
    Single<UserModel> register(@Body UserModel userData);

    @POST("users/verify_email")
    Single<ApiResponse<Void>> verifyEmail(@Body VerificationData data);

    @POST("users/resend_verification")
    Single<ApiResponse<Void>> resendVerification(@Body Map<String, String> data);
}
