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
    //direction lo necesitamos por el order
    @GET("routines")
    Single<ApiResponse<PagedListModel<RoutineModel>>> getRoutines(
            @Query("page") int page, @Query("size") int size,
            @Query("orderBy") String orderBy,
            @Query("direction") String direction);

    @GET("routines/{routineId}")
    Single<ApiResponse<RoutineModel>> getRoutine(
            @Path("routineId") int routineId);

    @GET("routines/{routineId}/cycles")
    Single<ApiResponse<PagedListModel<RoutineCycle>>> getRoutineCycles(
            @Path("routineId") int routineId);

    @GET("routines/{routineId}/cycles/{cycleId}")
    Single<ApiResponse<RoutineCycle>> getRoutineCycle(
            @Path("routineId") int routineId, @Path("cycleId") int cycleId);

    //creo que nos sirve para el historial
    @GET("users/current/routines/executions")
    Single<ApiResponse<PagedListModel<RoutineModel>>> getCurrentUserRoutines(
            @QueryMap Map<String, String> options
    );

    //para traer y actualizar los favoritos
    @GET("user/current/routines/favourites")
    Single<PagedListModel<RoutineModel>> getFavouriteRoutines(
            @QueryMap Map<String, String> options
    );

    @POST("user/current/routines/{routineId}/favourites")
    Single<Response<Void>> favRoutine(
            @Path("routineId") Integer routineId
    );

    @DELETE("user/current/routines/{routineId}/favourites")
    Single<Response<Void>> unfavRoutine(
            @Path("routineId") Integer routineId
    );
}
