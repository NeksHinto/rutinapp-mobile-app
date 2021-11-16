package ar.edu.itba.rutinapp_mobile_app.api;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ar.edu.itba.rutinapp_mobile_app.BuildConfig;
import ar.edu.itba.rutinapp_mobile_app.api.data.VerificationData;
import ar.edu.itba.rutinapp_mobile_app.api.model.CredentialsModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.TokenModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.UserModel;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient extends ApiService implements ApiUserService{

    public static final int CONNECT_TIMEOUT = 60;
    public static final int READ_TIMEOUT = 60;
    public static final int WRITE_TIMEOUT = 60;

    // No usar localhost o la IP 127.0.0.1 porque es la interfaz de loopback
    // del emulador. La forma de salir del emulador para acceder al localhost
    // de host del mismo es usando la IP 10.0.2.2.
    public static final String BASE_URL = "http://10.0.2.2:8080/api/";

    private ApiUserService api;

    private ApiClient() {
    }

    public static <S> S create(Context context, Class<S> serviceClass) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().
                setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(context))
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new ApiDateTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

        return retrofit.create(serviceClass);
    }

    public ApiUserService getApi() { return api; }

    @Override
    public LiveData<TokenModel> login(CredentialsModel credentials) { return api.login(credentials); }

    @Override
    public LiveData<ApiResponse<Void>> logout() { return api.logout();}

    @Override
    public LiveData<ApiResponse<UserModel>> getCurrentUser() { return api.getCurrentUser(); }

    @Override
    public LiveData<ApiResponse<UserModel>> register(UserModel user) {
        return null;
    }

    @Override
    public LiveData<ApiResponse<Void>> verifyEmail(VerificationData data) {
        return null;
    }

    @Override
    public LiveData<ApiResponse<Void>> resendVerification(Map<String, String> data) {
        return null;
    }


}
