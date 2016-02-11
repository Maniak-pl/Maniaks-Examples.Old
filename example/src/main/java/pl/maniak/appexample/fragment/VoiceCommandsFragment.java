package pl.maniak.appexample.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import pl.maniak.appexample.R;
import pl.maniak.appexample.activity.MainActivity;
import pl.maniak.appexample.common.log.L;


/**
 * Created by pliszka on 10.09.15.
 */
public class VoiceCommandsFragment extends Fragment implements View.OnClickListener {

    public ListView mList;
    public Button speakButton;

    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_google_voice_commands, null);
        speakButton = (Button) root.findViewById(R.id.btn_speak);
        speakButton.setOnClickListener(this);
        speakButton = (Button) root.findViewById(R.id.btn_speak);
        mList = (ListView) root.findViewById(R.id.list);

        return root;
    }

    public void gotoNext() {
        L.i("VoiceCommandsFragment.gotoNext() ");
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).changeStep(true);
        } else {
            throw new RuntimeException("Activity containing this fragment must be an instance of ActivityNotificationWizzard!");
        }
    }

    public void gotoBack() {
        L.i("VoiceCommandsFragment.gotoBack() ");
        if(getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).changeStep(false);
        } else {
            throw new RuntimeException("Activity containing this fragment must be an instance of ActivityNotificationWizzard!");
        }
    }


    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        startVoiceRecognitionActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it
            // could have heard
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mList.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, matches));
            // matches is the result of voice input. It is a list of what the
            // user possibly said.
            // Using an if statement for the keyword you want to use allows the
            // use of any activity if keywords match
            // it is possible to set up multiple keywords to use the same
            // activity so more than one word will allow the user
            // to use the activity (makes it so the user doesn't have to
            // memorize words from a list)
            // to use an activity from the voice input information simply use
            // the following format;
            // if (matches.contains("keyword here") { startActivity(new
            // Intent("name.of.manifest.ACTIVITY")

            if (matches.contains("dalej")) {
                gotoNext();
            }
            if (matches.contains("Dalej")) {
                gotoNext();
            }

            if(matches.contains("cofnij")) {
                gotoBack();
            }

            if(matches.contains("Cofnij")) {
                gotoBack();
            }
        }
    }
}