package com.example.weibo.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.weibo.R;
import com.example.weibo.adapter.AccountRecyclerView;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class ManageAccountActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AccountRecyclerView adapter;
    private List<String> account_id=new ArrayList<>();
    private List<Integer> account_icon=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        initView();
        StatusBarUtil.setColor(this,getResources().getColor(R.color.color_toolbar));
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        setSupportActionBar(toolbar);

        //searchAccount();
        adapter=new AccountRecyclerView(new AccountRecyclerView.ItemClickListener() {
            @Override
            public void ItemClick(View view, int position) {
                Toast.makeText(ManageAccountActivity.this,"DELETE",Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {

        return true;
    }

    private void searchAccount() {
        SharedPreferences pref = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
        String name=pref.getString("name","");

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_account);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_account);

    }
}
