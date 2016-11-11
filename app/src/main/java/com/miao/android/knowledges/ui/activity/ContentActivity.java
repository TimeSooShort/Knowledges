package com.miao.android.knowledges.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.miao.android.knowledges.R;
import com.miao.android.knowledges.bean.ContentBean;

/**
 * Created by Administrator on 2016/10/10.
 */

public class ContentActivity extends AppCompatActivity {

    private WebView mWebView;
    private ImageView mImageView;
    private TextView mTextView;
    private RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initViews();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        queue = Volley.newRequestQueue(this);

        String contentUrl = "http://news-at.zhihu.com/api/4/news/" + id;
        StringRequest stringRequest = new StringRequest(contentUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ContentBean contentBean = gson.fromJson(response, ContentBean.class);
                display(contentBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR!",error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void display(ContentBean contentBean) {
        String contentBody = contentBean.getBody();
        String contentTitle = contentBean.getTitle();
        String imageUrl = contentBean.getImage();
        String css = contentBean.getCss().get(0);

        String html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css + "\">" + contentBody;

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        mTextView.setText(contentTitle);

        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mImageView.setImageResource(R.drawable.background);
            }
        });
        queue.add(imageRequest);
    }

    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.content_imageView);
        mWebView = (WebView) findViewById(R.id.content_webView);
        mTextView = (TextView) findViewById(R.id.content_title);
    }
}
