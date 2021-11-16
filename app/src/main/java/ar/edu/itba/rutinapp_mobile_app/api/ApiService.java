package ar.edu.itba.rutinapp_mobile_app.api;

public abstract class ApiService {

    public static final String BASE_URL = "http://10.0.2.2:8080/api/";
    private static String authToken = null;

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        ApiService.authToken = authToken;
    }
}
