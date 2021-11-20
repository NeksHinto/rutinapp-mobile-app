package ar.edu.itba.rutinapp_mobile_app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.repository.Resource;

public class FavouriteViewModel extends AndroidViewModel {
    private int routinePage = 0;

    private final MutableLiveData<Resource<PagedListModel<RoutineModel>>> favorites = new MutableLiveData<>();
    private final MutableLiveData<Integer> routineId = new MutableLiveData<>();
    private final PagedListModel<RoutineModel> allFavorites = new PagedListModel<>();

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Resource<PagedListModel<RoutineModel>>> getFavorites() {
        return favorites;
    }

}
