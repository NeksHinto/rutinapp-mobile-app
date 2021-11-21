package ar.edu.itba.rutinapp_mobile_app.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class RoutineViewModel extends AndroidViewModel {
    private MediatorLiveData<List<RoutineData>> routineCards = new MediatorLiveData<>();
    private MutableLiveData<List<RoutineData>> userRoutines = new MutableLiveData<>();
    private MutableLiveData<List<RoutineData>> userRoutinesByDate = new MutableLiveData<>();
    private MutableLiveData<List<RoutineData>> userFavourites = new MutableLiveData<>();
    private MutableLiveData<List<RoutineData>> userHistory = new MutableLiveData<>();
    private MutableLiveData<RoutineData> currentRoutine = new MutableLiveData<>();
    private MutableLiveData<Boolean> noMoreEntries = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Boolean> routinesFirstLoad = new MutableLiveData<>(true);
    private MutableLiveData<Boolean> routinesFavFirstLoad = new MutableLiveData<>(true);

    private ApiRoutine routinesService;
    private CompositeDisposable disposable = new CompositeDisposable();

    private int currentPage = 0;
    private int totalPages = 0;
    private int itemsPerRequest = 15;
    private boolean isLastPage = false;
    private String direction = "desc";
    private Integer filter = null;
    private String orderBy = "averageRating";
    private int orderById = 0;
    private int directionId = 0;
    private int filterId = -1;

    public RoutineViewModel(@NonNull Application application) {
        super(application);
        routinesService = new ApiRoutine(application);
    }

    public void resetData() {
        currentPage = 0;
        isLastPage = false;
        totalPages = 0;
        routineCards.setValue(new ArrayList<>());
        updateData();
    }

    public void resetDataFavs() {
        currentPage = 0;
        isLastPage = false;
        totalPages = 0;
        routineCards.setValue(new ArrayList<>());
        updateDataFavs();
    }

    public void updateData() {
        if (!isLastPage) {
            fetchFromRemote();
        }
    }

    public void updateDataFavs() {
        if (!isLastPage) {
            fetchFromRemoteFavs();
        }
    }

    public void updateUserRoutines() {
        Map<String, String> options = new HashMap<>();
        options.put("page", "0");
        options.put("orderBy", orderBy);
        options.put("direction", direction);
        options.put("size", String.valueOf(1000));
        //TODO:el otro error que no entiendo
        disposable.add(
                routinesService.getUserRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                userRoutines.setValue(routinesEntries.getContent());
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void updateRoutines() {
        Map<String, String> options = new HashMap<>();
        options.put("page", "0");
        options.put("orderBy", orderBy);
        options.put("direction", direction);
        options.put("size", String.valueOf(1000));
        //TODO:el otro error que no entiendo
        disposable.add(
                routinesService.getRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                userRoutines.setValue(routinesEntries.getContent());
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void updateRoutinesByDate() {
        Map<String, String> options = new HashMap<>();
        options.put("page", "0");
        options.put("orderBy", "date");
        options.put("direction", direction);
        options.put("size", String.valueOf(1000));
        //TODO:el otro error que no entiendo
        disposable.add(
                routinesService.getRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                userRoutinesByDate.setValue(routinesEntries.getContent());
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void getFavouriteRoutines() {
        Map<String, String> options = new HashMap<>();
        options.put("page", "0");
        options.put("size", String.valueOf(1000));
        disposable.add(
                routinesService.getFavouriteRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                userFavourites.setValue(routinesEntries.getContent());
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void getRoutineById(int id) {
        disposable.add(
                routinesService.getRoutineById(id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<RoutineData>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull RoutineData routine) {
                                int id = routine.getCategory().getId();
                                switch (id) {
                                    case 1:
                                        routine.setImage(String.valueOf(R.drawable.encasa));
                                        break;
                                    case 2:
                                        routine.setImage(String.valueOf(R.drawable.pesas));
                                        break;
                                    case 3:
                                        routine.setImage(String.valueOf(R.drawable.running));
                                        break;
                                }
                                currentRoutine.setValue(routine);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void orderRoutines(int option) {
        directionId = option;
        switch (option) {
            case 0:
                direction = "desc";
                break;

            case 1:
                direction = "asc";
                break;
        }

        applyChanges();
    }

    public void filterRoutines(Integer option) {
        filterId = option;
        filter = (option != -1 ? option : null);
        applyChanges();
    }

    public void sortRoutines(int option) {
        orderById = option;
        switch (option) {
            case 0:
                orderBy = "date";
                break;

            case 1:
                orderBy = "averageRating";
                break;

            case 2:
                orderBy = "categoryId";
                break;

            case 4:
                orderBy = "name";
                break;

        }

        applyChanges();
    }

    private void applyChanges() {
        routineCards.setValue(new ArrayList<>());
        currentPage = 0;
        isLastPage = false;
        totalPages = 0;
        fetchFromRemote();
    }

    private void fetchFromRemote() {
        Map<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(currentPage));
        options.put("orderBy", orderBy);
        options.put("direction", direction);
        options.put("size", String.valueOf(itemsPerRequest));
        if (filter != null) {
            options.put("categoryId", String.valueOf(filter));
        }

        loading.setValue(true);
        disposable.add(
                routinesService.getRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            List<RoutineData> aux;
                            boolean duplicate = false;
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                isLastPage = routinesEntries.getLastPage();
                                noMoreEntries.setValue(isLastPage);
                                currentPage++;
                                routineCards.setValue(routinesEntries.getContent());
                                aux = routineCards.getValue();
                                if(aux != null && !duplicate) {
                                    aux.addAll(routinesEntries.getContent());
                                    duplicate = true;
                                }
                                totalPages = (int) Math.ceil(routinesEntries.getTotalCount() / (double) itemsPerRequest);
                                loading.setValue(false);
                            }

                            @Override
                            protected void onStart() {
                                super.onStart();
                                aux=null;
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    private void fetchFromRemoteFavs() {
        Map<String, String> options = new HashMap<>();
        options.put("page", String.valueOf(currentPage));
        options.put("orderBy", orderBy);
        options.put("direction", direction);
        options.put("size", String.valueOf(itemsPerRequest));
        if (filter != null) {
            options.put("categoryId", String.valueOf(filter));
        }

        loading.setValue(true);
        disposable.add(
                routinesService.getFavouriteRoutines(options)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<PagedList<RoutineData>>() {
                            List<RoutineData> aux;
                            boolean duplicate = false;
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull PagedList<RoutineData> routinesEntries) {
                                isLastPage = routinesEntries.getLastPage();
                                noMoreEntries.setValue(isLastPage);
                                currentPage++;
                                routineCards.setValue(routinesEntries.getContent());
                                aux = routineCards.getValue();
                                if(aux != null && !duplicate) {
                                    aux.addAll(routinesEntries.getContent());
                                    duplicate = true;
                                }
                                totalPages = (int) Math.ceil(routinesEntries.getTotalCount() / (double) itemsPerRequest);
                                loading.setValue(false);
                            }

                            @Override
                            protected void onStart() {
                                super.onStart();
                                aux=null;
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    public void addFav(int routId){
        disposable.add(routinesService.favRoutine(routId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Response<Void> voidResponse) {

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    public void unFav(int routId){
        disposable.add(routinesService.unfavRoutine(routId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Response<Void> voidResponse) {

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

    public MutableLiveData<List<RoutineData>> getRoutineCards() {
        return routineCards;
    }

    public MutableLiveData<List<RoutineData>> getUserHistory() {
        return userHistory;
    }

    public MutableLiveData<Boolean> getNoMoreEntries() {
        return noMoreEntries;
    }

    public MutableLiveData<Boolean> getRoutinesFirstLoad() {
        return routinesFirstLoad;
    }
    public MutableLiveData<Boolean> getRoutinesFirstLoadFavs() {
        return routinesFavFirstLoad;
    }

    public void setRoutinesFirstLoad(Boolean firstLoad) {
        routinesFirstLoad.setValue(firstLoad);
        routinesFavFirstLoad.setValue(firstLoad);
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<List<RoutineData>> getUserRoutines() {
        return userRoutines;
    }

    public MutableLiveData<List<RoutineData>> getRoutinesByDate() {
        return userRoutinesByDate;
    }

    public MutableLiveData<List<RoutineData>> getUserFavouriteRoutines() { return userFavourites; }

    public MutableLiveData<RoutineData> getCurrentRoutine() {
        return currentRoutine;
    }

    public String getDirection() {
        return direction;
    }

    public Integer getFilter() {
        return filter;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderById(int option) {
        orderById = option;
        switch (option) {
            case 0:
                orderBy = "date";
                break;

            case 1:
                orderBy = "averageRating";
                break;

            case 2:
                orderBy = "categoryId";
                break;

            case 3:
                orderBy = "name";
                break;
        }
    }
    public int getOrderById() {
        return orderById;
    }

    public int getDirectionId() {
        return directionId;
    }

    public int getFilterId() {
        return filterId;
    }

    public void rateRoutine(int routineId, int value) {
        RoutineRating rating = new RoutineRating(value, "");
        disposable.add(
                routinesService.rateRoutine(routineId, rating)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<RoutineData>() {
                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull RoutineData routineData) {
                            }
                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        })
        );
    }
}
