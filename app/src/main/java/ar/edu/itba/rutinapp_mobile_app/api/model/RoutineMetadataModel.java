package ar.edu.itba.rutinapp_mobile_app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoutineMetadataModel {
    @SerializedName("cycles")
    @Expose
    private CyclesModel[] cycles;

    @SerializedName("image")
    @Expose
    private String image;

    public RoutineMetadataModel(CyclesModel[] cycles, String image) {
        this.cycles = cycles;
        this.image = image;
    }

    public CyclesModel[] getCycles() {
        return cycles;
    }

    public String getImage() {
        return image;
    }
}
