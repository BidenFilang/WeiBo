package com.example.weibo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.weibo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BasicFragment extends Fragment {
    @BindView(R.id.rl_theme_color)
    RelativeLayout rl_theme_color;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.rl_theme_color})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_theme_color:
                Toast.makeText(getActivity(),"选择颜色",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
