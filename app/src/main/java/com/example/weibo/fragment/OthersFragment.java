package com.example.weibo.fragment;

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

public class OthersFragment extends Fragment {
    @BindView(R.id.rl_about)
    RelativeLayout rl_about;
    @BindView(R.id.rl_donate)
    RelativeLayout rl_donate;
    @BindView(R.id.rl_favorable)
    RelativeLayout rl_favorable;
    @BindView(R.id.rl_feedback)
    RelativeLayout rl_feedback;
    @BindView(R.id.rl_version_info)
    RelativeLayout rl_version_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_others,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.rl_about,R.id.rl_version_info,R.id.rl_feedback,R.id.rl_donate,R.id.rl_favorable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_about:
                Toast.makeText(getActivity(),"关于",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_donate:
                Toast.makeText(getActivity(),"捐赠开发者",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_favorable:
                Toast.makeText(getActivity(),"给我好评",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_version_info:
                Toast.makeText(getActivity(),"版本信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_feedback:
                Toast.makeText(getActivity(),"意见反馈",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
