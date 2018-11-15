package com.example.weibo.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weibo.R;

import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> implements View.OnClickListener{

    private List<String> list;



    public interface ItemClickListener {
        void ItemClick(View view,int position);
    }
    private ItemClickListener mItemClick;

    public DragAdapter(List<String> list,ItemClickListener mItemClick) {
        this.list = list;
        this.mItemClick = mItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item,parent,false);
        final ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onClick(View v) {
        if (mItemClick != null) {
            mItemClick.ItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView (recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager ();
        if(manager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager)manager;
            gridLayoutManager.setSpanSizeLookup (new GridLayoutManager.SpanSizeLookup () {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        }
    }


    @Override
    public void onBindViewHolder(@NonNull DragAdapter.ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView del;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.text);
        }


    }


}
