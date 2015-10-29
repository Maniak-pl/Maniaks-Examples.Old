package pl.maniak.appexample.activity;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.List;

import pl.maniak.appexample.Constants;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.fragment.NavigationDrawerFragment;
import pl.maniak.appexample.model.FragmentStep;
import pl.maniak.appexample.model.Step;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    private Button nextBt, prevBt;
    private NavigationDrawerFragment mNavigation;

    private List<FragmentStep> stepList;
    private int currentStep;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        L.i("MainActivity.onCreate() ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigation = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigation.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
        prevBt = (Button) findViewById(R.id.prevBt);
        nextBt = (Button) findViewById(R.id.nextBt);

        nextBt.setOnClickListener(this);
        prevBt.setOnClickListener(this);

        initFragmentStep(Step.HELP);
        getFragmentManager().beginTransaction().add(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigation.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        L.i("MainActivity.onNavigationDrawerItemSelected() ");
        switch (position) {
            case 0:
                initFragmentStep(Step.GOOGLE);
                L.d("MainActivity.onNavigationDrawerItemSelected() GOOGLE");
                break;
            case 1:
                initFragmentStep(Step.GITHUB);
                L.d("MainActivity.onNavigationDrawerItemSelected() GITHUB");
                break;
            case 2:
                initFragmentStep(Step.HELP);
                L.d("MainActivity.onNavigationDrawerItemSelected() HELP");
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();

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

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if(currentStep >= stepList.size()){
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
        if(currentStep == 0) {
            return stepList.size() -1;
        }
        return --currentStep;
    }

}
