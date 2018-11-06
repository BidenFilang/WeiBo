package com.example.weibo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weibo.ui.ManageAccountActivity;
import com.jaeger.library.StatusBarUtil;

import java.nio.charset.MalformedInputException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.weibo.R.id.user_id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView iv_center;
    private RelativeLayout user_id;
    private CircleImageView profile_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_action_move_light);
        }

        navView.setCheckedItem(R.id.nav_weibo);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_weibo:
                        //
                        Toast.makeText(MainActivity.this,"微博", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_notice:
//                        startActivity(new Intent(MainActivity.this,NoticeActivity.class));
                        Toast.makeText(MainActivity.this,"通知", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_collection:
                        Toast.makeText(MainActivity.this,"收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_private_msg:
                        Toast.makeText(MainActivity.this,"私信", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_hot_weibo:
                        Toast.makeText(MainActivity.this,"热门微博", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_hot_topic:
                        Toast.makeText(MainActivity.this,"热门话题", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_easy_time:
                        Toast.makeText(MainActivity.this,"轻松一刻", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_draft_box:
                        Toast.makeText(MainActivity.this,"草稿箱", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(MainActivity.this,"设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        StatusBarUtil.setColorForDrawerLayout(this,drawerLayout,getResources().getColor(R.color.colorAccent));
        iv_center = (ImageView) findViewById(R.id.iv_center);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv_center, "rotation", 0f, 360f);
        animator.setDuration(3000);
        animator.setRepeatCount(-1);
        animator.start();
        profile_photo = navView.getHeaderView(0).findViewById(R.id.profile_photo);
        ObjectAnimator _animator = ObjectAnimator.ofFloat(profile_photo,"rotation",0f,360f);
        _animator.setDuration(5000);
        _animator.setRepeatCount(-1);
        _animator.start();
        user_id = navView.getHeaderView(0).findViewById(R.id.user_id);
        user_id.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences.Editor editor=getSharedPreferences("ACCOUNT",MODE_PRIVATE).edit();
        editor.putString("name","B-biden");
        editor.putInt("icon",R.mipmap.funny);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return true;
    }

    /**
     * 初始化布局控件
     */
    private void initView() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navigation = (NavigationView) findViewById(R.id.drawer_navigation);
        iv_center = (ImageView) findViewById(R.id.iv_center);
//        user_id = (RelativeLayout) findViewById(R.id.user_id);
//        Glide.with(this).load(R.mipmap.bg_banner_dialog).into(iv_center);



    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.user_id:
                startActivity(new Intent(MainActivity.this, ManageAccountActivity.class));
                break;
        }
    }
}
