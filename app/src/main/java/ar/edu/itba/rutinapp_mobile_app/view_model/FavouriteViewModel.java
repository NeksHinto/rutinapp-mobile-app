package ar.edu.itba.rutinapp_mobile_app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.ApiRoutineService;
import ar.edu.itba.rutinapp_mobile_app.api.model.PagedListModel;
import ar.edu.itba.rutinapp_mobile_app.api.model.RoutineModel;
import ar.edu.itba.rutinapp_mobile_app.repository.Resource;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

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

    private ApiRoutineService routinesService;
    private CompositeDisposable disposable = new CompositeDisposable();

    public void favRoutine(int routineId) {
        disposable.add(routinesService.favRoutine(routineId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<Void>>() {
                    @Override
                    public void onSuccess(@NonNull ApiResponse<Void> voidResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

    public void unfavRoutine(int routineId) {
        disposable.add(routinesService.unfavRoutine(routineId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponse<Void>>() {
                    @Override
                    public void onSuccess(@NonNull ApiResponse<Void> voidResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                }));
    }

}
