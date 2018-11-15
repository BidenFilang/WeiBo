package com.example.weibo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weibo.R;
import com.jaeger.library.StatusBarUtil;

public class FoldingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folding);
        StatusBarUtil.setTransparent(this);
    }
}
