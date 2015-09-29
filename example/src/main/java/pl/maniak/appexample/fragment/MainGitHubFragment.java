package pl.maniak.appexample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Sony on 2015-09-29.
 */
public class MainGitHubFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainGitHubFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_github_main, null);
        return root;
    }
}
