package com.example.weibo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.weibo.adapter.HomePageRecyclerView;
import com.example.weibo.test.AsyncTestActivity;
import com.example.weibo.test.FoldingActivity;
import com.example.weibo.ui.ManageAccountActivity;
import com.example.weibo.ui.SettingActivity;
import com.jaeger.library.StatusBarUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
//    @BindView(R.id.iv_center)
//    ImageView iv_center;
    //@BindView(R.id.user_id)
    RelativeLayout user_id;
    //@BindView(R.id.profile_photo)
    CircleImageView profile_photo;
//    private FloatingActionButton fab;
    @BindView(R.id.sv_home)
    SearchView sv_home;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_homepage)
    RecyclerView recyclerView_homepage;
    @BindView(R.id.right)
    FrameLayout right;

    private HomePageRecyclerView mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        initViews();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_action_move_light);
        }
        navView.setCheckedItem(R.id.nav_weibo);
        sv_home.setIconifiedByDefault(false);
        sv_home.setSubmitButtonEnabled(true);
        sv_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        setClick();
        StatusBarUtil.setColorForDrawerLayout(this,drawerLayout,getResources().getColor(R.color.colorAccent));
        rotateIcon();
        recyclerView_homepage.setLayoutManager(new GridLayoutManager(this,2));
        mAdapter = new HomePageRecyclerView(new HomePageRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
//                Toast.makeText(MainActivity.this,""+pos,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AsyncTestActivity.class));

            }
        });
        recyclerView_homepage.setAdapter(mAdapter);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateWeibo();
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                right.layout(navView.getRight(),0,navView.getRight()+display.getWidth(),display.getHeight());
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

    }

    private void updateWeibo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //获取新微博
                        mAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initViews() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
//        navView=(NavigationView)findViewById(R.id.nav_view);
        user_id = navView.getHeaderView(0).findViewById(R.id.user_id);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        iv_center = (ImageView) findViewById(R.id.iv_center);
//        sv_home = (SearchView) findViewById(R.id.sv_home);

//        final ObjectAnimator translation = ObjectAnimator.ofFloat(iv_center,"translationY",0,750,0,-750,0);
//        final ObjectAnimator rotate = ObjectAnimator.ofFloat(iv_center,"rotation",0f,360f);
//        translation.setRepeatCount(-1);
//        rotate.setRepeatCount(-1);
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.playTogether(translation,rotate);
//        animSet.setDuration(5000);
//        animSet.start();
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
//        fab.setOnClickListener(this);

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
//                Toast.makeText(MainActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                toolbar.setVisibility(View.GONE);
                sv_home.setVisibility(View.VISIBLE);
                sv_home.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        toolbar.setVisibility(View.VISIBLE);
                        sv_home.setVisibility(View.GONE);
                        return true;
                    }
                });
                StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
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
//            case R.id.fab:
////                ObjectAnimator animator = ObjectAnimator.ofFloat(fab,"rotation",0f,45f);
////                animator.setDuration(750);
////                animator.start();
////                组合动画
//                final ObjectAnimator translation = ObjectAnimator.ofFloat(fab,"translationY",0,750,0,-750,0);
//                final ObjectAnimator rotate = ObjectAnimator.ofFloat(fab,"rotation",0f,360f);
//                final ObjectAnimator alpha = ObjectAnimator.ofFloat(fab,"alpha",1f,0f,1f);
//                translation.setDuration(5000);
//                rotate.setDuration(5000);
//                alpha.setDuration(5000);
//                translation.start();
//                translation.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        rotate.start();
//                    }
//                });
//                rotate.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        alpha.start();
//                    }
//                });
//                alpha.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        translation.start();
//                    }
//                });
//
//                startActivity(new Intent(MainActivity.this,RetrofitTestActivity.class));
//                break;
        }
    }
}
