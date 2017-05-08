package com.example.administrator.enjoylottery.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class ChartFragment extends BaseFragment {
    private View view;
    private ListView chartList;
    private TextView add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chart_fragment, null, false);
        initView();
        return view;
    }

    public void initView() {
        ((TextView) view.findViewById(R.id.every_top_text)).setText("聊 天");
        chartList = (ListView) view.findViewById(R.id.group_chart_list);
        add = (TextView) view.findViewById(R.id.every_top_right);
        add.setText("+");
    }
}
