package pl.maniak.appexample.fragment;

import android.app.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.model.CommandLog;

/**
 * Created by Maniak on 28.09.15.
 */
public class LogHelpFragment extends Fragment implements View.OnClickListener {

    ArrayAdapter<CommandLog> adapter;
    ListView consoleLogLv;
    Button logInfoBtn, logWarningBtn, logDebugBtn, logErrorBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("LogHelpFragment.onCreateView() ");
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_help_log, null);

        logInfoBtn = (Button) root.findViewById(R.id.logInfoBtn);
        logDebugBtn = (Button) root.findViewById(R.id.logDebugBtn);
        logWarningBtn = (Button) root.findViewById(R.id.logWarningBtn);
        logErrorBtn = (Button) root.findViewById(R.id.logErrorBtn);
        logInfoBtn.setOnClickListener(this);
        logWarningBtn.setOnClickListener(this);
        logDebugBtn.setOnClickListener(this);
        logErrorBtn.setOnClickListener(this);

        consoleLogLv = (ListView) root.findViewById(R.id.consoleLogLv);

        adapter = new ArrayAdapter<CommandLog>(getActivity() ,R.layout.item_simple_list_log, L.getLogList()){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {


                if (convertView == null) {
                    LayoutInflater mInflater = (LayoutInflater)
                            getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    convertView = mInflater.inflate(R.layout.item_simple_list_log, null);
                }

                TextView textView = (TextView) convertView.findViewById(R.id.consoleLogTv);
                textView.setText(getItem(position).getMgs());
                textView.setTextColor(getResources().getColor(getItem(position).getColor()));

                return convertView;
            }
        };

        consoleLogLv.setAdapter(adapter);


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        L.i("LogHelpFragment.onStart() ");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("LogHelpFragment.onResume() ");
       consoleLogLv.invalidate();
       adapter.notifyDataSetChanged();
    }


    @Override
    public void onPause() {
        super.onPause();
        L.i("LogHelpFragment.onPause() ");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        L.i("LogHelpFragment.onStop() ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logInfoBtn:
                L.i("LogHelpFragment.onClick()  --> Log.INFO");
                break;
            case R.id.logDebugBtn:
                L.d("LogHelpFragment.onClick()  --> Log.DEBUG");
                break;
            case R.id.logWarningBtn:
                L.w("LogHelpFragment.onClick()  --> Log.WARN");
                break;
            case R.id.logErrorBtn:
                L.e("LogHelpFragment.onClick()  --> Log.ERROR");
                break;

        }
        adapter.notifyDataSetChanged();
    }
}
