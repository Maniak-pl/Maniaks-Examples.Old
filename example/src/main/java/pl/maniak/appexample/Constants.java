package pl.maniak.appexample;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

import pl.maniak.appexample.model.FragmentStep;
import pl.maniak.appexample.model.NavDraItem;
import pl.maniak.appexample.model.Step;

public class Constants {

    public final static boolean DEBUG_LOG = true;
    public final static int SIZE_LOG = 20;
    public final static int ZOOM_LEVEL=15;

    public static final int REQUEST_PLACE_PICKER = 4421;

    public static final String PACKAGE_NAME = "pl.maniak.appexample";

    public static final String BROADCAST_ACTION = PACKAGE_NAME + ".BROADCAST_ACTION";

    public static final String DETECTED_ACTIVITIES_EXTRA = PACKAGE_NAME + ".DETECTED_ACTIVITIES_EXTRA";

    public static final String HAWK_WIDGET_INFO = "HAWK_WIDGET_INFO";

    /**
     * The desired time between activity detections. Larger values result in fewer activity
     * detections while improving battery life. A value of 0 results in activity detections at the
     * fastest possible rate. Getting frequent updates negatively impact battery life and a real
     * app may prefer to request less frequent updates.
     */
    public static final long DETECTION_INTERVAL_IN_MILLISECONDS = 0;

    /**
     * List of DetectedActivity types that we monitor in this sample.
     */
    protected static final int[] MONITORED_ACTIVITIES = {
            DetectedActivity.STILL,
            DetectedActivity.ON_FOOT,
            DetectedActivity.WALKING,
            DetectedActivity.RUNNING,
            DetectedActivity.ON_BICYCLE,
            DetectedActivity.IN_VEHICLE,
            DetectedActivity.TILTING,
            DetectedActivity.UNKNOWN
    };

    private static List<FragmentStep> fragmentSteps = new ArrayList<>();

    public static List<FragmentStep> getFragmentSteps(Step step){
        fragmentSteps.clear();
        switch (step) {
            case GOOGLE:
                fragmentSteps.add(FragmentStep.GOOGLE_MAIN);
                fragmentSteps.add(FragmentStep.GOOGLE_ANALYTICS);
//                fragmentSteps.add(FragmentStep.TURN_ON_GPS);
                fragmentSteps.add(FragmentStep.FINE_LOCATION);
                fragmentSteps.add(FragmentStep.ACTIVITIES_RECOGNITION);
                fragmentSteps.add(FragmentStep.VOICE_COMMANDS);
                break;
            case GITHUB:
                fragmentSteps.add(FragmentStep.GITHUB_MAIN);
                fragmentSteps.add(FragmentStep.GITHUB_FLOATING_ACTION_BUTTON);
                break;
            case UDACITY:
                fragmentSteps.add(FragmentStep.UDACITY_ANALYTICS);
                break;
            case RXJAVA:
                fragmentSteps.add(FragmentStep.RXJAVA_MAIN);
                fragmentSteps.add(FragmentStep.RXJAVA_SEARCH_PEOPLE);
                break;
            case HELP:
                fragmentSteps.add(FragmentStep.HELP_MAIN);
                fragmentSteps.add(FragmentStep.LOG);
                fragmentSteps.add(FragmentStep.CUSTOME_TOAST);
                fragmentSteps.add(FragmentStep.TABLELAYOUT_EXAMPLE);
                fragmentSteps.add(FragmentStep.VIDEO);
                fragmentSteps.add(FragmentStep.WEBVIEW_INJECTION);
                fragmentSteps.add(FragmentStep.ASYNC_TASK_EXAMPLE);
                fragmentSteps.add(FragmentStep.FACEBOOK_LOGIN);
                break;
            case ADVANCED_TUTORIAL:
                fragmentSteps.add(FragmentStep.TUTORIALSPOINT_MAIN);
                fragmentSteps.add(FragmentStep.TUTORIALSPOINT_JSON_PARSER);
                fragmentSteps.add(FragmentStep.TUTORIALSPOINT_NOTIFICATION);
                fragmentSteps.add(FragmentStep.TUTORIALSPOINT_SENDING_EMAIL);
                fragmentSteps.add(FragmentStep.TUTORIALSPOINT_WIDGETS);
                break;
            case SOLDIERS_OF_MOBILE:
                fragmentSteps.add(FragmentStep.SOLDIERS_OF_MOBILE_MAIN);
                fragmentSteps.add(FragmentStep.SOLDIERS_OF_MOBILE_EXIT_MODAL);
                fragmentSteps.add(FragmentStep.SOLDIERS_OF_MOBILE_MAP);
                fragmentSteps.add(FragmentStep.SOLDIERS_OF_MOBILE_FIND_LOCATION);

                break;
            case SECURITY:
                fragmentSteps.add(FragmentStep.SECURITY_MAIN);
                fragmentSteps.add(FragmentStep.PIN_PATTERN);
                fragmentSteps.add(FragmentStep.SYMMETRIC_ALGORITHM_AES);
                fragmentSteps.add(FragmentStep.GENERATION_KEY_SHA);
                break;

        }
        return fragmentSteps;
    }


}
