package pl.maniak.appexample.section.tutorialspoint.fragment;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.section.tutorialspoint.provider.WidgetInfoProvider;

/**
 * Created by Maniak on 2015-09-29.
 */
public class TutorialspointWidgetsFragment extends Fragment {


    @Bind(R.id.fragmentWidgetTextInput)
    AutoCompleteTextView widgetTextInput;
    @Bind(R.id.fragmentWidgetInfoTv)
    TextView descriptionWidgetTv;
    @Bind(R.id.fragmentWidgetInfoBtn)
    Button saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("TutorialspointWidgetsFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tutorialspoint_widgets, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        ButterKnife.bind(this, root);

        setupView();

        return root;
    }

    private void setupView() {
        widgetTextInput.setText(Hawk.get(Constants.HAWK_WIDGET_INFO, getString(R.string.tutorialspoint_widgets_info)));
        descriptionWidgetTv.setText(Hawk.get(Constants.HAWK_WIDGET_INFO, getString(R.string.tutorialspoint_widgets_info)));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fragmentWidgetInfoBtn)
    public void onClick() {
        descriptionWidgetTv.setText(widgetTextInput.getText().toString());
        Hawk.put(Constants.HAWK_WIDGET_INFO, widgetTextInput.getText().toString());
        updateWidget(getActivity());


    }

    private void updateWidget(Context context) {
        L.e("TutorialspointWidgetsFragment.updateWidget() ");
        Intent intent = new Intent(context,WidgetInfoProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int ids[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetInfoProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        context.sendBroadcast(intent);
    }
}
