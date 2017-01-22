package pl.maniak.appexample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by maniak on 07.03.16.
 */
public class FacebookLoginFragment extends Fragment {

    @BindView(R.id.login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;
    @BindView(R.id.facebookLoginName)
    TextView mLoginName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_facebook_login, null);
        ButterKnife.bind(this, root);

        initFacebook();

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }

    private void initFacebook() {

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        loginButton.setReadPermissions("public_profile");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                L.d("registerCallback - onSuccess");
            }

            @Override
            public void onCancel() {
                // App code
                L.d("registerCallback - onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                L.e("registerCallback - onError");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
