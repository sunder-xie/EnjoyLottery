package com.example.administrator.enjoylottery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.activity.LoginActivity;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private RelativeLayout headRel;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.me_fragment,null,false);
        initView();
        initEvent();
        return view;
    }

    private void initView(){
        ((TextView)view.findViewById(R.id.every_top_text)).setText("我 的");
        headRel = (RelativeLayout) view.findViewById(R.id.wode_diancidenglu);
    }

    private void initEvent(){
        headRel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wode_diancidenglu:
                intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent,1);
                break;
        }
    }
}
