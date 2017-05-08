package com.example.administrator.enjoylottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.view.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class CaiminRegistrationActivity extends BaseActivity implements TextWatcher {
    private EditText phoneEdit, yanzhengEdit, pwdEdit, nichengEdit, verificationEdit;
    private TextView shanPhone, shanPwd, shanNicheng, register, verification;
    private ImageView kanPwd;
    private int a = 0;
    private String phNumber;
    private JSONObject object;
    private Dialog mWeiboDialog;
    final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caimin);
        initView();
        initEvent();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(CaiminRegistrationActivity.this, "账号已注册", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("彩民注册");
        phoneEdit = (EditText) findViewById(R.id.denglu_shoujihao_edit_zhuce);
        yanzhengEdit = (EditText) findViewById(R.id.denglu_yanzhengma_edit_zhuce);
        pwdEdit = (EditText) findViewById(R.id.denglu_mima_edit_zhuce);
        nichengEdit = (EditText) findViewById(R.id.denglu_nicheng_edit_zhuce);
        shanPhone = (TextView) findViewById(R.id.denglu_quxiao_shoujihao);
        shanPwd = (TextView) findViewById(R.id.denglu_quxiao_mima);
        shanNicheng = (TextView) findViewById(R.id.denglu_quxiao_nicheng);
        kanPwd = (ImageView) findViewById(R.id.denglu_kanmima_caimin);
        register = (TextView) findViewById(R.id.immediately_register);
        verification = (TextView) findViewById(R.id.get_verification_code);
        verificationEdit = (EditText) findViewById(R.id.denglu_zhandianhao);
    }

    private void initEvent() {
        shanPhone.setOnClickListener(this);
        shanPwd.setOnClickListener(this);
        shanNicheng.setOnClickListener(this);
        phoneEdit.addTextChangedListener(this);
        yanzhengEdit.addTextChangedListener(this);
        pwdEdit.addTextChangedListener(this);
        nichengEdit.addTextChangedListener(this);
        kanPwd.setOnClickListener(this);
        register.setOnClickListener(this);
        verification.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.denglu_quxiao_shoujihao:
                phoneEdit.setText(null);
                break;
            case R.id.denglu_quxiao_mima:
                pwdEdit.setText(null);
                break;
            case R.id.denglu_quxiao_nicheng:
                nichengEdit.setText(null);
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
            case R.id.immediately_register:
                if (nextGo() == 0) {
                    return;
                }

                if (!isMobileNO(phoneEdit.getText().toString().trim())){
                    showToastShort("电话号码不正确");
                    return;
                }

                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(CaiminRegistrationActivity.this, "注册中...");
                try {
                    object = new JSONObject();
                    object.put("name", nichengEdit.getText().toString().trim());
                    object.put("telephone", phoneEdit.getText().toString().trim());
                    object.put("password", pwdEdit.getText().toString().trim());
                    object.put("yanzhengma", yanzhengEdit.getText().toString().trim());
                    if (verificationEdit.getText().length() != 0) {
                        object.put("invideCode", verificationEdit.getText().toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Register().execute();
                break;
            case R.id.get_verification_code:
                myCountDownTimer.start();
                new GetVerication().execute();
                break;
            default:
                break;
        }
    }

    private boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches( telRegex );
    }

    class GetVerication extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getVerificationCode(phNumber);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                return;
            }
        }
    }

    class Register extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getRegister(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            WeiboDialogUtils.closeDialog(mWeiboDialog);
            if (s == null) {
                return;
            }
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("status")) {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("phone", phoneEdit.getText().toString().trim());
                    intent.putExtra("pwd", pwdEdit.getText().toString().trim());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (phoneEdit.getText().length() == 11) {
            verification.setTextColor(Color.BLACK);
            verification.setOnClickListener(this);
            phNumber = phoneEdit.getText().toString().trim();
        }
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
        if (nichengEdit.getText().toString().trim().equals("")) {
            shanNicheng.setVisibility(View.GONE);
        } else {
            shanNicheng.setVisibility(View.VISIBLE);
        }
        if (!pwdEdit.getText().toString().trim().equals("")) {
            if (count >= 6 && count <= 15) {
                Toast.makeText(CaiminRegistrationActivity.this, "密码要求在6到15位", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            verification.setClickable(false);
            verification.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            verification.setText("重新获取");
            //设置可点击
            verification.setClickable(true);
        }
    }

    private int nextGo() {
        if ("".equals(yanzhengEdit.getText().toString().trim())||"".equals(phoneEdit.getText().toString().trim()) || "".equals(yanzhengEdit.getText().toString().trim()) || "".equals(pwdEdit.getText().toString().trim()) || "".equals(nichengEdit.getText().toString().trim())) {
            Toast.makeText(CaiminRegistrationActivity.this, "除邀请码均为必填项", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (pwdEdit.getText().length() < 6 || pwdEdit.getText().length() > 15) {
            Toast.makeText(CaiminRegistrationActivity.this, "密码长度在6到15位", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}
