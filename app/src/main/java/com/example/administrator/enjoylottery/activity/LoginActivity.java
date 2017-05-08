package com.example.administrator.enjoylottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.administrator.enjoylottery.bean.LoginBean;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.tools.SharedPreferencesUtils;
import com.example.administrator.enjoylottery.view.WeiboDialogUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class LoginActivity extends BaseActivity implements TextWatcher {
    private TextView shanPhone, shanPwd, caiminReg, findBackPwd, login;
    private EditText editPhone, editPwd;
    private ImageView kanPwd;
    private Intent intent;
    private int a = 0;
    private Dialog mWeiboDialog;
    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    showToastShort("登陆成功");
                    break;
                case 2:
                    showToastShort("密码错误");
                    break;
                case 3:
                    showToastShort("登录失败");
                    break;
            }
        }
    };

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("登 录");
        shanPhone = (TextView) findViewById(R.id.denglu_quxiao_shoujihao);
        editPhone = (EditText) findViewById(R.id.denglu_shoujihao_edit);
        shanPwd = (TextView) findViewById(R.id.denglu_quxiao_pwd);
        editPwd = (EditText) findViewById(R.id.denglu_pwd_edit);
        kanPwd = (ImageView) findViewById(R.id.denglu_kanmima);
        caiminReg = (TextView) findViewById(R.id.denglu_caiminzhuce);
        findBackPwd = (TextView) findViewById(R.id.find_back_pwd);
        login = (TextView) findViewById(R.id.login);
    }

    private void initEvent() {
        shanPhone.setOnClickListener(this);
        editPhone.addTextChangedListener(this);
        editPwd.addTextChangedListener(this);
        kanPwd.setOnClickListener(this);
        shanPwd.setOnClickListener(this);
        caiminReg.setOnClickListener(this);
        findBackPwd.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.denglu_quxiao_shoujihao:
                editPhone.setText(null);
                break;
            case R.id.denglu_quxiao_pwd:
                editPwd.setText(null);
                break;
            case R.id.denglu_kanmima:
                if (a % 2 == 0) {
                    editPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    kanPwd.setImageResource(R.drawable.kan);
                    a += 1;
                } else {
                    editPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    kanPwd.setImageResource(R.drawable.weikan);
                    a += 1;
                }
                break;
            case R.id.denglu_caiminzhuce:
                intent = new Intent(LoginActivity.this, CaiminRegistrationActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.find_back_pwd:
                intent = new Intent(LoginActivity.this, FindBackPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                if ("".equals(editPhone.getText().toString().trim()) || "".equals(editPwd.getText().toString().trim())) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isMobileNO(editPhone.getText().toString().trim())) {
                    showToastShort("电话号码不正确");
                    return;
                }
                object = new JSONObject();
                try {
                    object.put("telephone", editPhone.getText().toString().trim());
                    object.put("password", editPwd.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(LoginActivity.this, "登录中...");
                new Login().execute();
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
        if (editPhone.getText().toString().trim().equals("")) {
            shanPhone.setVisibility(View.GONE);
        } else {
            shanPhone.setVisibility(View.VISIBLE);
        }
        if (editPwd.getText().toString().trim().equals("")) {
            shanPwd.setVisibility(View.GONE);
        } else {
            shanPwd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            object = new JSONObject();
            String phone = data.getStringExtra("phone");
            String pwd = data.getStringExtra("pwd");
            editPhone.setText(phone);
            editPwd.setText(pwd);
            try {
                object.put("telephone", phone);
                object.put("password", pwd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mWeiboDialog = WeiboDialogUtils.createLoadingDialog(LoginActivity.this, "登录中...");
            new Login().execute();
        }
    }

    class Login extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().login(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                Message msg = new Message();
                msg.what = 3;
                handler.sendMessage(msg);
                WeiboDialogUtils.closeDialog(mWeiboDialog);
                return;
            }
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("flag")) {
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            WeiboDialogUtils.closeDialog(mWeiboDialog);
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
            setResult(RESULT_OK);
            Gson gson = new Gson();
            LoginBean bean = gson.fromJson(s, LoginBean.class);
            String id = bean.getUserDto().getId();
            String sex = (String) bean.getUserDto().getSex();
            sex = null != sex ? sex : "";
            String signature = (String) bean.getUserDto().getSignature();
            signature = null != signature ? signature : "";
            String touXiangUrl = null != bean.getUserDto().getTouXiangUrl() ? bean.getUserDto().getTouXiangUrl() : "";
            if (bean.getUserDto().getCode() != null) {
                String code = (String) bean.getUserDto().getCode();
                SharedPreferencesUtils.setParam(LoginActivity.this, "code", code);
            }
            SharedPreferencesUtils.setParam(LoginActivity.this, "id", id);
            SharedPreferencesUtils.setParam(LoginActivity.this, "login", s);
            SharedPreferencesUtils.setParam(LoginActivity.this, "touXiangUrl", touXiangUrl);
            SharedPreferencesUtils.setParam(LoginActivity.this, "firstLogin", 1);
            SharedPreferencesUtils.setParam(LoginActivity.this, "sex", sex);
            SharedPreferencesUtils.setParam(LoginActivity.this, "signature", signature);
            finish();
        }
    }

    private boolean isMobileNO(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}
