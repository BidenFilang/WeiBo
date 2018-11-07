package com.example.weibo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import com.example.weibo.ui.SettingActivity;
import com.jaeger.library.StatusBarUtil;

import java.nio.charset.MalformedInputException;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.weibo.R.id.start;
import static com.example.weibo.R.id.user_id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ImageView iv_center;
    private RelativeLayout user_id;
    private CircleImageView profile_photo;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        initViews();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_action_move_light);
        }
        navView.setCheckedItem(R.id.nav_weibo);
        setClick();
        StatusBarUtil.setColorForDrawerLayout(this,drawerLayout,getResources().getColor(R.color.colorAccent));
        rotateIcon();


    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navView=(NavigationView)findViewById(R.id.nav_view);
        user_id = navView.getHeaderView(0).findViewById(R.id.user_id);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    /**
     * NavigationView中子元素点击事件
     */
    private void setClick() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_weibo:
                        Toast.makeText(MainActivity.this,"微博", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("微博");
                        break;
                    case R.id.nav_notice:
                        Toast.makeText(MainActivity.this,"通知", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("通知");
                        break;
                    case R.id.nav_collection:
                        Toast.makeText(MainActivity.this,"收藏", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("收藏");
                        break;
                    case R.id.nav_private_msg:
                        Toast.makeText(MainActivity.this,"私信", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("私信");
                        break;
                    case R.id.nav_hot_weibo:
                        Toast.makeText(MainActivity.this,"热门微博", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("热门微博");
                        break;
                    case R.id.nav_hot_topic:
                        Toast.makeText(MainActivity.this,"热门话题", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("热门话题");
                        break;
                    case R.id.nav_easy_time:
                        Toast.makeText(MainActivity.this,"轻松一刻", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("轻松一刻");
                        break;
                    case R.id.nav_draft_box:
                        Toast.makeText(MainActivity.this,"草稿箱", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("草稿箱");
                        break;
                    case R.id.nav_setting:
                        startActivity(new Intent(MainActivity.this,SettingActivity.class));
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        user_id.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    private void rotateIcon() {
        profile_photo = navView.getHeaderView(0).findViewById(R.id.profile_photo);
        ObjectAnimator animator = ObjectAnimator.ofFloat(profile_photo,"rotation",0f,360f);
        animator.setDuration(5000);
        animator.setRepeatCount(-1);
        animator.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences.Editor editor=getSharedPreferences("ACCOUNT",MODE_PRIVATE).edit();
        editor.putString("name","B-biden");
        editor.putInt("icon",R.mipmap.funny);
        editor.apply();
    }

    /**
     * 顶部菜单栏元素的点击事件
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search:
                Toast.makeText(MainActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_write_blog:
                Toast.makeText(MainActivity.this,"写博客",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_begin_quit:
                Toast.makeText(MainActivity.this,"开始离线",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_quit:
                Toast.makeText(MainActivity.this,"退出",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.user_id:
                startActivity(new Intent(MainActivity.this, ManageAccountActivity.class));
                break;
            case R.id.fab:
                ObjectAnimator animator = ObjectAnimator.ofFloat(fab,"rotation",0f,45f);
                animator.setDuration(750);
                animator.start();
                break;
        }
    }
}
