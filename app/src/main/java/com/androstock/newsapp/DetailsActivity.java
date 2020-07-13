package com.androstock.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DetailsActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar loader;
    String url = "";
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        b1=(Button) findViewById(R.id.b);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        loader = findViewById(R.id.loader);
        webView = findViewById(R.id.webView);



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loader.setVisibility(View.VISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loader.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(url);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, " " +item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId())
        {
            case R.id.app_bar_search:

                return true;
            case R.id.rate_item:

                return true;
            case R.id.copy_item:

                return true;
            case R.id.print_item:

                {

                sync T = new sync(this);
                T.execute();   // this will call do in background
            }
                return true;
            case R.id.share_item:
            {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s={"",""};
                i.putExtra(Intent.EXTRA_EMAIL,s);
                i.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                i.putExtra(Intent.EXTRA_TEXT,"");
                i.setType("message/rfc822");//setting MIME Email Multipurpose Internet Mail Extensions
                Intent chooser = Intent.createChooser(i,"Launch Email");
                startActivity(chooser);
            }
            return true;
            case R.id.bookmark_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}