package pl.maniak.appexample.model;

import android.content.Context;
import android.support.v4.app.Fragment;

import pl.maniak.appexample.fragment.ActivitiesRecognitionGoogleFragment;
import pl.maniak.appexample.fragment.AsyncTaskEducativoFragment;
import pl.maniak.appexample.fragment.FineLocationGoogleFragment;
import pl.maniak.appexample.fragment.FloatingActionButtonGitHubFragment;
import pl.maniak.appexample.fragment.LogHelpFragment;
import pl.maniak.appexample.fragment.MainGitHubFragment;
import pl.maniak.appexample.fragment.MainGoogleFragment;
import pl.maniak.appexample.fragment.MainSecurityFragment;
import pl.maniak.appexample.fragment.PinPatternSecurityFragment;
import pl.maniak.appexample.fragment.SymmetricAlgorithmAESFragment;
import pl.maniak.appexample.fragment.TurnOnGPSGoogleFragment;
import pl.maniak.appexample.fragment.VoiceCommandsFragment;
import pl.maniak.appexample.fragment.WebViewInjectionHelpFragment;

/**
 * Created by Sony on 2015-09-28.
 */
public enum FragmentStep {

    // Google

    GOOGLE_MAIN(MainGoogleFragment.class),
    TURN_ON_GPS(TurnOnGPSGoogleFragment.class),
    FINE_LOCATION(FineLocationGoogleFragment.class),
    ACTIVITIES_RECOGNITION(ActivitiesRecognitionGoogleFragment.class),
    VOICE_COMMANDS(VoiceCommandsFragment.class),

    // GitHub

    GITHUB_MAIN(MainGitHubFragment.class),
    GITHUB_FLOATING_ACTION_BUTTON(FloatingActionButtonGitHubFragment.class),

    // Help

    LOG(LogHelpFragment.class),
    ASYNC_TASK_EXAMPLE(AsyncTaskEducativoFragment.class),
    WEBVIEW_INJECTION(WebViewInjectionHelpFragment.class),

    // Security

    SECURITY_MAIN(MainSecurityFragment.class),
    PIN_PATTERN(PinPatternSecurityFragment.class),
    SYMMETRIC_ALGORITHM_AES(SymmetricAlgorithmAESFragment.class);

    // --------------------------------------------------------

    String fragmentName;

    FragmentStep(Class<? extends Fragment> aClass) {
        fragmentName = aClass.getName();
    }

    public Fragment getFragment(Context context) {
        return Fragment.instantiate(context, fragmentName);
    }
}