package pl.maniak.appexample.model;

import android.app.Fragment;
import android.content.Context;

import pl.maniak.appexample.fragment.FineLocationGoogleFragment;

/**
 * Created by Sony on 2015-09-28.
 */
public enum FragmentStep {
    FINE_LOCATION(FineLocationGoogleFragment.class);

    String fragmentName;

    FragmentStep(Class<? extends Fragment> aClass) {
        fragmentName = aClass.getName();
    }

    public Fragment getFragment(Context context) {
        return Fragment.instantiate(context, fragmentName);
    }
}