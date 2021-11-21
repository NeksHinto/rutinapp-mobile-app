package ar.edu.itba.rutinapp_mobile_app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CyclesModel {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("repetitions")
    @Expose
    private Integer repetitions;

    @SerializedName("exercises")
    @Expose
    private ExerciseModel[] exercises;

    public CyclesModel(Integer id, String type, Integer repetitions, ExerciseModel[] exercises) {
        this.id = id;
        this.type = type;
        this.repetitions = repetitions;
        this.exercises = exercises;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public ExerciseModel[] getExercises() {
        return exercises;
    }
}
