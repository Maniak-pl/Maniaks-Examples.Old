package pl.maniak.appexample.model;

import android.content.Context;
import android.support.v4.app.Fragment;

import pl.maniak.appexample.fragment.FacebookLoginFragment;
import pl.maniak.appexample.fragment.GoogleActivitiesRecognitionFragment;
import pl.maniak.appexample.fragment.EducativoAsyncTaskFragment;
import pl.maniak.appexample.fragment.GoogleAnalyticsFragment;
import pl.maniak.appexample.fragment.HelpTableLayoutExampleFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileExitModalFragment;
import pl.maniak.appexample.fragment.GoogleFindLocationFragment;
import pl.maniak.appexample.fragment.GitHubFloatingActionButtonFragment;
import pl.maniak.appexample.fragment.SecurityGenerationKeySHAFragment;
import pl.maniak.appexample.fragment.HelpLogFragment;
import pl.maniak.appexample.section.advancedtutorial.fragment.AdvancedTutorialMainFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileFindLocationFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileMainFragment;
import pl.maniak.appexample.fragment.GitHubMainFragment;
import pl.maniak.appexample.fragment.GoogleMainFragment;
import pl.maniak.appexample.fragment.SecurityMainFragment;
import pl.maniak.appexample.fragment.SecurityPinPatternFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileMapFragment;
import pl.maniak.appexample.fragment.SecuritySymmetricAlgorithmAESFragment;
import pl.maniak.appexample.fragment.GoogleTurnOnGPSFragment;
import pl.maniak.appexample.fragment.GoogleVoiceCommandsFragment;
import pl.maniak.appexample.fragment.HelpWebViewInjectionFragment;
import pl.maniak.appexample.fragment.UdacityAnalyticsFragment;
import pl.maniak.appexample.section.advancedtutorial.fragment.AdvancedTutorialSendingEmailFragment;

/**
 * Created by Sony on 2015-09-28.
 */
public enum FragmentStep {

    // Google

    GOOGLE_MAIN(GoogleMainFragment.class),
    GOOGLE_ANALYTICS(GoogleAnalyticsFragment.class),
    TURN_ON_GPS(GoogleTurnOnGPSFragment.class),
    FINE_LOCATION(GoogleFindLocationFragment.class),
    ACTIVITIES_RECOGNITION(GoogleActivitiesRecognitionFragment.class),
    VOICE_COMMANDS(GoogleVoiceCommandsFragment.class),

    // GitHub

    GITHUB_MAIN(GitHubMainFragment.class),
    GITHUB_FLOATING_ACTION_BUTTON(GitHubFloatingActionButtonFragment.class),

    // Udacity

    UDACITY_ANALYTICS(UdacityAnalyticsFragment.class),


    // Help

    LOG(HelpLogFragment.class),
    ASYNC_TASK_EXAMPLE(EducativoAsyncTaskFragment.class),
    TABLELAYOUT_EXAMPLE(HelpTableLayoutExampleFragment.class),
    WEBVIEW_INJECTION(HelpWebViewInjectionFragment.class),
    FACEBOOK_LOGIN(FacebookLoginFragment.class),

    // Security

    SECURITY_MAIN(SecurityMainFragment.class),
    PIN_PATTERN(SecurityPinPatternFragment.class),
    SYMMETRIC_ALGORITHM_AES(SecuritySymmetricAlgorithmAESFragment.class),
    GENERATION_KEY_SHA(SecurityGenerationKeySHAFragment.class),

    // Advanced Tutorial

    ADVANCED_TUTORIAL_MAIN(AdvancedTutorialMainFragment.class),
    ADVANCED_TUTORIAL_SENDING_EMAIL(AdvancedTutorialSendingEmailFragment.class),

    // Soldiers Of Mobile

    SOLDIERS_OF_MOBILE_MAIN(SoldiersOfMobileMainFragment.class),
    SOLDIERS_OF_MOBILE_EXIT_MODAL(SoldiersOfMobileExitModalFragment.class),
    SOLDIERS_OF_MOBILE_MAP(SoldiersOfMobileMapFragment.class),
    SOLDIERS_OF_MOBILE_FIND_LOCATION(SoldiersOfMobileFindLocationFragment.class);

    // --------------------------------------------------------

    String fragmentName;

    FragmentStep(Class<? extends Fragment> aClass) {
        fragmentName = aClass.getName();
    }

    public Fragment getFragment(Context context) {
        return Fragment.instantiate(context, fragmentName);
    }
}