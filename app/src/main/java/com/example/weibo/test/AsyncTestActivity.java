package com.example.weibo.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weibo.R;

public class AsyncTestActivity extends AppCompatActivity {

    MyTask mTask;
    Button button,cancel;
    TextView text;
    ProgressBar progressBar;

    private class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected  void onPreExecute() {
            text.setText("加载中");
        }
        @Override
        protected  String doInBackground(String... params) {
            try {
                int count = 0;
                int length = 1;
                while (count<99) {
                    count += length;
                    publishProgress(count);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            progressBar.setProgress(progresses[0]);
            text.setText("loading..."+progresses[0]+"%");
        }
        @Override
        protected void onPostExecute(String result) {
            text.setText("加载完毕");
        }
        @Override
        protected void onCancelled() {
            text.setText("已取消");
            progressBar.setProgress(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_test);
        button = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.cancel);
        text = (TextView) findViewById(R.id.text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mTask = new MyTask();
        /**了解安卓开发的流程，熟悉安卓控件的使用方式和方法，
         * 了解*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.execute();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.cancel(true);
            }
        });
    }

}
