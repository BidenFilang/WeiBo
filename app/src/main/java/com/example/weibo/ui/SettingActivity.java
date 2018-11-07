package com.example.weibo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.weibo.R;
import com.example.weibo.fragment.AdvanceFragment;
import com.example.weibo.fragment.AssistanceFragment;
import com.example.weibo.fragment.BasicFragment;
import com.example.weibo.fragment.OthersFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private Toolbar toolbar_setting;
    private TabLayout tablayout;
    private ViewPager viewPager;

    private String[] titles={"基本","高级","其他","帮助"};
    private List<Fragment> fragmentList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        initView();
        setSupportActionBar(toolbar_setting);
        toolbar_setting.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fragmentList = new ArrayList<>();
        fragmentList.add(new BasicFragment());
        fragmentList.add(new AdvanceFragment());
        fragmentList.add(new OthersFragment());
        fragmentList.add(new AssistanceFragment());
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        toolbar_setting = (Toolbar) findViewById(R.id.toolbar_setting);
        viewPager = (ViewPager) findViewById(R.id.viewpager_setting);
        tablayout = (TabLayout) findViewById(R.id.tablayout_setting);
        tablayout.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        tablayout.setTabTextColors(getResources().getColor(R.color.grey),getResources().getColor(R.color.white));
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

   class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

       @Override
       public Fragment getItem(int i) {
           return fragmentList.get(i);
       }

       @Override
       public int getCount() {
           return fragmentList.size();
       }

       @Override
       public CharSequence getPageTitle(int position) {
            return titles[position];
       }
   }
}
