package pl.maniak.appexample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import pl.maniak.appexample.App;
import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.db.DBHelper;
import pl.maniak.appexample.model.FavoriteLocation;

/**
 * Created by Sony on 2016-02-25.
 */
public class SoldiersOfMobileMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mSupportMapFragment;

    @Inject
    public DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mTrackView = inflater.inflate(R.layout.fragment_soldiers_of_mobile_map, container, false);

        App.getAppComponent().inject(this);
        initilizeMap();

        App.getAnalytics().sendScreenView(getClass().getSimpleName());
        return mTrackView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(this);
        }

    }

    private void initilizeMap() {
        mSupportMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mSupportMapFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng androidCourse = new LatLng(52.232219699999995, 20.988004);
        mMap.addMarker(new MarkerOptions().position(androidCourse)
                .title("Advanced android programming")
                .snippet("Clever point, date: 24-26.02.2016"));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                androidCourse, Constants.ZOOM_LEVEL);
        mMap.animateCamera(cameraUpdate);

        setMarkerFromDB();

    }

    private void setMarkerFromDB() {
        List<FavoriteLocation> locations = dbHelper.getLocations();

        for (int i=0; i < locations.size();i++) {
            if(i==locations.size()-1) {
                FavoriteLocation location = locations.get(i);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(location.getName())
                        .icon(getIconColor(location.getTag()))
                        .snippet(location.getAddress()));
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                        latLng, Constants.ZOOM_LEVEL);
                mMap.animateCamera(cameraUpdate);

            } else {
                FavoriteLocation location = locations.get(i);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(location.getName())
                        .icon(getIconColor(location.getTag()))
                                .snippet(location.getAddress()));

            }
        }
    }

    private BitmapDescriptor getIconColor(String tag) {
        Log.i("Maniak", "getIconColor: "+tag);
        if(tag != null) {
            switch (tag) {
                case "goto":
                    return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                case "like":
                    return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
                default:
                    return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
            }
        }
        return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
    }



}
