package com.example.weibo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.weibo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> mList;
    private List<String> url_list = new ArrayList<>();
    private DragAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        mList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            mList.add("" + i);
        }
        url_list.add("https://www.baidu.com/");
        url_list.add("https://www.nowcoder.com/440699");
        url_list.add("http://www.runoob.com/");
        url_list.add("https://www.baidu.com/");
        url_list.add("https://www.nowcoder.com/440699");
        url_list.add("http://www.runoob.com/");
        url_list.add("https://www.baidu.com/");
        url_list.add("https://www.nowcoder.com/440699");
        url_list.add("http://www.runoob.com/");
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new DragAdapter(mList,new DragAdapter.ItemClickListener(){
           @Override
           public void ItemClick(View view, int pos) {
               Toast.makeText(TestActivity.this,url_list.get(pos),Toast.LENGTH_SHORT).show();

           }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //获取触摸相应的方向 包含两个：
                int swipeFlags = ItemTouchHelper.LEFT;
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                return makeMovementFlags(dragFlags,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();
                if (fromPosition > targetPosition) {
                    for (int i = fromPosition; i< targetPosition; i++) {
                        Collections.swap(mList,i,i+1);
                    }
                } else {
                    for (int i = fromPosition; i > targetPosition; i--) {
                        Collections.swap(mList,i,i-1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition,targetPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mList.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);

    }
}
