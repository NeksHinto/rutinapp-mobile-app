package ar.edu.itba.rutinapp_mobile_app.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.Routine;
import ar.edu.itba.rutinapp_mobile_app.repository.Resource;

public class FavouriteViewModel extends ViewModel {
    private int routinePage = 0;

    private final MediatorLiveData<Resource<PagedListModel<Routine>>> favorites = new MediatorLiveData<>();
    private final MutableLiveData<Integer> routineId = new MutableLiveData<>();
    private final LiveData<Resource<Routine>> favorite;

    public FavouriteViewModel(LiveData<Resource<Routine>> favorite) {
        this.favorite = favorite;
    }
    public LiveData<Resource<PagedListModel<Routine>>> getFavorites() {
        return favorites;
    }
}
