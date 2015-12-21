package pl.maniak.appexample;

import android.app.Application;


public class App extends Application {
    private static App instance = new App();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static App getInstance() {
        return instance;
    }


}
