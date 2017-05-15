package com.example.administrator.enjoylottery.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.tools.SharedPreferencesUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;


/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class ConversationActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        initView();
    }

    private void initView(){
        ((ImageView) findViewById(R.id.every_top_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.every_top_text)).setText(getIntent().getData().getQueryParameter("title"));
        RongIM.getInstance().setCurrentUserInfo(new UserInfo((String) SharedPreferencesUtils.getParam(this,"token",""),
                (String)SharedPreferencesUtils.getParam(this,"nick",""),
                Uri.parse((String)SharedPreferencesUtils.getParam(this,
                        OKhttpHelper.getInstance().DOMAIN + "touXiangUrl",""))));
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        //RongIM.getInstance().refreshUserInfoCache();
    }
}
