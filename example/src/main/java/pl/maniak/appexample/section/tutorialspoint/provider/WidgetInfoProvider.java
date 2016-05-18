package pl.maniak.appexample.section.tutorialspoint.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.activity.MainActivity;
import pl.maniak.appexample.section.help.view.SystemToast;

/**
 * Created by maniak on 17.05.16.
 */
public class WidgetInfoProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for(int i=0; i<appWidgetIds.length; i++){
            int currentWidgetId = appWidgetIds[i];


            Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            PendingIntent pending = PendingIntent.getActivity(context, 0,intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_tutorialspoint_info);
            views.setTextViewText(R.id.widgetInfoTv, Hawk.get(Constants.HAWK_WIDGET_INFO, context.getString(R.string.tutorialspoint_widgets_info)));
            views.setOnClickPendingIntent(R.id.widgetInfoBtn, pending);
            appWidgetManager.updateAppWidget(currentWidgetId, views);
            SystemToast.show(context, "Widget added", SystemToast.STYLE_CONFIRM);
        }
    }
}
