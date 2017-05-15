package com.example.administrator.enjoylottery.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.view.trendtype_11s5.ViceView;

/**
 * Created by lz on 2017/5/9.
 */

public class ViewActivity extends BaseActivity {
    ViceView cView;
    Handler handler = new Handler();
    private ScrollView scrollView;
    private boolean isBottom=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }



    @Override
    public void widgetClick(View v) {

    }


}
