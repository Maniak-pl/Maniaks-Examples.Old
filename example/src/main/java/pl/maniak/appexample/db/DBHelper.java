package pl.maniak.appexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.maniak.appexample.model.FavoriteLocation;

/**
 * Created by maniak on 06.03.16.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String FAVORITE_LOCATION_DB = "FavoriteLocation.db";
    public static final int DATABASE_VERSION = 1;

    private Dao<FavoriteLocation, Long> locationsDao = null;
    public static final String TAG = "Maniak";


    @Inject
    public DBHelper(Context context) {
        super(context, FAVORITE_LOCATION_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, FavoriteLocation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    private Dao<FavoriteLocation, Long> getLocationDao() throws SQLException {
        if (locationsDao == null) {
            locationsDao = getDao(FavoriteLocation.class);
        }
        return locationsDao;
    }

    public List<FavoriteLocation> getLocations() {
        List<FavoriteLocation> locations = new ArrayList<>();
        try {
            locations = getLocationDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public void saveLocation(FavoriteLocation location) {
        try {
            getLocationDao().createOrUpdate(location);
        } catch (SQLException e) {
            e.printStackTrace();

            Log.e(TAG, "saveLocation: ", e);
        }
    }

    public void deleteLocation(FavoriteLocation location) {
        try {
            DeleteBuilder<FavoriteLocation, Long> deleteBuilder = getLocationDao().deleteBuilder();
            deleteBuilder.where().eq(FavoriteLocation.LATITUDE, location.getLatitude()).and().eq(FavoriteLocation.LONGITUDE, location.getLongitude());
            deleteBuilder.delete();

            Log.e(TAG, "DELETE ");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "deleteLocation: ", e);
        }
    }
}
