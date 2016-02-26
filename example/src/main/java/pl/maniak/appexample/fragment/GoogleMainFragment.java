package pl.maniak.appexample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Maniak on 2015-09-29.
 */
public class GoogleMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainGoogleFragment.onCreateView() ");
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_google_main, null);

        return root;
    }
}
