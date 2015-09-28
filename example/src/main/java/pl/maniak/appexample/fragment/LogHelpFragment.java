package pl.maniak.appexample.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.model.CommandLog;

/**
 * Created by pliszka on 28.09.15.
 */
public class LogHelpFragment extends Fragment {


    ListView consoleLogLv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("LogHelpFragment.onCreateView() ");
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_help_log, null);

        try {
            throw new Exception("Exception Test");
        } catch (Exception e) {
            L.e("LogHelpFragment.onCreateView() ", e);
        }


        consoleLogLv = (ListView) root.findViewById(R.id.consoleLogLv);

        ArrayAdapter<CommandLog> adapter=new ArrayAdapter<CommandLog>(getActivity() ,R.layout.item_simple_list_log, L.getLogList()){

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
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("LogHelpFragment.onResume() ");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.i("LogHelpFragment.onPause() ");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.i("LogHelpFragment.onStop() ");
    }
}
