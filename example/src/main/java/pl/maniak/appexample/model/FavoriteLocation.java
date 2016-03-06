package pl.maniak.appexample.model;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sony on 2016-02-26.
 */

@DatabaseTable
public class FavoriteLocation {

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String name;
    @DatabaseField(canBeNull = false)
    private String address;
    @DatabaseField(columnName = LATITUDE, canBeNull = false)
    private double latitude;
    @DatabaseField(columnName = LONGITUDE, canBeNull = false)
    private double longitude;
    @DatabaseField
    private String tag;

    public FavoriteLocation() {
    }

    public FavoriteLocation(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "FavoriteLocation{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", tag='" + tag + '\'' +
                '}';
    }
}
