package ar.edu.itba.rutinapp_mobile_app;

import android.app.Application;

import ar.edu.itba.rutinapp_mobile_app.repository.UserRepository;

public class App extends Application {

    AppPreferences preferences;
    UserRepository userRepository;


    public AppPreferences getPreferences() {
        return preferences;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
