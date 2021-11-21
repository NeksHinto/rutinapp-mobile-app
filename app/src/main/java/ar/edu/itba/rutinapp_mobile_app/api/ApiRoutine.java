package ar.edu.itba.rutinapp_mobile_app.api;

import android.content.Context;

import java.util.Map;

import ar.edu.itba.rutinapp_mobile_app.api.model.ExerciseCycle;
import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineCycle;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRoutine extends ApiService implements ApiRoutineService{
    private ApiRoutineService api;

    public ApiRoutine(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AuthInterceptor(context))
                .build();

        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiRoutineService.class);
    }

    @Override
    public Single<ApiResponse<PagedListModel<RoutineModel>>> getRoutines(Map<String, String> options) {
        return api.getRoutines(options);
    }

    @Override
    public Single<ApiResponse<PagedListModel<RoutineModel>>> getUserRoutines(Map<String, String> options) {
        return api.getUserRoutines(options);
    }

//    @Override
//    public Single<PagedList<RoutineHistory>> getUserHistory(Map<String, String> options) {
//        return api.getUserHistory(options);
//    }

    @Override
    public Single<PagedListModel<RoutineModel>> getFavouriteRoutines(Map<String, String> options) {
        return api.getFavouriteRoutines(options);
    }

//    @Override
//    public Single<RoutineModel> rateRoutine(Integer routineId, RoutineRating rating) {
//        return api.rateRoutine(routineId, rating);
//    }

    @Override
    public Single<Response<Void>> favRoutine(Integer routineId) {
        return api.favRoutine(routineId);
    }

    @Override
    public Single<Response<Void>> unfavRoutine(Integer routineId) {
        return api.unfavRoutine(routineId);
    }

//    @Override
//    public Single<ApiResponse<PagedListModel<RoutineModel>>> getUserExecutionsRoutines(Map<String, String> options) {
//        return api.getUserRoutines(options);
//    }

    @Override
    public Single<ApiResponse<PagedListModel<RoutineCycle>>> getRoutineCycles(Integer routineId, Map<String, String> options) {
        return api.getRoutineCycles(routineId, options);
    }

//    @Override
//    public Single<ApiResponse<RoutineCycle>> getRoutineCycle(int routineId, int cycleId) {
//        return null;
//    }

//    @Override
//    public Single<RoutineData> addRoutineExecution(Integer routineId, RoutineExecution routineExecution) {
//        return api.addRoutineExecution(routineId, routineExecution);
//    }

    @Override
    public Single<ApiResponse<RoutineModel>> getRoutine(Integer routineId) {
        return api.getRoutine(routineId);
    }
}
