package ar.edu.itba.rutinapp_mobile_app.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import ar.edu.itba.rutinapp_mobile_app.App;
import ar.edu.itba.rutinapp_mobile_app.api.ApiClient;
import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;

public class RoutineRepository {
    private final ApiResponse.ApiRoutineServiceInner apiService;

    public RoutineRepository(App app) {
        this.apiService = ApiClient.create(app, ApiResponse.ApiRoutineServiceInner.class);
    }

    public LiveData<Resource<RoutineModel>> getRoutine(int routineId) {
        return new NetworkBoundResource<RoutineModel, RoutineModel>()
        {
            @NonNull
            @Override
            protected LiveData<ApiResponse<RoutineModel>> createCall() {
                return apiService.getRoutine(routineId);
            }
        }.asLiveData();
    }

}
