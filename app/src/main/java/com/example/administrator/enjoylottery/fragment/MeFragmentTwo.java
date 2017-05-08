package com.example.administrator.enjoylottery.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.activity.GroupCardActivity;
import com.example.administrator.enjoylottery.activity.HelpActivity;
import com.example.administrator.enjoylottery.activity.HomeActivity;
import com.example.administrator.enjoylottery.activity.LotterySiteAuthenticationActivity;
import com.example.administrator.enjoylottery.activity.PersonalInformationActivity;
import com.example.administrator.enjoylottery.activity.SetUpActivity;
import com.example.administrator.enjoylottery.activity.WalletActivity;
import com.example.administrator.enjoylottery.bean.LoginBean;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.tools.SharedPreferencesUtils;
import com.google.gson.Gson;

import io.rong.imageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class MeFragmentTwo extends BaseFragment implements View.OnClickListener {
    private View view;
    private RelativeLayout qianBao, kaBao, bangZhu, sheZhi, caiZhan;
    private LinearLayout head;
    private TextView back, name, cailiaoName;
    private Intent intent;
    private static ImageView headIv;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me_success, null, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        ((TextView) view.findViewById(R.id.every_top_text)).setText("我的");
        qianBao = (RelativeLayout) view.findViewById(R.id.item_lay_faxian_sao);
        kaBao = (RelativeLayout) view.findViewById(R.id.item_lay_bangzhuzhongxin);
        bangZhu = (RelativeLayout) view.findViewById(R.id.item_lay_mimashezhi);
        sheZhi = (RelativeLayout) view.findViewById(R.id.item_lay_shezhi);
        caiZhan = (RelativeLayout) view.findViewById(R.id.item_lay_renzhengzhongxin);
        back = (TextView) view.findViewById(R.id.quanju_dengchu);
        head = (LinearLayout) view.findViewById(R.id.lay_head);
        name = (TextView) view.findViewById(R.id.page_name);
        headIv = (ImageView) view.findViewById(R.id.image_head);
        cailiaoName = (TextView) view.findViewById(R.id.cailiao_name);
        imageLoader.displayImage(OKhttpHelper.DOMAIN + SharedPreferencesUtils.getParam(getActivity(),"touXiangUrl",""),headIv);
        cailiaoName.setText((String) SharedPreferencesUtils.getParam(getActivity(), "code", ""));
        String a = (String) SharedPreferencesUtils.getParam(getActivity(), "login", "");
        LoginBean bean = new Gson().fromJson(a, LoginBean.class);
        if (!"".equals(SharedPreferencesUtils.getParam(getActivity(), "cailiaoHao", ""))) {
            cailiaoName.setText("彩聊号:" + (String) SharedPreferencesUtils.getParam(getActivity(), "cailiaoName", ""));
        } else {
            cailiaoName.setText("彩聊号:" + "未填写");
        }
        name.setText(null != bean ? "昵称:" + bean.getUserDto().getName() : "昵称:");
    }

    private void initEvent() {
        qianBao.setOnClickListener(this);
        kaBao.setOnClickListener(this);
        bangZhu.setOnClickListener(this);
        sheZhi.setOnClickListener(this);
        caiZhan.setOnClickListener(this);
        back.setOnClickListener(this);
        head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_lay_faxian_sao:
                intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
                break;
            case R.id.item_lay_bangzhuzhongxin:
                intent = new Intent(getActivity(), GroupCardActivity.class);
                startActivity(intent);
                break;
            case R.id.item_lay_mimashezhi:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.item_lay_shezhi:
                intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
                break;
            case R.id.item_lay_renzhengzhongxin:
                intent = new Intent(getActivity(), LotterySiteAuthenticationActivity.class);
                startActivity(intent);
                break;
            case R.id.quanju_dengchu:
                SharedPreferences sp = getActivity().getSharedPreferences("Database", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                SharedPreferencesUtils.setParam(getActivity(), "first", 0);
                dialog();
                break;
            case R.id.lay_head:
                intent = new Intent(getActivity(), PersonalInformationActivity.class);
                intent.putExtra("cailiaoHao", cailiaoName.getText().toString());
                startActivityForResult(intent, 111);
                break;
            default:
                break;
        }
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencesUtils.cleanSharedPreference(getActivity());
                dialog.dismiss();
                ((HomeActivity) getActivity()).changeFragment();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 111:
                imageLoader.displayImage(OKhttpHelper.DOMAIN + SharedPreferencesUtils.getParam(getActivity(), "touXiangUrl", ""), headIv);
                break;
            default:
                break;
        }
    }
}
