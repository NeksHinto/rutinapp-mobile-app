package ar.edu.itba.rutinapp_mobile_app.api;

import androidx.lifecycle.LiveData;

import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineCategory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRoutineCategoryService {
    @GET("categories")
    Single<ApiResponse<PagedListModel<RoutineCategory>>> getCategories();

    @GET("category/{categoryId}")
    Single<ApiResponse<RoutineCategory>> getCategory(
            @Path("categoryId") int categoryId);
}
