package com.byl.enjoylottery.activity;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.byl.enjoylottery.tools.SharedPreferencesUtils;
import com.byl.enjoylottery.view.WeiboDialogUtils;
import com.byl.enjoylottery.bean.PersonInformationBean;
import com.example.administrator.enjoylottery.R;
import com.byl.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class PersonalizedSignatureActivity extends BaseActivity implements TextWatcher {

    private TextView titleRight, tvCount;
    private EditText editText;
    private JSONObject object;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalized_signature);
        initView();
        initEvent();
    }

    private void initView(){
        titleRight = (TextView) findViewById(R.id.every_top_right);
        editText = (EditText) findViewById(R.id.nickname_edittext);
        tvCount = (TextView) findViewById(R.id.shengyu_count);
        editText.setText(getIntent().getStringExtra("signature"));
        tvCount.setText(30 - editText.getText().length() + "");
        ((TextView)findViewById(R.id.every_top_text)).setText("个性签名");
        titleRight.setText("保存");
        titleRight.setBackgroundResource(R.color.save);
    }

    private void initEvent(){
        titleRight.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.every_top_right:
                dialog = WeiboDialogUtils.createLoadingDialog(PersonalizedSignatureActivity.this, "修改中");
                object = new JSONObject();
                try {
                    object.put("signature", editText.getText().toString());
                    object.put("id", SharedPreferencesUtils.getParam(PersonalizedSignatureActivity.this, "id", ""));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new ModifySignature().execute();
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        titleRight.setBackgroundResource(R.drawable.save);
        tvCount.setText(30 - editText.getText().length() + "");
        if (editText.getText().length() >= 31){
            String a = editText.getText().toString();
            String b = a.substring(0,30);
            editText.setText(b);
        }
    }

    @Override
    public void afterTextChanged(Editable s){}

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    showToastShort("修改失败");
                    break;
                case 2:
                    showToastShort("修改成功");
                    break;
            }
        }
    };

    class ModifySignature extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyUserInformation(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                return;
            }
            Gson gson = new Gson();
            PersonInformationBean bean = gson.fromJson(s, PersonInformationBean.class);
            if (!bean.getFlag()) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                WeiboDialogUtils.closeDialog(dialog);
            } else {
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
                SharedPreferencesUtils.setParam(PersonalizedSignatureActivity.this, "signature", bean.getUserDto().getSignature());
                WeiboDialogUtils.closeDialog(dialog);
                finish();
            }
        }
    }
}
