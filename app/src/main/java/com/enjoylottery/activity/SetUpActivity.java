package com.enjoylottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class SetUpActivity extends BaseActivity {

    private RelativeLayout modifyPwd, clearCache;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();
        initEvent();
    }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("钱包");
        modifyPwd = (RelativeLayout) findViewById(R.id.rel_modify_password);
        clearCache = (RelativeLayout) findViewById(R.id.rel_clear_cache);
    }

    private void initEvent(){
        modifyPwd.setOnClickListener(this);
        clearCache.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.rel_modify_password:
                intent = new Intent(this,ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_clear_cache:

                break;
            default:
                break;
        }
    }
}
