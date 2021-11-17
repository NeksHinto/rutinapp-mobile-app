package ar.edu.itba.rutinapp_mobile_app.repository;

import static ar.edu.itba.rutinapp_mobile_app.repository.Status.ERROR;
import static ar.edu.itba.rutinapp_mobile_app.repository.Status.LOADING;
import static ar.edu.itba.rutinapp_mobile_app.repository.Status.SUCCESS;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import ar.edu.itba.rutinapp_mobile_app.api.model.ErrorModel;

public class Resource<T> {
    @NonNull
    private final Status status;

    @Nullable
    private final Error error;

    @Nullable
    private final T data;

    @NotNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public Error getError() {
        return error;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public Resource(@NonNull Status status, @Nullable T data, @Nullable Error error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(ErrorModel error, @Nullable T data) {
        return new Resource<>(ERROR, data, error);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
