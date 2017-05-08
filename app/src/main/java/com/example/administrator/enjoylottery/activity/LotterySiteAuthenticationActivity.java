package com.example.administrator.enjoylottery.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.adapter.LotterySiteAuthenticationAdapter;
import com.example.administrator.enjoylottery.bean.CaiZhanRenZhengBean;
import com.example.administrator.enjoylottery.bean.LotteryAuthenticationBean;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.tools.SharedPreferencesUtils;
import com.example.administrator.enjoylottery.view.MyListView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class LotterySiteAuthenticationActivity extends BaseActivity {

    private LinearLayout addCaizhan;
    private Intent intent;
    private MyListView listView;
    private LotterySiteAuthenticationAdapter lotterySiteAuthenticationAdapter;
    private List<CaiZhanRenZhengBean> list = new ArrayList<>();
    private JSONObject object = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotterysite_authentication);
        initView();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showToastShort("您还未有彩票站进行认证");
                    break;
            }
        }
    };

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("彩站认证");
        addCaizhan = (LinearLayout) findViewById(R.id.add_caizhanrenzheng);
        addCaizhan.setOnClickListener(this);
        listView = (MyListView) findViewById(R.id.caizhanrenzheng_listview);
        initData();
        lotterySiteAuthenticationAdapter = new LotterySiteAuthenticationAdapter(this, list);
        listView.setAdapter(lotterySiteAuthenticationAdapter);
        try {
            String id = (String) SharedPreferencesUtils.getParam(this,"id","");
            object.put("userId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new getLottery().execute();
    }

    private void initData() {
        CaiZhanRenZhengBean bean = new CaiZhanRenZhengBean();
        bean.setZhandianNumber("2112054682");
        bean.setType(1);
        bean.setShiFouTongguo(0);
        list.add(bean);
        CaiZhanRenZhengBean bean1 = new CaiZhanRenZhengBean();
        bean1.setZhandianNumber("21120556487");
        bean1.setType(2);
        bean1.setShiFouTongguo(1);
        list.add(bean1);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.add_caizhanrenzheng:
                intent = new Intent(this, CaizhanRegistrationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    class getLottery extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getHaveLottery(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                return;
            }
            Gson gson = new Gson();
            LotteryAuthenticationBean bean = gson.fromJson(s,LotteryAuthenticationBean.class);
            if (bean.getStations().size()==0){
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                return;
            }else {

            }
        }
    }
}
