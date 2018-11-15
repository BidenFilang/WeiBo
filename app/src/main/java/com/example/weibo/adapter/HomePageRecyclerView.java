package com.example.weibo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.weibo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageRecyclerView extends RecyclerView.Adapter<HomePageRecyclerView.ViewHolder> implements View.OnClickListener {

    //private List<Integer> imageList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    public HomePageRecyclerView(OnItemClickListener mOnItemClickListener) {
        //this.imageList = imageList;
        this.mOnItemClickListener = mOnItemClickListener;
    }



    @NonNull
    @Override
    public HomePageRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_homepage,parent,false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageRecyclerView.ViewHolder holder, int position) {
        holder.pic.setImageResource(R.mipmap.pic);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_card)
        ImageView pic;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
