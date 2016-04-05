package pl.maniak.appexample.fragment;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by Maniak on 2015-09-28.
 */
public class GoogleFindLocationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
  /*
     1. Wymagany jest import biblioteki compile 'com.google.android.gms:play-services-location:7.+'
     2. Musimy dodać meta-data do AndroidManifest
     3. Musimy dodać uprawnienia
    */

    private TextView mLongitudeTv, mLatitudeTv;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        L.i("FineLocationGoogleFragment.onCreateView() ");
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_google_fine_location, null);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mLongitudeTv = (TextView) root.findViewById(R.id.LongitudeTv);
        mLatitudeTv = (TextView) root.findViewById(R.id.LatitudeTv);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Connect the Client
        mGoogleApiClient.connect();
    }


    @Override
    public void onStop() {
        super.onStop();
        // Disconnect the client
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        // 1 - create a Location Request called mLocationRequest
        mLocationRequest = LocationRequest.create();
        // 2 - set it's priority to high accuracy
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // 3 - set it to update every second (1000 ms)
        mLocationRequest.setInterval(1000);

        // 4 - Call requestLocationUpdates in the Api with this request
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLatitudeTv.setText(Double.toString(location.getLatitude())); // Latitude - Szerokość
        mLongitudeTv.setText(Double.toString(location.getLongitude())); // Longitude - długość geograficzną

    }
}