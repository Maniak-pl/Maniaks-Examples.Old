package pl.maniak.appexample.common.log;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.model.CommandLog;


/**
 * Created by pliszka on 09.09.15.
 */
public class L {

    public static final String TAG = "Maniak";

    private static List<CommandLog> logList = new ArrayList();

    public static void i(String str) {
        if (Constants.DEBUG_LOG) {
            addLogToList(str, R.color.green);
            Log.i(TAG, str);
        }
    }

    public static void w(String str) {
        if (Constants.DEBUG_LOG) {
            Log.w(TAG, str);
            addLogToList(str, R.color.orange);
        }
    }

    public static void d(String str) {
        if (Constants.DEBUG_LOG) {
            Log.d(TAG, str);
            addLogToList(str, R.color.blue);
        }
    }

    public static void e(String str) {
        if (Constants.DEBUG_LOG) {
            Log.e(TAG, str);
            addLogToList(str, R.color.red);
        }
    }

    public static void e(String str, Exception e) {
        if (Constants.DEBUG_LOG) {
            if (e != null) {
                Log.e(TAG, str + " Exception: ", e);
                addLogToList(str+ ": "+e.toString(), R.color.red);
            }
        }
    }

    private static void addLogToList(String mgs, int color) {
        if(logList.size() > Constants.SIZE_LOG) {
            logList.remove(0);
        }
        logList.add(new CommandLog(mgs,color));
    }

    public static List<CommandLog> getLogList() {
        return logList;
    }

}

