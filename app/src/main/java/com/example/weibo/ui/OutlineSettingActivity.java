package com.example.weibo.ui;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weibo.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OutlineSettingActivity extends AppCompatActivity {
    @BindView(R.id.rl_outline_group)
    RelativeLayout rl_outline_group;
    @BindView(R.id.rl_num_of_weibo)
    RelativeLayout rl_num_of_weibo;
    @BindView(R.id.toolbar_outline_setting)
    Toolbar toolbar;
    @BindView(R.id.tv_group_item)
    TextView tv_group_item;
    @BindView(R.id.tv_num_of_blog)
    TextView tv_num_of_blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_outline_setting);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @OnClick({R.id.rl_outline_group,R.id.rl_num_of_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_outline_group:
                AlertDialog.Builder builder = new AlertDialog.Builder(OutlineSettingActivity.this);
                builder.setTitle("设置离线分组");
                builder.setMultiChoiceItems(new String[]{"特别关注","nba","知识","搞笑","情感","明星","游戏",
                        "电商","音乐人","美妆","婚纱","美食","媒体","学生","名人明星","同学","同事"},null,null);

                builder.setPositiveButton("确定",null);
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            case R.id.rl_num_of_weibo:
                AlertDialog.Builder _builder = new AlertDialog.Builder(OutlineSettingActivity.this);
                _builder.setTitle("每次离线微博");
                _builder.setSingleChoiceItems(new String[]{"50条", "100条", "200条"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                _builder.setNegativeButton("CANCEL",null);
                _builder.show();
                break;
        }
    }
}
