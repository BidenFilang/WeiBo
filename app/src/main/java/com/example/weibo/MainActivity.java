package com.example.weibo;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView iv_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        initView();
        //设置toolbar标题文本
        toolbar.setTitle("首页");
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        toolbar.inflateMenu(R.menu.toolbar);
        setSupportActionBar(toolbar);
        //设置左上角图标是否可点击
        getSupportActionBar().setHomeButtonEnabled(true);
        //左上角加上一个返回图标
        //初始化ActionBarDrawerToggle(ActionBarDrawerToggle就是一个开关一样用来打开或者关闭drawer)
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close) {
            /**
             * 抽屉菜单打开监听
             * @param drawerView
             */
            @Override
            public void onDrawerOpened(View drawerView) {
                //Toast.makeText(MainActivity.this,"菜单打开了",Toast.LENGTH_SHORT).show();
                super.onDrawerOpened(drawerView);
            }

            /**
             * 抽屉菜单关闭监听
             * @param drawerView
             */
            @Override
            public void onDrawerClosed(View drawerView) {
                //Toast.makeText(MainActivity.this,"菜单关闭了",Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }
        };
        /**
         * NavigationView设置点击监听
         */
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerToggle.syncState();
        //设置DrawerLayout的抽屉开关监听
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 初始化布局控件
     */
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation = (NavigationView) findViewById(R.id.drawer_navigation);
        iv_center = (ImageView) findViewById(R.id.iv_center);
        Glide.with(this).load(R.mipmap.bg_banner_dialog).into(iv_center);
    }


}
