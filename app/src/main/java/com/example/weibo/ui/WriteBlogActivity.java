package com.example.weibo.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.weibo.R;
import com.jaeger.library.StatusBarUtil;

public class WriteBlogActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_blog);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        initViews();
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setTitle("用户授权");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_write_blog);

    }
}
