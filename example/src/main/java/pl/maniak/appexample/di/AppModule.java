package pl.maniak.appexample.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Maniak on 2016-02-25.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
