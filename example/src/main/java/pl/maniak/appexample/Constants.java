package pl.maniak.appexample;

import java.util.ArrayList;
import java.util.List;

import pl.maniak.appexample.common.log.L;
import pl.maniak.appexample.model.FragmentStep;
import pl.maniak.appexample.model.NavDraItem;
import pl.maniak.appexample.model.Step;

/**
 * Created by pliszka on 28.09.15.
 */
public class Constants {

    public final static boolean DEBUG_LOG = true;
    public final static int SIZE_LOG = 20;

    private static List<NavDraItem> navDraItems = new ArrayList();
    private static List<FragmentStep> fragmentSteps = new ArrayList();

    public static List<NavDraItem> getNavDraItems() {
        navDraItems.clear();
        navDraItems.add(new NavDraItem("Google", R.drawable.ic_google));
        navDraItems.add(new NavDraItem("GitHub", R.drawable.ic_github));
        navDraItems.add(new NavDraItem("Help", R.drawable.ic_help));
        return navDraItems;
    }

    public static List<FragmentStep> getFragmentSteps(Step step){
        fragmentSteps.clear();
        switch (step) {
            case GOOGLE:
                fragmentSteps.add(FragmentStep.GOOGLE_MAIN);
                fragmentSteps.add(FragmentStep.FINE_LOCATION);
                break;
            case GITHUB:
                break;
            case HELP:
                fragmentSteps.add(FragmentStep.LOG);
                break;
        }
        return fragmentSteps;
    }


}
