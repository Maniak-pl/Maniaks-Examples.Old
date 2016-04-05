package pl.maniak.appexample;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import pl.maniak.appexample.di.AppComponent;
import pl.maniak.appexample.di.AppModule;
import pl.maniak.appexample.di.DaggerAppComponent;
import pl.maniak.appexample.helpers.GoogleAnalyticsHelper;


public class App extends Application {
    private static App instance = new App();
    private static AppComponent appComponent;
    private GoogleAnalyticsHelper mAnalytics;

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

    public static GoogleAnalyticsHelper getAnalytics() {

        if(getInstance().mAnalytics == null) {
            getInstance().mAnalytics = new GoogleAnalyticsHelper(getInstance());
        }
        return getInstance().mAnalytics;
    }





}
