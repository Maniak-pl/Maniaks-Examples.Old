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


public class HomeActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigation;
    private CharSequence mDrawerTitle;

    private List<FragmentStep> stepList;
    private int currentStep = 0;

    enum Step {
        GOOGLE, GITHUB, HELP
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        L.i("HomeActivity.onCreate() ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mNavigation = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mDrawerTitle = getTitle();
        mNavigation.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        initWizard(Step.GOOGLE);
        getFragmentManager().beginTransaction().add(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();
        currentStep = 0;

    }

    private void initWizard(Step step) {

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
        switch (position) {
            case 0:
                initWizard(Step.GOOGLE);
                L.d("HomeActivity.onNavigationDrawerItemSelected() GOOGLE");
                break;
            case 1:
                initWizard(Step.GITHUB);
                L.d("HomeActivity.onNavigationDrawerItemSelected() GITHUB");
                break;
            case 2:
                initWizard(Step.HELP);
                L.d("HomeActivity.onNavigationDrawerItemSelected() HELP");
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.container, getFragment(stepList.get(0)), "stepFragment").commit();

    }

    private Fragment getFragment(final FragmentStep fragmentStep) {
        return fragmentStep.getFragment(this);
    }

}
