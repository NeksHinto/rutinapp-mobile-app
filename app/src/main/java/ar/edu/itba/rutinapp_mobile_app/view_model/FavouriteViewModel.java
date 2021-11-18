package ar.edu.itba.rutinapp_mobile_app.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.repository.Resource;

public class FavouriteViewModel extends ViewModel {
    private int routinePage = 0;

    private final MediatorLiveData<Resource<PagedListModel<RoutineModel>>> favorites = new MediatorLiveData<>();
    private final MutableLiveData<Integer> routineId = new MutableLiveData<>();
    private final LiveData<Resource<RoutineModel>> favorite;

    public FavouriteViewModel(LiveData<Resource<RoutineModel>> favorite) {
        this.favorite = favorite;
    }
    public LiveData<Resource<PagedListModel<RoutineModel>>> getFavorites() {
        return favorites;
    }
}
