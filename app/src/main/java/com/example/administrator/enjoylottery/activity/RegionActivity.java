package com.example.administrator.enjoylottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class RegionActivity extends BaseActivity {

    private TextView titleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        initView();
        initEvent();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("我的地址");
        titleRight = (TextView) findViewById(R.id.every_top_right);
        titleRight.setText("+");
        titleRight.setTextSize(16);
    }

    private void initEvent() {
        titleRight.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.every_top_right:
                Intent intent = new Intent(this, NewAddRegionActivity.class);
                startActivity(intent);
                break;
        }
    }
}
