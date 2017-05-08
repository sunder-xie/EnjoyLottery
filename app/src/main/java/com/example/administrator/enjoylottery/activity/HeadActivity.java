package com.example.administrator.enjoylottery.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.view.TouchImageView;

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
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        head.setImageURI(uritempFile);
    }

    @Override
    public void widgetClick(View v) {

    }
}
