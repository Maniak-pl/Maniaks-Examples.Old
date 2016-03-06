package pl.maniak.appexample.activity;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.fragment.SoldiersOfMobileExitModalFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileFindLocationFragment;
import pl.maniak.appexample.modals.ExitDialogFragment;
import pl.maniak.appexample.model.FavoriteLocation;
import pl.maniak.appexample.model.FragmentStep;
import pl.maniak.appexample.model.Step;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, SoldiersOfMobileExitModalFragment.ExitCallback {

    private Button nextBt, prevBt;


    private List<FragmentStep> stepList;
    private int currentStep;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        L.i("MainActivity.onCreate() ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        prevBt = (Button) findViewById(R.id.prevBt);
        nextBt = (Button) findViewById(R.id.nextBt);

        nextBt.setOnClickListener(this);
        prevBt.setOnClickListener(this);

        initFragmentStep(Step.SOLDIERS_OF_MOBILE);
        currentStep = 0;

    }

    @Override
    protected void onStart() {
        L.i("MainActivity.onStart() ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        L.i("MainActivity.onResume() ");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        L.i("MainActivity.onRestart() ");
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        L.i("MainActivity.onWindowFocusChanged() ");
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause() {
        L.i("MainActivity.onPause() ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        L.i("MainActivity.onStop() ");
        super.onStop();
    }

    private void initFragmentStep(Step step) {
        L.i("MainActivity.initFragmentStep() ");
        stepList = Constants.getFragmentSteps(step);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();
        currentStep = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_google:
                initFragmentStep(Step.GOOGLE);
                L.d("MainActivity.onNavigationDrawerItemSelected() GOOGLE");
                break;
            case R.id.nav_github:
                initFragmentStep(Step.GITHUB);
                L.d("MainActivity.onNavigationDrawerItemSelected() GITHUB");
                break;
            case R.id.nav_help:
                initFragmentStep(Step.HELP);
                L.d("MainActivity.onNavigationDrawerItemSelected() HELP");
                break;
            case R.id.nav_advanced_tutorial:
                initFragmentStep(Step.ADVANCED_TUTORIAL);
                L.d("MainActivity.onNavigationDrawerItemSelected() ADVANCED_TUTORIAL");
                break;
            case R.id.nav_security:
                initFragmentStep(Step.SECURITY);
                L.d("MainActivity.onNavigationDrawerItemSelected() SECURITY");
                break;
            case R.id.nav_soldiers_of_mobile:
                initFragmentStep(Step.SOLDIERS_OF_MOBILE);
                L.d("MainActivity.onNavigationDrawerItemSelected() SOLDIERS_OF_MOBILE");
                break;
            case R.id.nav_share:
                shareApp();
                break;
            case R.id.nav_send:
                sendMail();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void shareApp() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.about_share_title));
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + this.getApplicationContext().getPackageName());
        Intent intent = Intent.createChooser(shareIntent, getString(R.string.share));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ignored) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage(R.string.msg_intent_failed);
            builder.setPositiveButton(R.string.ok, null);
            builder.show();
        }
    }

    private void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.about_email), null));
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.about_message_subject));
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.about_mail_chooser)));
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, getString(R.string.msg_intent_failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_menu_close:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private Fragment getFragment(final FragmentStep fragmentStep) {
        return fragmentStep.getFragment(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextBt:
                changeStep(true);
                break;
            case R.id.prevBt:
                changeStep(false);
                break;
        }
    }

    public void changeStep(boolean isNest) {
        currentStep = isNest ? nextStep() : previousStep();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currentStep >= stepList.size()) {
            currentStep = 0;
        }
        ft.replace(R.id.container, getFragment(stepList.get(currentStep)), "stepFragment").commit();
    }

    private int nextStep() {
        if (currentStep == stepList.size() - 1) {
            return 0;
        }
        return ++currentStep;
    }

    private int previousStep() {
        if (currentStep == 0) {
            return stepList.size() - 1;
        }
        return --currentStep;
    }

    @Override
    public void exit() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ExitDialogFragment dialogFragment = new ExitDialogFragment();
        dialogFragment.show(transaction, "ExitDialogFragment");
    }


}
