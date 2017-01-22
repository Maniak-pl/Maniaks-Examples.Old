package pl.maniak.appexample.section.tutorialspoint.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

public class TutorialspointJSONParserFragment extends Fragment {


    @BindView(R.id.parserJSON)
    TextView parserJSON;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("TutorialspointJSONParserFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tutorialspoint_json_parser, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        ButterKnife.bind(this, root);

        prepareJSON();

        return root;
    }


    private void prepareJSON() {
        String strJson = "{" +
                "\"Employee\" :[" +
                "{" +
                "\"id\":\"01\"," +
                "\"name\":\"Gopal Varma\"," +
                "\"salary\":\"500000\" " +
                "}, " +
                "{" +
                "\"id\":\"02\"," +
                "\"name\":\"Sairamkrishna\"," +
                "\"salary\":\"500000\"" +
                "}," +
                "{" +
                "\"id\":\"03\"," +
                "\"name\":\"Sathish kallakuri\"," +
                "\"salary\":\"600000\"" +
                "}" +
                "]" +
                "}";
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObject(i);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Node " + i + " : \n id = " + id + " \n Name = " + name + " \n Salary = " + salary + " \n\n ";
            }
            parserJSON.setText(data);

        } catch (JSONException e) {
            L.e("TutorialspointJSONParserFragment.setView() ", e);
        }
    }

}
