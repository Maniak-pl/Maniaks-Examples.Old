package pl.maniak.appexample.di;


import pl.maniak.appexample.activity.MainActivity;
import pl.maniak.appexample.fragment.SoldiersOfMobileFindLocationFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileMapFragment;

/**
 * Created by Maniak on 2016-02-25.
 */
@dagger.Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(SoldiersOfMobileMapFragment soldiersOfMobileMapFragment);

    void inject(SoldiersOfMobileFindLocationFragment soldiersOfMobileFindLocationFragment);
}
