package ar.edu.itba.rutinapp_mobile_app.api;
import androidx.lifecycle.LiveData;

import java.util.Map;

import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineCycle;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRoutineService {
    @GET("routines")
    Single<ApiResponse<PagedListModel<RoutineModel>>> getRoutines(
            @QueryMap Map<String, String> options);

    @GET("users/current/routines")
    Single<ApiResponse<PagedListModel<RoutineModel>>> getUserRoutines(
            @QueryMap Map<String, String> options
    );

    @GET("routines/{routineId}")
    Single<ApiResponse<RoutineModel>> getRoutine(
            @Path("routineId") Integer routineId);

    @GET("routines/{routineId}/cycles")
    Single<ApiResponse<PagedListModel<RoutineCycle>>> getRoutineCycles(
            @Path("routineId") Integer routineId,  Map<String, String> options);

//    @GET("routines/{routineId}/cycles/{cycleId}")
//    Single<ApiResponse<RoutineCycle>> getRoutineCycle(
//            @Path("routineId") int routineId,
//            @Path("cycleId") int cycleId);
//
//    //trae las rutinas ejecutadas para el historial
//    @GET("users/current/executions")
//    Single<ApiResponse<PagedListModel<RoutineModel>>> getUserExecutionsRoutines(
//            @QueryMap Map<String, String> options
//    );

    //traer y actualizar los favoritos
    @GET("favourites")
    Single<PagedListModel<RoutineModel>> getFavouriteRoutines(
            @QueryMap Map<String, String> options
    );

    @POST("favourites/{routineId}")
    Single<Response<Void>> favRoutine(
            @Path("routineId") Integer routineId
    );

    @DELETE("favourites/{routineId}")
    Single<Response<Void>> unfavRoutine(
            @Path("routineId") Integer routineId
    );
}
