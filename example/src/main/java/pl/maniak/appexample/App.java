package pl.maniak.appexample;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;


import java.util.List;



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
