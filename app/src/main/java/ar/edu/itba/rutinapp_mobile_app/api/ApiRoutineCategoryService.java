package ar.edu.itba.rutinapp_mobile_app.api;

import androidx.lifecycle.LiveData;

import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineCategory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRoutineCategoryService {
    @GET("categories")
    LiveData<ApiResponse<PagedListModel<RoutineCategory>>> getCategories();

    @GET("category/{categoryId}")
    LiveData<ApiResponse<RoutineCategory>> getCategory(@Path("categoryId") int categoryId);
}
