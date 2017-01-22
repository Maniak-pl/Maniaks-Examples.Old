package pl.maniak.appexample.model;

import android.content.Context;
import android.support.v4.app.Fragment;

import pl.maniak.appexample.fragment.FacebookLoginFragment;
import pl.maniak.appexample.fragment.GoogleActivitiesRecognitionFragment;
import pl.maniak.appexample.fragment.EducativoAsyncTaskFragment;
import pl.maniak.appexample.fragment.GoogleAnalyticsFragment;
import pl.maniak.appexample.section.help.fragment.HelpMainFragment;
import pl.maniak.appexample.section.help.fragment.HelpTableLayoutExampleFragment;
import pl.maniak.appexample.fragment.SoldiersOfMobileExitModalFragment;
import pl.maniak.appexample.fragment.GoogleFindLocationFragment;
import pl.maniak.appexample.fragment.GitHubFloatingActionButtonFragment;
import pl.maniak.appexample.fragment.SecurityGenerationKeySHAFragment;
import pl.maniak.appexample.section.help.fragment.HelpLogFragment;
import pl.maniak.appexample.section.help.fragment.HelpToastFragment;
import pl.maniak.appexample.section.help.fragment.HelpVideoFragment;
import pl.maniak.appexample.section.rxjava.fragment.RxJavaMainFragment;
import pl.maniak.appexample.section.rxjava.fragment.RxJavaSearchPeopleFragment;
import pl.maniak.appexample.section.tutorialspoint.fragment.TutorialspointJSONParserFragment;
import pl.maniak.appexample.section.tutorialspoint.fragment.TutorialspointMainFragment;
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
import pl.maniak.appexample.section.help.fragment.HelpWebViewInjectionFragment;
import pl.maniak.appexample.fragment.UdacityAnalyticsFragment;
import pl.maniak.appexample.section.tutorialspoint.fragment.TutorialspointNotificationsFragment;
import pl.maniak.appexample.section.tutorialspoint.fragment.TutorialspointSendingEmailFragment;
import pl.maniak.appexample.section.tutorialspoint.fragment.TutorialspointWidgetsFragment;

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

    // RxJava

    RXJAVA_MAIN(RxJavaMainFragment.class),
    RXJAVA_SEARCH_PEOPLE(RxJavaSearchPeopleFragment.class),

    // Help

    HELP_MAIN(HelpMainFragment.class),
    LOG(HelpLogFragment.class),
    CUSTOME_TOAST(HelpToastFragment.class),
    ASYNC_TASK_EXAMPLE(EducativoAsyncTaskFragment.class),
    TABLELAYOUT_EXAMPLE(HelpTableLayoutExampleFragment.class),
    WEBVIEW_INJECTION(HelpWebViewInjectionFragment.class),
    VIDEO(HelpVideoFragment.class),
    FACEBOOK_LOGIN(FacebookLoginFragment.class),

    // Security

    SECURITY_MAIN(SecurityMainFragment.class),
    PIN_PATTERN(SecurityPinPatternFragment.class),
    SYMMETRIC_ALGORITHM_AES(SecuritySymmetricAlgorithmAESFragment.class),
    GENERATION_KEY_SHA(SecurityGenerationKeySHAFragment.class),

    // Advanced Tutorial

    TUTORIALSPOINT_MAIN(TutorialspointMainFragment.class),
    TUTORIALSPOINT_JSON_PARSER(TutorialspointJSONParserFragment.class),
    TUTORIALSPOINT_NOTIFICATION(TutorialspointNotificationsFragment.class),
    TUTORIALSPOINT_SENDING_EMAIL(TutorialspointSendingEmailFragment.class),
    TUTORIALSPOINT_WIDGETS(TutorialspointWidgetsFragment.class),

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