package com.byl.enjoylottery.activity;

import android.app.Dialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.byl.enjoylottery.tools.SharedPreferencesUtils;
import com.byl.enjoylottery.view.WeiboDialogUtils;
import com.byl.enjoylottery.bean.PersonInformationBean;
import com.example.administrator.enjoylottery.R;
import com.byl.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.rong.imageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class LotteryChatNumberActivity extends BaseActivity implements TextWatcher {

    private TextView titleRight, prompt, chatNumber, nickName;
    private ImageView head;
    private Uri uritempFile;
    private EditText editText;
    private boolean go = false;
    private Dialog dialog;
    private JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_chat_number);
        initView();
        initEvent();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("设置彩聊号");
        titleRight = (TextView) findViewById(R.id.every_top_right);
        titleRight.setText("保存");
        titleRight.setBackgroundResource(R.color.save);
        head = (ImageView) findViewById(R.id.chat_number_image);
        ImageLoader.getInstance().displayImage(OKhttpHelper.DOMAIN + SharedPreferencesUtils.getParam(this, "touXiangUrl", ""), head);
        chatNumber = (TextView) findViewById(R.id.chat_number_tv);
        editText = (EditText) findViewById(R.id.lottery_edittext);
        prompt = (TextView) findViewById(R.id.tishi_text);
        nickName = (TextView) findViewById(R.id.nickname_text);
    }

    private void initEvent() {
        titleRight.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.every_top_right:
                if (go) {
                    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                    for (int a = 0; a <= editText.getText().length(); a++) {
                        String b = editText.getText().toString().substring(a, a + 1);
                        Matcher m = p.matcher(b);
                        if (m.matches()) {
                            showToastShort("彩聊号不能包含汉字");
                            return;
                        }
                    }

                    dialog = WeiboDialogUtils.createLoadingDialog(LotteryChatNumberActivity.this, "修改中");
                    object = new JSONObject();
                    try {
                        object.put("id", SharedPreferencesUtils.getParam(LotteryChatNumberActivity.this, "id", ""));
//                        object.put("cailiaoHao",)
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    new ModifySex().execute();
                }

                break;
            default:
                break;
        }
    }

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

    class ModifySex extends AsyncTask<Void, Void, String> {

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
                SharedPreferencesUtils.setParam(LotteryChatNumberActivity.this, "caiLiaoHao", bean.getUserDto().getCailiaoName());
                WeiboDialogUtils.closeDialog(dialog);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 15 || s.length() < 6) {
            prompt.setText("彩聊号仅支持大于6位小于15位");
            prompt.setTextColor(getResources().getColor(R.color.red));
            titleRight.setBackgroundResource(R.color.save);
            chatNumber.setText("彩聊号：" + editText.getText().toString().trim());
            go = false;
        } else {
            titleRight.setBackgroundResource(R.drawable.save);
            prompt.setTextColor(getResources().getColor(R.color.chatnumber));
            chatNumber.setText("彩聊号：" + editText.getText().toString().trim());
            prompt.setText("彩聊号是账号的唯一凭证，只能设置一次");
            go = true;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
