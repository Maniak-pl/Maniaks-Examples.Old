package pl.maniak.appexample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;

/**
 * Created by Sony on 2015-10-29.
 */
public class SecurityPinPatternFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_security_pin_pattern, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return root;
    }
}
