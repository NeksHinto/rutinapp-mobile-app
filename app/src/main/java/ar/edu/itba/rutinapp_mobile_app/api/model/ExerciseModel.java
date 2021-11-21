package ar.edu.itba.rutinapp_mobile_app.api.model;

//GET
///exercises/{exerciseId}
//Retrieve exercise

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private long date;
    @SerializedName("metadata")
    @Expose
    private ExerciseMetadataModel metadata;

    /**
     * No args constructor for use in serialization
     *
     */
    public ExerciseModel() {
    }

    /**
     *
     * @param date
     * @param metadata
     * @param name
     * @param id
     * @param detail
     * @param type
     */
    public ExerciseModel(int id, String name, String detail, String type, long date, ExerciseMetadataModel metadata) {
        super();
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.type = type;
        this.date = date;
        this.metadata = metadata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public ExerciseMetadataModel getMetadata() {
        return metadata;
    }

    public void setMetadata(ExerciseMetadataModel metadata) {
        this.metadata = metadata;
    }

}