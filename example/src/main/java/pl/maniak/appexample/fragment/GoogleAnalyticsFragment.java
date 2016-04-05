package pl.maniak.appexample.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.helpers.GoogleAnalyticsHelper;

/**
 * Created by Maniak on 2015-09-29.
 */
public class GoogleAnalyticsFragment extends Fragment {

    @Bind(R.id.analyticsCategory)
    AutoCompleteTextView analyticsCategory;
    @Bind(R.id.analyticsAction)
    AutoCompleteTextView analyticsAction;
    @Bind(R.id.analyticsLabel)
    AutoCompleteTextView analyticsLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("GoogleAnalyticsFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_google_analytics, null);

        ButterKnife.bind(this, root);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.sendEvnetBtn, R.id.sendScreenBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendEvnetBtn:
                if(validateFilds()) {
                    String category = analyticsCategory.getText().toString();
                    String action = analyticsAction.getText().toString();
                    String label = analyticsLabel.getText().toString();
                    App.getAnalytics().sendEvent(category, action, label);
                    clearAllViews();
                }
                break;
            case R.id.sendScreenBtn:
                App.getAnalytics().sendScreenView(getClass().getSimpleName());
                break;
        }
    }

    private boolean validateFilds() {
        if(isEmpty(analyticsAction) || isEmpty(analyticsCategory) || isEmpty(analyticsLabel)) {
            Toast.makeText(getActivity(), "All fields must be completed", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private boolean isEmpty (AutoCompleteTextView view) {
        return view.getText().toString().isEmpty();
    }

    private void clearAllViews() {
        analyticsCategory.setText("");
        analyticsAction.setText("");
        analyticsLabel.setText("");
    }
}
