package com.example.administrator.enjoylottery.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.adapter.ChangyongXiaAdapter;
import com.example.administrator.enjoylottery.adapter.ChangyongZoushiAdapter;
import com.example.administrator.enjoylottery.bean.ElevenFivePictureBean;
import com.example.administrator.enjoylottery.listener.CheckMuban;
import com.example.administrator.enjoylottery.listener.SimpleItemTouchHelperCallback;
import com.example.administrator.enjoylottery.model.Number;
import com.example.administrator.enjoylottery.tools.ExampleUtil;
import com.example.administrator.enjoylottery.view.MyRecyclerview;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ZoushituHomeActivity extends BaseActivity {
    private static final String TAG = "JPush";
    private TextView bianJi;
    private List<ElevenFivePictureBean> mUserList = new ArrayList<>();
    private MyRecyclerview changYongRecycler;
    private RecyclerView moreRecycler;
    private ChangyongZoushiAdapter changyongAdapter;
    private ChangyongXiaAdapter changyongXiaAdapter;
    private List<ElevenFivePictureBean> listShang = new ArrayList<>();
    private List<ElevenFivePictureBean> listXia = new ArrayList<>();
    private Boolean checkMuban = false;
    private LinearLayoutManager linearLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private static final int MSG_SET_TAGS = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoushitu_home);
        initView();
        initData();
        initEvent();
        setTag("210000,1");
    }

    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "设置别名和标签成功！";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "设置超时，60s后重试！";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i(TAG, "没有连接网络");
                    }
                    break;

                default:
                    logs = "失败代码 = " + code;
                    Log.e(TAG, logs);
            }
            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case MSG_SET_TAGS:
                    Log.d(TAG, "在handler里面设置tags");
                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    Log.i(TAG, "handler没有内容 - " + msg.what);
            }
        }
    };

    /**
     * 设置tags
     */
    private void setTag(String tag) {

        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(ZoushituHomeActivity.this, "tag不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // ","隔开的多个 转换成 Set
        Set<String> tagSet = new LinkedHashSet<String>();
        String[] sArray = tag.split(",");
        for (String sTagItme : sArray) {
            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
                Toast.makeText(ZoushituHomeActivity.this, "格式不对", Toast.LENGTH_SHORT).show();
                return;
            }
            tagSet.add(sTagItme);
        }
        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));
    }

    private void setTags(String[] tags){
        Set<String> tagSet = new LinkedHashSet<String>();
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("走势图");
        bianJi = (TextView) findViewById(R.id.every_top_right);
        bianJi.setText("编辑");
        changYongRecycler = (MyRecyclerview) findViewById(R.id.changyongzoushitu);
        changYongRecycler.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new GridLayoutManager(this, 4);
        changYongRecycler.setLayoutManager(linearLayoutManager);

        moreRecycler = (RecyclerView) findViewById(R.id.gengduozoushitu);
        moreRecycler.setItemAnimator(new DefaultItemAnimator());
        moreRecycler.setLayoutManager(new GridLayoutManager(this, 4));

        changYongRecycler.setHasFixedSize(true);
        moreRecycler.setHasFixedSize(true);
    }

    private void initData() {
        ElevenFivePictureBean bean7 = new ElevenFivePictureBean();
        bean7.setName("模板零号");
        bean7.setCode(0);
        bean7.setPicture(R.drawable.eleven_five_zero);

        ElevenFivePictureBean bean1 = new ElevenFivePictureBean();
        bean1.setCode(1);
        bean1.setPicture(R.drawable.eleven_five_one);
        bean1.setName("模板一号");
        mUserList.add(bean1);

        ElevenFivePictureBean bean2 = new ElevenFivePictureBean();
        bean2.setCode(2);
        bean2.setPicture(R.drawable.eleven_five_two);
        bean2.setName("模板二号");
        mUserList.add(bean2);

        ElevenFivePictureBean bean3 = new ElevenFivePictureBean();
        bean3.setCode(3);
        bean3.setPicture(R.drawable.eleven_five_three);
        bean3.setName("模板三号");
        mUserList.add(bean3);

        ElevenFivePictureBean bean4 = new ElevenFivePictureBean();
        bean4.setName("模板四号");
        bean4.setCode(4);
        bean4.setPicture(R.drawable.eleven_five_four);
        mUserList.add(bean4);

        ElevenFivePictureBean bean5 = new ElevenFivePictureBean();
        bean5.setName("模板五号");
        bean5.setCode(5);
        bean5.setPicture(R.drawable.eleven_five_five);
        mUserList.add(bean5);

        ElevenFivePictureBean bean6 = new ElevenFivePictureBean();
        bean6.setName("模板六号");
        bean6.setCode(6);
        bean6.setPicture(R.drawable.eleven_five_six);
        mUserList.add(bean6);

        for (int a = 0; a < mUserList.size(); a++) {
            if (a < Number.CHANGYONG) {
                listShang.add(mUserList.get(a));
            } else {
                listXia.add(mUserList.get(a));
            }
        }
    }

    private void initEvent() {
        changyongAdapter = new ChangyongZoushiAdapter(this, listShang, checkMuban);
        changYongRecycler.setAdapter(changyongAdapter);
        changyongXiaAdapter = new ChangyongXiaAdapter(this, listXia, checkMuban);
        moreRecycler.setAdapter(changyongXiaAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(changyongAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(changYongRecycler);

        ItemTouchHelper.Callback callbackk = new SimpleItemTouchHelperCallback(changyongXiaAdapter);
        mItemTouchHelper = new ItemTouchHelper(callbackk);
        mItemTouchHelper.attachToRecyclerView(moreRecycler);

        bianJi.setOnClickListener(this);
        changyongAdapter.setCheckMuban(new CheckMuban() {
            @Override
            public void appear(View v, int position) {
                listXia.add(listXia.size(), listShang.get(position));
                changyongXiaAdapter.notifyItemInserted(0);
                listShang.remove(position);
                changyongAdapter.notifyItemRemoved(position);
                changyongAdapter.notifyItemRangeChanged(0, listShang.size());
                changyongXiaAdapter.notifyItemRangeChanged(0, listXia.size());
            }
        });

        changyongXiaAdapter.setCheckMuban(new CheckMuban() {
            @Override
            public void appear(View v, int position) {
                if (listShang.size() >= 4) {
                    Toast.makeText(ZoushituHomeActivity.this, "常用走势图最多放置4张", Toast.LENGTH_SHORT).show();
                    return;
                }
                listShang.add(0, listXia.get(position));
                changyongAdapter.notifyItemInserted(0);
                listXia.remove(position);
                changyongXiaAdapter.notifyItemRemoved(position);
                changyongAdapter.notifyItemRangeChanged(0, listShang.size());
                changyongXiaAdapter.notifyItemRangeChanged(0, listXia.size());
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.every_top_right:
                if (!checkMuban) {
                    bianJi.setText("完成");
                    checkMuban = true;
                    changyongXiaAdapter.getCheck(true);
                    changyongAdapter.getCheck(true);
                    changyongAdapter.notifyDataSetChanged();
                    changyongXiaAdapter.notifyDataSetChanged();
                } else {
                    bianJi.setText("编辑");
                    checkMuban = false;
                    changyongXiaAdapter.getCheck(false);
                    changyongAdapter.getCheck(false);
                    changyongAdapter.notifyDataSetChanged();
                    changyongXiaAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setTags(new String[0]);
    }
}
