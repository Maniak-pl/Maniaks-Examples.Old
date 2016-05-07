package pl.maniak.appexample.section.help.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Maniak on 2015-09-29.
 */
public class HelpMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("HelpMainFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_main, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }
}
