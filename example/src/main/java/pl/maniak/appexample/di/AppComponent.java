package pl.maniak.appexample.di;


import pl.maniak.appexample.activity.MainActivity;

/**
 * Created by Maniak on 2016-02-25.
 */
@dagger.Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainActivity activity);
}
