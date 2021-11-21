package ar.edu.itba.rutinapp_mobile_app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoutineRatingModel {
    @Expose
    @SerializedName("score")
    private Integer score;

    @Expose
    @SerializedName("review")
    private String review;

    public RoutineRatingModel(Integer score, String review) {
        this.score = score;
        this.review = review;
    }
}
