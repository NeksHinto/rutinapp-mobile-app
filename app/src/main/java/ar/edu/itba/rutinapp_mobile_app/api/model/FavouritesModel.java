package ar.edu.itba.rutinapp_mobile_app.api.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.rutinapp_mobile_app.api.ApiResponse;
import ar.edu.itba.rutinapp_mobile_app.api.ApiRoutine;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class FavouritesModel extends AndroidViewModel {
    private boolean fav = false;

    private MutableLiveData<List<RoutineModel>> favouriteRoutines = new MutableLiveData<>();

    private ApiRoutine routinesService;
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

    public FavouritesModel(@androidx.annotation.NonNull Application application) {
        super(application);
        routinesService = new ApiRoutine(application);
    }

    public void updateData() {
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        Map<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(0));
        options.put("orderBy", "averageRating");
        options.put("direction", "desc");
        options.put("size", String.valueOf(100));

        disposable.add(
                routinesService.getFavouriteRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedListModel<RoutineModel>>() {
                            @Override
                            public void onSuccess(@NonNull PagedListModel<RoutineModel> favourites) {
                                favouriteRoutines.setValue(favourites.getContent());
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public MutableLiveData<List<RoutineModel>> getFavouriteRoutines() {
        return favouriteRoutines;
    }
}