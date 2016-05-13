package pl.maniak.appexample.section.help.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.section.help.view.ImageToast;

/**
 * Created by Maniak on 2015-09-29.
 */
public class HelpToastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("HelpToastFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_toast, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.toastBtn)
    public void onClick() {
        ImageToast.show(getActivity(), "Launcher Icon", false);
    }
}
