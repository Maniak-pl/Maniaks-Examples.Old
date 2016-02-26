package pl.maniak.appexample.fragment;


import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.activity.MainActivity;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.model.FavoriteLocation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoldiersOfMobileFindLocationFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener {

    @Bind(R.id.findLocationMapContainer)
    FrameLayout findLocationMapContainer;
    @Bind(R.id.findLocationBtn)
    Button findLocationBtn;
    @Bind(R.id.saveLocationBtn)
    Button saveLocationBtn;
    @Bind(R.id.removeLocationBtn)
    Button removeLocationBtn;
    private GoogleMap mMap;
    private SupportMapFragment mSupportMapFragment;

    public static final int RESOLUTION_REQUEST_CODE = 1234;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private CharSequence name;
    private CharSequence address;
    private CharSequence phone;
    private LatLng latLng;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mTrackView = inflater.inflate(R.layout.fragment_soldiers_of_mobile_find_location, container, false);

        initGoogleApi();
        setMap();

        ButterKnife.bind(this, mTrackView);
        return mTrackView;
    }


    private void setMap() {
        FragmentManager fm = getChildFragmentManager();
        mSupportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.findLocationMapContainer);
        if (mSupportMapFragment == null) {
            mSupportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.findLocationMapContainer, mSupportMapFragment).commit();
        }
        mSupportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        FavoriteLocation location = ((MainActivity)getActivity()).getFavoriteLocation();
        mMap.addMarker(new MarkerOptions().position(location.getLatLng())
                .title(location.getName())
                .snippet(location.getAddress()));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(Constants.ZOOM_LEVEL));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location.getLatLng()));

    }



    private void initGoogleApi() {

        googleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();

        locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1000);
    }


    @Override
    public void onResume() {
        super.onResume();
        googleApiClient.connect();

    }


    @Override
    public void onPause() {
        super.onPause();
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        L.i("SoldiersOfMobileFindLocationFragment.onConnectionFailed() ");
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), RESOLUTION_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        L.i("SoldiersOfMobileFindLocationFragment.onConnected() ");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            handleLocation(lastLocation);
        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    private void handleLocation(Location lastLocation) {
        L.d("Last location:" + lastLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        handleLocation(location);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.findLocationBtn)
    public void onClick() {
        ((MainActivity)getActivity()).startLocationPicker();
    }
}
