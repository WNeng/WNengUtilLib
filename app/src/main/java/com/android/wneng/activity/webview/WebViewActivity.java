package com.android.wneng.activity.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.wneng.R;
import com.android.wneng.utils.toast.ToastHelper;
import com.android.wneng.widget.Html5Webview;

/**
 * webview
 * Created by Auro on 15/9/22.
 */
public class WebViewActivity extends AppCompatActivity {

    public final static String URL = "url";

    Html5Webview webview;

    private Context context;
    private boolean hasMainActivity = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new Html5Webview(this);
        setContentView(webview.getLayout());
        String url = null;
        if (getIntent() != null){
            url = getIntent().getStringExtra(URL);
        }

        if (url == null) {
            ToastHelper.showToast(R.string.error_url);
            finish();
            return;
        }

        if (!url.startsWith("http"))
            url = "http://" + url;
        initView(url);
    }

//    @Override
//    protected void doBack(int keyCode, KeyEvent event) {
//        if (hasMainActivity) {
//            NavigationHelper.finish(this, RESULT_OK, null);
//        } else {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
//    }
//
//    @Override
//    protected boolean isSwipeback() {
//        return (hasMainActivity = AppManager.getAppManager().hasActivity(MainActivity.class));
//    }

    private void initView(String url) {
        context = this;
        webview.getSettings().setLoadsImagesAutomatically(true);// 设置可以自动加载图片
        webview.setHorizontalScrollBarEnabled(true);//设置水平滚动条
        webview.setVerticalScrollBarEnabled(false);//设置竖直滚动条
        webview.loadUrl(url);
        webview.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            LoadingDialog.getInstance(context).showLoading();
        }

        public void onPageFinished(WebView view, String url) {
//            LoadingDialog.getInstance(context).hideLoading();
        }


    }

}
