package com.example.weibo.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.weibo.R;
import com.example.weibo.ui.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;

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
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(null,"支付宝账号");
                clipboard.setPrimaryClip(clipData);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("捐赠开发者");
                builder.setMessage("如果你觉得源码对你有用，支付宝账号。。。已复制到黏贴板，捐赠一点碎银子");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("打开支付宝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = "https://github.com/";
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
                builder.show();
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
