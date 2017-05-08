package com.example.administrator.enjoylottery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.view.AmountView;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class GroupCardBuyPageActivity extends BaseActivity {

    private AmountView amountView;
    private TextView ticai,fucai,buy;
    private int amout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupcard_buypage);
        initView();
        initEvent();
    }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("建群卡");
        amountView = (AmountView) findViewById(R.id.amount_view);
        ticai = (TextView) findViewById(R.id.kazhong_ticai);
        fucai = (TextView) findViewById(R.id.kazhong_fucai);
        buy = (TextView) findViewById(R.id.buy_group_card);
    }

    private void initEvent(){
        ticai.setOnClickListener(this);
        fucai.setOnClickListener(this);
        buy.setOnClickListener(this);
        amountView.setGoods_storage(100);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                GroupCardBuyPageActivity.this.amout = amount;
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.kazhong_ticai:
                ticai.setTextColor(R.color.background_white);
                fucai.setTextColor(R.color.title_color);
                ticai.setBackgroundResource(R.drawable.lvdiyuanjiao);
                fucai.setBackgroundResource(R.drawable.baidiyouyuanjiao);
                break;
            case R.id.kazhong_fucai:
                ticai.setTextColor(R.color.title_color);
                fucai.setTextColor(R.color.background_white);
                ticai.setBackgroundResource(R.drawable.baidizuoyuanjiao);
                fucai.setBackgroundResource(R.drawable.lvdiyouyuanjiao);
                break;
            default:
                break;
        }
    }
}
