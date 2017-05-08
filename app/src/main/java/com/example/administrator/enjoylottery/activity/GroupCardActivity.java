package com.example.administrator.enjoylottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class GroupCardActivity extends BaseActivity{
    private RelativeLayout groupCard,expert,integral;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupcard);
        initView();
        initEvent();
    }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("建群卡");
        groupCard = (RelativeLayout) findViewById(R.id.rel_jianqun);
        expert = (RelativeLayout) findViewById(R.id.rel_zhuanjia);
        integral = (RelativeLayout) findViewById(R.id.rel_jifen);
    }

    private void initEvent(){
        groupCard.setOnClickListener(this);
        expert.setOnClickListener(this);
        integral.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.rel_jianqun:
                intent = new Intent(this,GroupCardBuyPageActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_zhuanjia:
                Toast.makeText(this,"未上线",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rel_jifen:
                Toast.makeText(this,"未上线",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
