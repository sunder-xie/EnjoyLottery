package com.enjoylottery.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.enjoylottery.view.TouchImageView;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.presenters.OKhttpHelper;
import com.enjoylottery.tools.SharedPreferencesUtils;

import io.rong.imageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class HeadActivity extends BaseActivity {

    private TouchImageView head;
    private Uri uritempFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        initView();
    }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("头像");
        head = (TouchImageView) findViewById(R.id.image_head);
        ImageLoader.getInstance().displayImage(OKhttpHelper.DOMAIN + SharedPreferencesUtils.getParam(this,"touXiangUrl",""),head);
    }

    @Override
    public void widgetClick(View v) {

    }
}
