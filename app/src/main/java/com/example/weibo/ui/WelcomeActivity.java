package com.example.weibo.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weibo.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private int count = 4;
    private TextView countdowm;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        timer.schedule(task, 1000, 1000);//等待一秒 ，停顿一秒
        //正常情况下不点击跳过
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        }, 4000);//延迟三秒后发送handler信息

    }

    /**
     * 初始化
     * */
    private void initView() {
        countdowm = findViewById(R.id.countdowm_tv);
        countdowm.setOnClickListener(this);
    }

    /**
     * 点击跳过
     * */
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.countdowm_tv:
                //跳转到首页面
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    count--;
                    countdowm.setText("跳过"+count);
                    if (count < 0) {
                        timer.cancel();
                        countdowm.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };


}
