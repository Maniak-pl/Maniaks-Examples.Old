package pl.maniak.appexample.helpers;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;

/**
 * Created by maniak on 05.04.16.
 */
public class GoogleAnalyticsHelper {

    private Tracker mTracker;
    private Context mContext;

    public GoogleAnalyticsHelper(Context context) {
        mContext = context;
    }

    // Get the tracker associated with this app
    public void startTracking() {

        // Initialize an Analytics tracker using a Google Analytics property ID.

        // Does the Tracker already exist?
        // If not, create it

        if (mTracker == null) {
            GoogleAnalytics ga = GoogleAnalytics.getInstance(mContext);

            // Get the config data for the tracker
            mTracker = ga.newTracker(R.xml.google_analytics_api);

            // Enable tracking of activities
            ga.enableAutoActivityReports(App.getInstance());

            // Set the log level to verbose.
            ga.getLogger()
                    .setLogLevel(Logger.LogLevel.VERBOSE);
        }
    }

    public Tracker getTracker() {
        // Make sure the tracker exists
        startTracking();

        // Then return the tracker
        return mTracker;
    }

    public void sendScreenView(String screenName) {
        getTracker().setScreenName(screenName);
        getTracker().send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendEvent(String category, String action, String label) {

        // Send the hit
        getTracker().send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }
}
