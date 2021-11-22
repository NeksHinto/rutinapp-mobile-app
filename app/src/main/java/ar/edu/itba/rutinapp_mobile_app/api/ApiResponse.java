package ar.edu.itba.rutinapp_mobile_app.api;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.model.ErrorModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiResponse<T> {

    private T data;
    private ErrorModel error;

    public T getData() {
        return data;
    }

    public ErrorModel getError() {
        return error;
    }

    public ApiResponse(Response<T> response) {
        parseResponse(response);
    }

    public ApiResponse(Throwable throwable) {
        this.error = buildError(throwable.getMessage());
    }

    private void parseResponse(Response<T> response) {
        if (response.isSuccessful()) {
            this.data = response.body();
            return;
        }

        if (response.errorBody() == null) {
            this.error = buildError("Missing error body");
            return;
        }

        String message;

        try {
            message = response.errorBody().string();
        } catch (IOException exception) {
            Log.e("API", exception.toString());
            this.error = buildError(exception.getMessage());
            return;
        }

        if (message != null && message.trim().length() > 0) {
            Gson gson = new Gson();
            this.error =  gson.fromJson(message, new TypeToken<ErrorModel>() {}.getType());
        }
    }

    private static ErrorModel buildError(String message) {
        ErrorModel error = new ErrorModel(ErrorModel.LOCAL_UNEXPECTED_ERROR, "Unexpected error");
        if (message != null) {
            List<String> details = new ArrayList<>();
            details.add(message);
            error.setDetails(details);
        }
        return error;
    }

    public static interface ApiRoutineServiceInner {

        @GET("routines/{routineId}")
        LiveData<ApiResponse<RoutineModel>> getRoutine(@Path("routineId") int routineId);

    }

}
