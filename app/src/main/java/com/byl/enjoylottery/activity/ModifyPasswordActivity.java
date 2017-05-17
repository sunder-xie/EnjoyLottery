package com.byl.enjoylottery.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.byl.enjoylottery.bean.LoginBean;
import com.byl.enjoylottery.tools.SharedPreferencesUtils;
import com.example.administrator.enjoylottery.R;
import com.byl.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ModifyPasswordActivity extends BaseActivity implements TextWatcher {

    private EditText phone, oldPwd, newPwd, repeatPwd;
    private TextView sure;
    private JSONObject object;
    private JSONObject objectNew;
    private LoginBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        initView();
        initEvent();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(ModifyPasswordActivity.this,"原密码输入不正确",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(ModifyPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("修改密码");
        phone = (EditText) findViewById(R.id.denglu_shoujihao_edit_zhuce_modify);
        oldPwd = (EditText) findViewById(R.id.denglu_yanzhengma_edit_zhuce_modify);
        newPwd = (EditText) findViewById(R.id.denglu_mima_edit_zhuce_modify);
        repeatPwd = (EditText) findViewById(R.id.denglu_nicheng_edit_zhuce_modify);
        sure = (TextView) findViewById(R.id.sure_modify_password);
    }

    private void initEvent() {
        phone.addTextChangedListener(this);
        oldPwd.addTextChangedListener(this);
        newPwd.addTextChangedListener(this);
        repeatPwd.addTextChangedListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.sure_modify_password:
                if ((newPwd.getText().length()<6||newPwd.getText().length()>15)||(repeatPwd.getText().length()<6||repeatPwd.getText().length()>15)) {
                    Toast.makeText(ModifyPasswordActivity.this, "密码不能小于6位大于15位", Toast.LENGTH_SHORT).show();
                    return;
                }
                    if (newPwd.getText().toString().contains(" ")) {
                    Toast.makeText(ModifyPasswordActivity.this, "新密码不能有空格", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((!"".equals(newPwd.getText().toString().trim())) && (!"".equals(repeatPwd.getText().toString().trim()))) {
                    if (!newPwd.getText().toString().equals(repeatPwd.getText().toString())) {
                        Toast.makeText(ModifyPasswordActivity.this, "新密码和重复密码不同", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if ((!"".equals(phone.getText().toString().trim()))&&(!"".equals(oldPwd.getText().toString().trim()))&&(!"".equals(newPwd.getText().toString().trim()))&&(!"".equals(repeatPwd.getText().toString().trim()))){
                    object = new JSONObject();
                    String login = (String) SharedPreferencesUtils.getParam(ModifyPasswordActivity.this,"login","");
                    Gson gson = new Gson();
                    bean = gson.fromJson(login, LoginBean.class);
                    try {
                        object.put("id",bean.getUserDto().getId());
                        object.put("password",oldPwd.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new ModifyOldPwd().execute();
                }
                break;
            default:
                break;
        }
    }

    class ModifyOldPwd extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyOldPwd(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null){
                return;
            }
            try {
                JSONObject object = new JSONObject(s);
                objectNew = new JSONObject();
                if (object.getBoolean("flag")){
                    objectNew.put("password",newPwd.getText().toString());
                    objectNew.put("id",bean.getUserDto().getId());
                    new ModifyNewPed().execute();
                }else {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class ModifyNewPed extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyNewPwd(objectNew);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null){
                return;
            }
            try {
                JSONObject object = new JSONObject(s);
                if (object.getBoolean("flag")){
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
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
        if ((!"".equals(phone.getText().toString().trim()))&&(!"".equals(oldPwd.getText().toString().trim()))&&(!"".equals(newPwd.getText().toString().trim()))&&(!"".equals(repeatPwd.getText().toString().trim()))){
            sure.setOnClickListener(this);
            sure.setTextColor(getResources().getColor(R.color.background_white));
            sure.setBackgroundResource(R.drawable.dianjizhuce);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
