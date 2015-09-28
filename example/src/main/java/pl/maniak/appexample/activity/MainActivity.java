package pl.maniak.appexample.activity;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.fragment.NavigationDrawerFragment;
import pl.maniak.appexample.model.FragmentStep;


public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigation;
    private CharSequence mDrawerTitle;

    private List<FragmentStep> stepList;
    private int currentStep = 0;

    enum Step {
        GOOGLE, GITHUB, HELP
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        L.i("MainActivity.onCreate() ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigation = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mDrawerTitle = getTitle();
        mNavigation.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        initWizard(Step.GOOGLE);
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

    private void initWizard(Step step) {
        L.i("MainActivity.initWizard() ");
        stepList = new ArrayList();
        switch (step) {
            case GOOGLE:
                stepList.add(FragmentStep.FINE_LOCATION);
                break;
            case GITHUB:
                break;
            case HELP:
                stepList.add(FragmentStep.LOG);
                break;
        }


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
        actionBar.setTitle(mDrawerTitle);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        L.i("MainActivity.onNavigationDrawerItemSelected() ");
        switch (position) {
            case 0:
                initWizard(Step.GOOGLE);
                L.d("MainActivity.onNavigationDrawerItemSelected() GOOGLE");
                break;
            case 1:
                initWizard(Step.GITHUB);
                L.d("MainActivity.onNavigationDrawerItemSelected() GITHUB");
                break;
            case 2:
                initWizard(Step.HELP);
                L.d("MainActivity.onNavigationDrawerItemSelected() HELP");
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();

    }

    private Fragment getFragment(final FragmentStep fragmentStep) {
        return fragmentStep.getFragment(this);
    }

}
