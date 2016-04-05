package pl.maniak.appexample.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import pl.maniak.appexample.App;
import pl.maniak.appexample.R;
import pl.maniak.appexample.common.log.L;

/**
 * Created by pliszka on 09.06.15.
 */
public class HelpWebViewInjectionFragment extends Fragment {

    WebView mWebView;
    String rbank = "https://www.r-bank.pl/flex-pbl/index.jsp";
    String sslBlocked = "https://192.168.0.24/"; // Ten link dla bezpieczeństwa powinien być blokowany


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help_web_view_injection, null);
        init(root);

        App.getAnalytics().sendScreenView(getClass().getSimpleName());

        return root;
    }

    private void init(ViewGroup root) {

         mWebView = (WebView) root.findViewById(R.id.webView);

        // ADD URL

        setUpWebView(rbank);

    }

    private void setUpWebView(String url) {
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new BankWebViewClientTest());

    }

    public class BankWebViewClientTest extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            L.i("BankWebViewClientTest.onPageFinished() URL = " + url);
            mWebView.setVisibility(View.VISIBLE);
            L.d("WebView.VISIBLE ");
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);

            L.i("BankWebViewClientTest.onLoadResource()  URL = " + url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            L.i("BankWebViewClientTest.onReceivedError() ");
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            L.i("BankWebViewClientTest.onPageStarted() ");

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            L.i("BankWebViewClientTest.shouldOverrideUrlLoading() ");
            return super.shouldOverrideUrlLoading(view, url);
        }
    }



}
