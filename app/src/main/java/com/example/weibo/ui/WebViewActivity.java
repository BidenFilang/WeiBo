package com.example.weibo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.weibo.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_webview)
    Toolbar toolbar_webview;
    @BindView(R.id.load)
    ProgressBar load;
    @BindView(R.id.webView)
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view,int newProgress) {
                if (newProgress == 100) {
                    load.setVisibility(View.GONE);
                } else {
                    load.setVisibility(View.VISIBLE);
                    load.setProgress(newProgress);
                }
            }
        });
        webView.loadUrl(url);
        //load.setProgress(webView.getProgress());
    }
}
