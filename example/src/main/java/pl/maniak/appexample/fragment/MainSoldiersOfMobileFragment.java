package pl.maniak.appexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by maniak on 23.02.16.
 */
public class MainSoldiersOfMobileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainSecurityFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_soldiers_of_mobile_main, null);
        return root;
    }
}
