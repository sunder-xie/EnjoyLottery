package com.byl.enjoylottery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class MyNewsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news);
        initView();
    }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("消息中心");
    }

    @Override
    public void widgetClick(View v) {

    }
}
