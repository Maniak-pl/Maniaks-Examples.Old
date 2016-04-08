package pl.maniak.appexample.section.udacity.analytics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import pl.maniak.appexample.R;

public class RemoveMealActivity extends Activity {
    String selectedDinnerExtrasKey = String.valueOf(R.string.selected_dinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_info);
    }

    protected void onStart() {
        super.onStart();

        // Set the heading
        TextView heading_tv = (TextView) findViewById(R.id.textView_info_heading);
        heading_tv.setText(getResources().getText(R.string.dislike_dinner_heading));

        // Set the text
        TextView tv = (TextView) findViewById(R.id.textView_info);
        String dinner = getIntent().getStringExtra(selectedDinnerExtrasKey);
        tv.setText(dinner + " \n\n" + getResources().getText(R.string.dislike_dinner));
    }

}