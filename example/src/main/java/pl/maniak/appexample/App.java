package pl.maniak.appexample;

import android.app.Application;

import pl.maniak.appexample.di.AppComponent;
import pl.maniak.appexample.di.AppModule;
import pl.maniak.appexample.di.DaggerAppComponent;


public class App extends Application {
    private static App instance = new App();
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
