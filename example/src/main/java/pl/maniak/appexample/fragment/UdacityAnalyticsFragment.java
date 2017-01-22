package pl.maniak.appexample.fragment;


import android.content.Intent;
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
import pl.maniak.appexample.section.udacity.analytics.activity.DinnerMainActivity;

/**
 * Created by Maniak on 2015-09-29.
 */
public class UdacityAnalyticsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("UdacityAnalyticsFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_udacity_analytics, null);

        ButterKnife.bind(this, root);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return root;
    }

    @OnClick(R.id.showDinnerActivity)
    public void onClick() {
        startActivity(new Intent(getActivity(), DinnerMainActivity.class));
    }
}
