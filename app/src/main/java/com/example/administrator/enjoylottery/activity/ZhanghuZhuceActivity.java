package com.example.administrator.enjoylottery.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class ZhanghuZhuceActivity extends BaseActivity implements TextWatcher {
    private EditText phoneEdit, yanzhengEdit, caiDianEdit;
    private TextView shanPhone, shanCaidian, shenHe;
    private int a = 0;
    private Boolean go = false;
    private Boolean pwdTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghuzhuce);
        initView();
        initEvent();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("彩站注册");
        ((TextView) findViewById(R.id.tianxie_ziliao)).setTextColor(getResources().getColor(R.color.zitilv));
        ((ImageView) findViewById(R.id.jiantou_one)).setImageResource(R.drawable.yuanjiantoulv);
        ((TextView) findViewById(R.id.zhengjian_renzheng)).setTextColor(getResources().getColor(R.color.zitilv));
        ((ImageView) findViewById(R.id.jiantou_two)).setImageResource(R.drawable.yuanjiantoulv);
        ((TextView) findViewById(R.id.zhanghu_zhuce)).setTextColor(getResources().getColor(R.color.zitilv));
        phoneEdit = (EditText) findViewById(R.id.denglu_shoujihao_edit_zhuce);
        yanzhengEdit = (EditText) findViewById(R.id.denglu_yanzhengma_edit_zhuce);
        caiDianEdit = (EditText) findViewById(R.id.denglu_nicheng_edit_zhuce);
        shanPhone = (TextView) findViewById(R.id.denglu_quxiao_shoujihao);
        shanCaidian = (TextView) findViewById(R.id.denglu_quxiao_nicheng);
        shenHe = (TextView) findViewById(R.id.ziliao_xiayibu);
    }

    private void initEvent() {
        shanPhone.setOnClickListener(this);
        shanCaidian.setOnClickListener(this);
        phoneEdit.addTextChangedListener(this);
        yanzhengEdit.addTextChangedListener(this);
        caiDianEdit.addTextChangedListener(this);
        shenHe.setOnClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ziliao_xiayibu:
                 if (go == false){
                     showToastShort("必填项不能为空");
                     return;
                 }

                try {
                    ZhengjianRenzhengActivity activity = ZhengjianRenzhengActivity.getContext();
                    activity.getA(phoneEdit.getText().toString().trim()
                            ,yanzhengEdit.getText().toString().trim()
                            ,caiDianEdit.getText().toString().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.denglu_quxiao_shoujihao:
                phoneEdit.setText(null);
                break;
            case R.id.denglu_quxiao_mima:

                break;
            case R.id.denglu_quxiao_nicheng:
                caiDianEdit.setText(null);
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        nextGo();
        if (phoneEdit.getText().toString().trim().equals("")) {
            shanPhone.setVisibility(View.GONE);
        } else {
            shanPhone.setVisibility(View.VISIBLE);
        }
        if (caiDianEdit.getText().toString().trim().equals("")) {
            shanCaidian.setVisibility(View.GONE);
        } else {
            shanCaidian.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void nextGo() {
        if (!phoneEdit.getText().toString().trim().equals("") && !yanzhengEdit.getText().toString().trim().equals("") && !caiDianEdit.getText().toString().trim().equals("")) {
            shenHe.setBackgroundResource(R.drawable.dianjizhuce);
            go = true;
        } else {
            shenHe.setBackgroundResource(R.color.edithui);
            go = false;
        }
    }
}
