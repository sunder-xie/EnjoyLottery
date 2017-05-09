package com.example.administrator.enjoylottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class FuntionConfigurationFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        view = inflater.inflate(R.layout.fragment_funtion_configuration,null,false);
        return view;
    }
}
