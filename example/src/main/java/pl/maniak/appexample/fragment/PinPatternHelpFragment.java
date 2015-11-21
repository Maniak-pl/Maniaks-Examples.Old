package pl.maniak.appexample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Sony on 2015-10-29.
 */
public class PinPatternHelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_pin_pattern, null);
        return root;
    }
}
