package ar.edu.itba.rutinapp_mobile_app.api.model;

//GET
// /cycles/{cycleId}/exercises/{exerciseId}

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseCycle {

    @SerializedName("order")
    @Expose
    private int order;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("repetitions")
    @Expose
    private int repetitions;
    @SerializedName("exercise")
    @Expose
    private ExerciseModel exercise;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExerciseCycle() {
    }


    public ExerciseCycle(int order, int duration, int repetitions, ExerciseModel exercise) {
        super();
        this.order = order;
        this.duration = duration;
        this.repetitions = repetitions;
        this.exercise = exercise;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public ExerciseModel getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseModel exercise) {
        this.exercise = exercise;
    }

}