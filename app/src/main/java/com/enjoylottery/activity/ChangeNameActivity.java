package com.enjoylottery.activity;

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

import com.enjoylottery.bean.PersonInformationBean;
import com.enjoylottery.view.WeiboDialogUtils;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.presenters.OKhttpHelper;
import com.enjoylottery.tools.SharedPreferencesUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class ChangeNameActivity extends BaseActivity implements TextWatcher{

    private TextView titleRight;
    private EditText editText;
    private JSONObject object = new JSONObject();
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        initView();
        initEvent();
    }

    private void initView(){
        titleRight = (TextView) findViewById(R.id.every_top_right);
        editText = (EditText) findViewById(R.id.nickname_edittext);
        editText.setText(getIntent().getStringExtra("nickName"));
        ((TextView)findViewById(R.id.every_top_text)).setText("更改彩聊名");
        titleRight.setText("保存");
        titleRight.setBackgroundResource(R.color.save);
    }

    private void initEvent(){
        editText.addTextChangedListener(this);
        titleRight.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.every_top_right:
                if (!editText.getText().toString().trim().equals("")){
                    try {
                        object.put("cailiaoName",editText.getText().toString());
                        object.put("id", SharedPreferencesUtils.getParam(this,"id",""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    showToastShort("彩聊名不能为空");
                    return;
                }
                dialog = WeiboDialogUtils.createLoadingDialog(this,"修改中");
                new ModifyNickName().execute();
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
    }

    @Override
    public void afterTextChanged(Editable s) {}

    class ModifyNickName extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyUserInformation(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null){
                return;
            }
            Gson gson = new Gson();
            PersonInformationBean bean = gson.fromJson(s,PersonInformationBean.class);
            if (!bean.getFlag()){
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                WeiboDialogUtils.closeDialog(dialog);
            }else {
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
                SharedPreferencesUtils.setParam(ChangeNameActivity.this,"cailiaoName",bean.getUserDto().getCailiaoName());
                WeiboDialogUtils.closeDialog(dialog);
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showToastShort("修改失败");
                    break;
                case 2:
                    showToastShort("修改成功");
                    break;
            }
        }
    };
}
