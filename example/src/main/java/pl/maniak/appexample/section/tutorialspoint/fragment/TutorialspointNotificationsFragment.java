package pl.maniak.appexample.section.tutorialspoint.fragment;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.activity.MainActivity;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Maniak on 2015-09-29.
 */
public class TutorialspointNotificationsFragment extends Fragment {

    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_tutorialspoint_notifications, null);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.startNotificationBtn)
    public void displayNotification() {
        L.i("Display Notification");

        // STEP 1 - Create Notification Builder

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());

        // STEP 2 - Setting Notification Properties

        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);

        /* Increase notification number every time a new notification arrives */

        mBuilder.setNumber(++numMessages);


        /* STEP 3 - Attach Actions
            Creates an explicit intent for an Activity in your app */

        Intent resultIntent = new Intent(getActivity(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        // STEP 4 - Issue the Notification

        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    @OnClick(R.id.cancelNotificationBtn)
    public void cancelNotification() {
        L.i("Cancel Notification");
        if(mNotificationManager != null) {
            mNotificationManager.cancel(notificationID);
        }
    }


    @OnClick(R.id.updateNotificationBtn)
    public void updateNotification() {
    }
}
