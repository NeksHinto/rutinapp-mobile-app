package ar.edu.itba.rutinapp_mobile_app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseMetadataModel {
    @SerializedName("series")
    @Expose
    private Integer series;

    @SerializedName("duration")
    @Expose
    private Integer duration;

    @SerializedName("difficulty")
    @Expose
    private String difficulty;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("category")
    @Expose
    private String category;

    public ExerciseMetadataModel(Integer series, Integer duration, String difficulty, String image, String category) {
        this.series = series;
        this.duration = duration;
        this.difficulty = difficulty;
        this.image = image;
        this.category = category;
    }

    public Integer getSeries() { return this.series; }

    public Integer getDuration() { return this.duration; }

    public String getDifficulty() { return this.difficulty; }

    public String getImage() { return this.image; }

    public String getCategory() { return this.category; }
    
}
