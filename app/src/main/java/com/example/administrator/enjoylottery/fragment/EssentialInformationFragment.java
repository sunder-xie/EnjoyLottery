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

public class EssentialInformationFragment extends BaseFragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_essential_information,null,false);
        return view;
    }
}
