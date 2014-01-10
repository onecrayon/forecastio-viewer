package com.onecrayon.forecastio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load in basic Activity (which includes the web view)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate our WebView
        WebView mainWebView = (WebView) findViewById(R.id.mainWebView);
        // Make sure scrollbars are inside the viewport (to prevent extra padding)
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Enable Javascript
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Enable localStorage
        webSettings.setDomStorageEnabled(true);
        // Ensure that forecast.io links open in this app, and others open elsewhere
        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        // Load up the root site!
        mainWebView.loadUrl("http://forecast.io");
    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // If navigating to forecast.io, just load the URL
            if (Uri.parse(url).getHost().equals("forecast.io")) {
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

}
