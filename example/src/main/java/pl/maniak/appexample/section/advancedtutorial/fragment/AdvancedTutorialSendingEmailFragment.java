package pl.maniak.appexample.section.advancedtutorial.fragment;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Maniak on 2015-09-29.
 */
public class AdvancedTutorialSendingEmailFragment extends Fragment {

    @Bind(R.id.sendingEmailExtraEmail)
    AutoCompleteTextView tvEmailTo;
    @Bind(R.id.sendingEmailExtraCc)
    AutoCompleteTextView tvExtraCc;
    @Bind(R.id.sendingEmailExtraSubject)
    AutoCompleteTextView tvExtraSubject;
    @Bind(R.id.sendingEmailExtraText)
    AutoCompleteTextView tvExtraText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("MainGoogleFragment.onCreateView() ");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_advanced_tutorial_sending_email, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.sendEmailBtn)
    public void onClick() {
        sendEmail();
    }

    private void sendEmail() {
        L.d("Send email");

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {tvEmailTo.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_CC, new String[] {tvExtraCc.getText().toString()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, tvExtraSubject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, tvExtraText.getText().toString());

        L.d( "sendEmail:  "+tvEmailTo.getText());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            L.d("Finished sending email... ");

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_LONG).show();
        }
    }
}
