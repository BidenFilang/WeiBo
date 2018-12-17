package com.example.weibo.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.weibo.R;
import com.example.weibo.ui.ManageAccountActivity;
import com.example.weibo.ui.OutlineSettingActivity;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvanceFragment extends Fragment {
    @BindView(R.id.ll_account_manage)
    LinearLayout ll_account_manage;
    @BindView(R.id.rl_notification_center)
    RelativeLayout rl_notification_center;
    @BindView(R.id.rl_data_control)
    RelativeLayout rl_data_control;
    @BindView(R.id.rl_outline_setting)
    RelativeLayout rl_outline_setting;
    @BindView(R.id.rl_delay_post)
    RelativeLayout rl_delay_post;
    @BindView(R.id.rl_inner_explorer)
    RelativeLayout rl_inner_explorer;
    @BindView(R.id.rl_memory_resident)
    RelativeLayout rl_memory_resident;
    @BindView(R.id.rl_rotate_image)
    RelativeLayout rl_rotate_image;
    @BindView(R.id.iv_delay_post)
    ImageView iv_delay_post;
    @BindView(R.id.iv_inner_explorer)
    ImageView iv_inner_explorer;
    @BindView(R.id.iv_memory_resident)
    ImageView iv_memory_resident;
    @BindView(R.id.iv_rotate_image)
    ImageView iv_rotate_image;

    private static int DELAY_POST_VALUE=0;
    private static int INNER_EXPLORER_VALUE=0;
    private static int MEMORY_RESIDENT_VALUE=0;
    private static int ROTATE_IAMGE_VALUE=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //initView();
        View view = inflater.inflate(R.layout.fragment_advance,container,false);
        ButterKnife.bind(this,view);
        return view;

    }


    @OnClick({R.id.ll_account_manage,R.id.rl_notification_center,R.id.rl_data_control,
            R.id.rl_outline_setting,R.id.rl_delay_post,R.id.rl_inner_explorer,
            R.id.rl_memory_resident,R.id.rl_rotate_image })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_account_manage:
                startActivity(new Intent(getActivity(), ManageAccountActivity.class));
                break;
            case R.id.rl_notification_center:
//                startActivity(new Intent(getActivity(),NotificationActivity.class));
                Toast.makeText(getActivity(),"通知中心",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_data_control:
//                startActivity(new Intent(getActivity(),DataControlActivity.class));
                Toast.makeText(getActivity(),"流量控制",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_outline_setting:
                startActivity(new Intent(getActivity(),OutlineSettingActivity.class));
                Toast.makeText(getActivity(),"离线设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_delay_post:
                if (DELAY_POST_VALUE == 0) {
                    DELAY_POST_VALUE = 1;
                    iv_delay_post.setImageResource(R.mipmap.selected);
                } else {
                    DELAY_POST_VALUE = 0;
                    iv_delay_post.setImageResource(R.mipmap.un_selected);
                }
                break;
            case R.id.rl_inner_explorer:
                if (INNER_EXPLORER_VALUE == 0) {
                    INNER_EXPLORER_VALUE = 1;
                    iv_inner_explorer.setImageResource(R.mipmap.un_selected);
                } else {
                    INNER_EXPLORER_VALUE = 0;
                    iv_inner_explorer.setImageResource(R.mipmap.selected);
                }
                break;
            case R.id.rl_memory_resident:
                if (MEMORY_RESIDENT_VALUE == 0) {
                    MEMORY_RESIDENT_VALUE = 1;
                    iv_memory_resident.setImageResource(R.mipmap.un_selected);
                } else {
                    MEMORY_RESIDENT_VALUE = 0;
                    iv_memory_resident.setImageResource(R.mipmap.selected);
                }
                break;
            case R.id.rl_rotate_image:
                if (ROTATE_IAMGE_VALUE == 0) {
                    ROTATE_IAMGE_VALUE = 1;
                    iv_rotate_image.setImageResource(R.mipmap.selected);
                } else {
                    ROTATE_IAMGE_VALUE = 0;
                    iv_rotate_image.setImageResource(R.mipmap.un_selected);
                }
                break;
        }
    }
}
