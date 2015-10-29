package pl.maniak.appexample;

import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.model.FragmentStep;
import pl.maniak.appexample.model.NavDraItem;
import pl.maniak.appexample.model.Step;

/**
 * Created by pliszka on 28.09.15.
 */
public class Constants {

    public final static boolean DEBUG_LOG = true;
    public final static int SIZE_LOG = 20;

    public static final String PACKAGE_NAME = "pl.maniak.appexample";

    public static final String BROADCAST_ACTION = PACKAGE_NAME + ".BROADCAST_ACTION";

    public static final String DETECTED_ACTIVITIES_EXTRA = PACKAGE_NAME + ".DETECTED_ACTIVITIES_EXTRA";

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

    private static List<NavDraItem> navDraItems = new ArrayList();
    private static List<FragmentStep> fragmentSteps = new ArrayList();

    public static List<NavDraItem> getNavDraItems() {
        navDraItems.clear();
        navDraItems.add(new NavDraItem("Google", R.drawable.ic_google));
        navDraItems.add(new NavDraItem("GitHub", R.drawable.ic_github));
        navDraItems.add(new NavDraItem("Help", R.drawable.ic_help));
        return navDraItems;
    }

    public static List<FragmentStep> getFragmentSteps(Step step){
        fragmentSteps.clear();
        switch (step) {
            case GOOGLE:
                fragmentSteps.add(FragmentStep.GOOGLE_MAIN);
//                fragmentSteps.add(FragmentStep.TURN_ON_GPS);
                fragmentSteps.add(FragmentStep.FINE_LOCATION);
                fragmentSteps.add(FragmentStep.ACTIVITIES_RECOGNITION);
                break;
            case GITHUB:
                fragmentSteps.add(FragmentStep.GITHUB_MAIN);
                break;
            case HELP:
                fragmentSteps.add(FragmentStep.PIN_PATTERN);
                fragmentSteps.add(FragmentStep.LOG);
                break;
        }
        return fragmentSteps;
    }


}
