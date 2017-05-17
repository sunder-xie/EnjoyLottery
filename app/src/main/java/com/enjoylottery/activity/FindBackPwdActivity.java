package com.enjoylottery.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class FindBackPwdActivity extends BaseActivity implements TextWatcher {
    private EditText phoneEdit, yanzhengEdit, pwdEdit;
    private TextView shanPhone, shanPwd, shanCaidian, shenHe;
    private ImageView kanPwd;
    private int a = 0;
    private Boolean go = false;
    private Boolean pwdTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_back_pwd);
        initView();
        initEvent();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("找回密码");
        phoneEdit = (EditText) findViewById(R.id.denglu_shoujihao_edit_zhuce);
        yanzhengEdit = (EditText) findViewById(R.id.denglu_yanzhengma_edit_zhuce);
        pwdEdit = (EditText) findViewById(R.id.denglu_mima_edit_zhuce);
        shanPhone = (TextView) findViewById(R.id.denglu_quxiao_shoujihao);
        shanPwd = (TextView) findViewById(R.id.denglu_quxiao_mima);
        kanPwd = (ImageView) findViewById(R.id.denglu_kanmima_caimin);
        shenHe = (TextView) findViewById(R.id.ziliao_xiayibu);
    }

    private void initEvent() {
        shanPhone.setOnClickListener(this);
        shanPwd.setOnClickListener(this);
        phoneEdit.addTextChangedListener(this);
        yanzhengEdit.addTextChangedListener(this);
        pwdEdit.addTextChangedListener(this);
        kanPwd.setOnClickListener(this);
        shenHe.setOnClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ziliao_xiayibu:
                if (!pwdTrue){
                    Toast.makeText(FindBackPwdActivity.this,"密码不能少于6位或多于15位",Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(FindBackPwdActivity.this,"确定",Toast.LENGTH_SHORT).show();
                break;
            case R.id.denglu_quxiao_shoujihao:
                phoneEdit.setText(null);
                break;
            case R.id.denglu_quxiao_mima:
                pwdEdit.setText(null);
                break;
            case R.id.denglu_kanmima_caimin:
                if (a % 2 == 0) {
                    pwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    kanPwd.setImageResource(R.drawable.kan);
                    a += 1;
                } else {
                    pwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    kanPwd.setImageResource(R.drawable.weikan);
                    a += 1;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        nextGo();
        if (phoneEdit.getText().toString().trim().equals("")) {
            shanPhone.setVisibility(View.GONE);
        } else {
            shanPhone.setVisibility(View.VISIBLE);
        }
        if (pwdEdit.getText().toString().trim().equals("")) {
            shanPwd.setVisibility(View.GONE);
        } else {
            shanPwd.setVisibility(View.VISIBLE);
        }
        if (!pwdEdit.getText().toString().trim().equals("")) {
            if (start - before + 1< 6) {
                pwdTrue = false;
            } else if (start - before + 1 > 15){
                pwdTrue = false;
            }else {
                pwdTrue = true;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}

    private void nextGo() {
        if (!phoneEdit.getText().toString().trim().equals("") && !yanzhengEdit.getText().toString().trim().equals("") && !pwdEdit.getText().toString().trim().equals("")) {
            shenHe.setBackgroundResource(R.drawable.dianjizhuce);
            go = true;
        } else {
            shenHe.setBackgroundResource(R.color.edithui);
            go = false;
        }
    }
}
