package pl.maniak.appexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.R;

/**
 * Created by pliszka on 29.02.16.
 */
public class HelpTableLayoutExampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_table_layout_example, null);
        return root;
    }
}
