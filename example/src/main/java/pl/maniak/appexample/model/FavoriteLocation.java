package pl.maniak.appexample.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Sony on 2016-02-26.
 */
public class FavoriteLocation {
    private String name;
    private String address;
    private String phone;
    private LatLng latLng;

    public FavoriteLocation() {
    }

    public FavoriteLocation(String name, String address, LatLng latLng) {
        this.name = name;
        this.address = address;
        this.latLng = latLng;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
