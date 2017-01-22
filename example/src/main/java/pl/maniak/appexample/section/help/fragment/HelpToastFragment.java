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
import pl.maniak.appexample.section.help.view.SystemToast;

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

    @OnClick({R.id.imageToastBtn, R.id.infoToastBtn, R.id.confirmToastBtn, R.id.warningToastBtn, R.id.alertToastBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageToastBtn:
                ImageToast.show(getActivity(), "Android System", false);
                break;
            case R.id.infoToastBtn:
                SystemToast.show(getActivity(), "Data has been sent", SystemToast.STYLE_INFO);
                break;
            case R.id.confirmToastBtn:
                SystemToast.show(getActivity(), "You're done!", SystemToast.STYLE_CONFIRM);
                break;
            case R.id.warningToastBtn:
                SystemToast.show(getActivity(), "No internet connection", SystemToast.STYLE_WARNING);
                break;
            case R.id.alertToastBtn:
                SystemToast.show(getActivity(), "Username is incorrect", SystemToast.STYLE_ALERT);
                break;
        }
    }
}
