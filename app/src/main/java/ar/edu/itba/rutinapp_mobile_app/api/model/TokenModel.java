package ar.edu.itba.rutinapp_mobile_app.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenModel {
    @SerializedName("token")
    @Expose
    private String token;

    public TokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
