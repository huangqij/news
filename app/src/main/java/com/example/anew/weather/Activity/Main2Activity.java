package com.example.anew.weather.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

;import com.example.anew.weather.R;

import static android.view.KeyEvent.KEYCODE_BACK;

public class Main2Activity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String item_url = getIntent().getStringExtra("p1url");
        webView = findViewById(R.id.webview);
        //访问网页
        webView.loadUrl(item_url);

        //webwiew支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //当需要跳转另一个网页时任在webview中显示
        webView.setWebViewClient(new WebViewClient());

        //是否可以后退webView.canGoBack();
        //后退网页webView.goBack();

}

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static void actionStart(Context context, String url) {
        Intent intent=new Intent(context,Main2Activity.class);
        intent.putExtra("p1url",url);
        //intent.putExtra("p2content",content);
        //intent.putExtra("p3",imageId);
        context.startActivity(intent);
    }
}
