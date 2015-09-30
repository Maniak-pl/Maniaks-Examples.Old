package pl.maniak.appexample.fragment;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.service.DetectedActivitiesIntentService;

/**
 * Created by Sony on 2015-09-30.
 */
public class ActivitiesRecognitionGoogleFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback< Status>, View.OnClickListener {


    protected ActivityDetectionBroadcastReceiver mBroadcastReceiver;
    protected GoogleApiClient mGoogleApiClient;
    private TextView mStatusText;
    private Button request_activity_updates_button, remove_activity_updates_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("ActivitiesRecognitionGoogleFragment.onCreateView() ");
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_google_activities_recognition, null);
        mStatusText = (TextView) root.findViewById(R.id.detectedActivities);
        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        remove_activity_updates_button = (Button) root.findViewById(R.id.remove_activity_updates_button);
        request_activity_updates_button = (Button) root.findViewById(R.id.request_activity_updates_button);
        remove_activity_updates_button.setOnClickListener(this);
        request_activity_updates_button.setOnClickListener(this);
        buildGoogleApiClient();
        return root;
    }

    public void requestActivityUpdatesButtonHandler() {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(getActivity(), getString(R.string.google_api_client_not_connected),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                mGoogleApiClient,
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
    }

    public void removeActivityUpdatesButtonHandler() {
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(getActivity(), getString(R.string.google_api_client_not_connected), Toast.LENGTH_SHORT).show();
            return;
        }
        // Remove all activity updates for the PendingIntent that was used to request activity
        // updates.
        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                mGoogleApiClient,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(getActivity(), DetectedActivitiesIntentService.class);

        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // requestActivityUpdates() and removeActivityUpdates().
        return PendingIntent.getService(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(ActivityRecognition.API)
                .build();
    }


    public void onResult(Status status) {
        if (status.isSuccess()) {
            L.d("Successfully added activity detection.");

        } else {
            L.d("Error adding or removing activity detection: " + status.getStatusMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register the broadcast receiver that informs this activity of the DetectedActivity
        // object broadcast sent by the intent service.
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiver,
                new IntentFilter(Constants.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        // Unregister the broadcast receiver that was registered during onResume().
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onConnected(Bundle bundle) {
        L.d("Connected to GoogleApiClient");
    }

    @Override
    public void onConnectionSuspended(int i) {
        L.i("ActivitiesRecognitionGoogleFragment.onConnectionSuspended()");
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        L.e("Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remove_activity_updates_button:
                removeActivityUpdatesButtonHandler();
                break;
            case R.id.request_activity_updates_button:
                requestActivityUpdatesButtonHandler();
                break;
        }
    }

    /**
     * Receiver for intents sent by DetectedActivitiesIntentService via a sendBroadcast().
     * Receives a list of one or more DetectedActivity objects associated with the current state of
     * the device.
     */
    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            L.i("ActivityDetectionBroadcastReceiver.onReceive()");
            ArrayList<DetectedActivity> updatedActivities =
                    intent.getParcelableArrayListExtra(Constants.DETECTED_ACTIVITIES_EXTRA);

            String strStatus = "";
            for(DetectedActivity thisActivity: updatedActivities){
                strStatus +=  getActivityString(thisActivity.getType()) + thisActivity.getConfidence() + "%\n";
            }
            mStatusText.setText(strStatus);

        }
    }

    /**
     * Returns a human readable String corresponding to a detected activity type.
     */
    public String getActivityString(int detectedActivityType) {
        Resources resources = this.getResources();
        switch(detectedActivityType) {
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.activities_recognition_in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.activities_recognition_on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.activities_recognition_on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.activities_recognition_running);
            case DetectedActivity.STILL:
                return resources.getString(R.string.activities_recognition_still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.activities_recognition_tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.activities_recognition_unknown);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.activities_recognition_walking);
            default:
                return resources.getString(R.string.activities_recognition_unidentifiable_activity, detectedActivityType);
        }
    }
}
