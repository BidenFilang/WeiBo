package com.example.weibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weibo.R;

import java.util.ArrayList;
import java.util.List;

public class AccountRecyclerView extends RecyclerView.Adapter<AccountRecyclerView.ViewHolder> implements View.OnClickListener {
    private List<String> account_id=new ArrayList<>();
    private List<Integer> account_icon=new ArrayList<>();
    private static View v;

    public interface ItemClickListener {
        void ItemClick(View view,int position);
    }
    private ItemClickListener itemClickListener;

    public AccountRecyclerView(ItemClickListener itemClickListener) {
//        this.account_id = account_id;
//        this.account_icon = account_icon;
        this.itemClickListener = itemClickListener;
        account_id.add("B-biden");
    }

    @Override
    public AccountRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_recyclerview,parent,false);
        final ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_account_icon;
        TextView tv_account_name;
        public ViewHolder(View view) {
            super(view);
            v=view;
            iv_account_icon = (ImageView) view.findViewById(R.id.iv_account_image);
            tv_account_name = (TextView) view.findViewById(R.id.tv_account_name);
        }
    }

    @Override
    public void onBindViewHolder(AccountRecyclerView.ViewHolder holder, int position) {
        String account_name = account_id.get(position);
        //Integer account_image = account_icon.get(position);
//        holder.iv_account_icon.setImageResource(account_image);
        Glide.with(v.getContext()).load(R.mipmap.funny).into(holder.iv_account_icon);
        holder.tv_account_name.setText(account_name);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return account_id.size();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener!=null) {
            itemClickListener.ItemClick(view,(int)view.getTag());
        }

    }
}
