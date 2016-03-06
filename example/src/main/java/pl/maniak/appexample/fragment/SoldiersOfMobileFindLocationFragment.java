package pl.maniak.appexample.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.maniak.appexample.App;
import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.db.DBHelper;
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
    @Bind(R.id.locationNameTv)
    TextView locationNameTv;
    @Bind(R.id.latituteLocationTv)
    TextView latituteLocationTv;
    @Bind(R.id.longitudeLocationTv)
    TextView longitudeLocationTv;
    @Bind(R.id.adressLocationTv)
    TextView adressLocationTv;

    @Inject
    public DBHelper dbHelper;
    @Bind(R.id.tagSpinner)
    Spinner tagSpinner;
    @Bind(R.id.loadLocationBtn)
    Button loadLocationBtn;

    private GoogleMap mMap;
    private SupportMapFragment mSupportMapFragment;

    public static final int RESOLUTION_REQUEST_CODE = 1234;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private FavoriteLocation mFavoriteLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mTrackView = inflater.inflate(R.layout.fragment_soldiers_of_mobile_find_location, container, false);


        App.getAppComponent().inject(this);
        ButterKnife.bind(this, mTrackView);

        initGoogleApi();
        setMap();

        List<String> tagList = new ArrayList<>();
        tagList.add("like");
        tagList.add("goto");

        ArrayAdapter<String> tagAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, tagList);
        tagSpinner.setAdapter(tagAdapter);

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
        setUpLocationOnMap();


    }

    private void setUpLocationOnMap() {
        if (mFavoriteLocation != null) {

            mMap.clear();
            LatLng location = new LatLng(mFavoriteLocation.getLatitude(), mFavoriteLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location)
                    .title(mFavoriteLocation.getName())
                    .snippet(mFavoriteLocation.getAddress()));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.getUiSettings().setScrollGesturesEnabled(false);
            mMap.moveCamera(CameraUpdateFactory.zoomTo(Constants.ZOOM_LEVEL));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
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


    @OnClick({R.id.findLocationBtn, R.id.loadLocationBtn, R.id.saveLocationBtn, R.id.removeLocationBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.findLocationBtn:
                startLocationPicker();
                break;
            case R.id.loadLocationBtn:
                break;
            case R.id.saveLocationBtn:
                saveLocationToDB();
                break;
            case R.id.removeLocationBtn:
                deleteLocationToDB();
                break;
        }
    }

    public void startLocationPicker() {

        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(getActivity());
            // Start the Intent by requesting a result, identified by a request code.
            startActivityForResult(intent, Constants.REQUEST_PLACE_PICKER);

            // Hide the pick option in the UI to prevent users from starting the picker
            // multiple times.
            // showPickAction(false);

        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil
                    .getErrorDialog(e.getConnectionStatusCode(), getActivity(), 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(getActivity(), "Google Play Services is not available.",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // BEGIN_INCLUDE(activity_result)
        if (requestCode == Constants.REQUEST_PLACE_PICKER) {
            // This result is from the PlacePicker dialog.

            // Enable the picker option
            //showPickAction(true);

            if (resultCode == Activity.RESULT_OK) {
                /* User has picked a place, extract data.
                    Data is extracted from the returned intent by retrieving a Place object from
                    the PlacePicker.
                */
                final Place place = PlacePicker.getPlace(data, getActivity());

                /* A Place object contains details about that place, such as its name, address
                    and phone number. Extract the name, address, phone number, place ID and place types.
                */

                final String placeId = place.getId();
                String attribution = PlacePicker.getAttributions(data);
                if (attribution == null) {
                    attribution = "";

                    mFavoriteLocation = new FavoriteLocation(place.getName().toString(), place.getAddress().toString(), place.getLatLng().latitude, place.getLatLng().longitude);
                    setLocationData();

                }

            } else {
                // User has not selected a place, hide the card.
                // getCardStream().hideCard(CARD_DETAIL);
            }

        }
        // END_INCLUDE(activity_result)
    }

    private void setLocationData() {
        if (mFavoriteLocation != null) {
            locationNameTv.setText(mFavoriteLocation.getName());
            latituteLocationTv.setText(String.valueOf(mFavoriteLocation.getLatitude()));
            longitudeLocationTv.setText(String.valueOf(mFavoriteLocation.getLongitude()));
            adressLocationTv.setText(mFavoriteLocation.getAddress());

            setUpLocationOnMap();
        }
    }

    private void saveLocationToDB() {
        if (mFavoriteLocation != null) {
            mFavoriteLocation.setTag(tagSpinner.getSelectedItem().toString());
            dbHelper.saveLocation(mFavoriteLocation);
        }
    }

    private void deleteLocationToDB() {
        if (mFavoriteLocation != null) {
            dbHelper.deleteLocation(mFavoriteLocation);
        }
    }
}
