package com.example.administrator.enjoylottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.adapter.MyFragmentPagerAdapter;
import com.example.administrator.enjoylottery.bean.BallBean;
import com.example.administrator.enjoylottery.bean.ProvincePlayBean;
import com.example.administrator.enjoylottery.fragment.ChartFragment;
import com.example.administrator.enjoylottery.fragment.FaxianFragment;
import com.example.administrator.enjoylottery.fragment.GongjuFragment;
import com.example.administrator.enjoylottery.fragment.MeFragment;
import com.example.administrator.enjoylottery.fragment.MeFragmentTwo;
import com.example.administrator.enjoylottery.model.Info;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.example.administrator.enjoylottery.service.MapApplication;
import com.example.administrator.enjoylottery.tools.SharedPreferencesUtils;
import com.example.administrator.enjoylottery.view.WeiboDialogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.leibnik.wechatradiobar.WechatRadioButton;
import io.github.leibnik.wechatradiobar.WechatRadioGroup;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class HomeActivity extends BaseActivity {
    private ViewPager viewPager;
    private MyFragmentPagerAdapter adapter;
    private List<Fragment> list = new ArrayList<>();
    private Fragment chartFragment, gongjuFragment, meFragment, faxianFragment;
    private WechatRadioGroup wechatRadioGroup;
    private WechatRadioButton chart;
    private LocationClient mLocationClient = null;
    private Dialog mWeiboDialog;
    private int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        mWeiboDialog = WeiboDialogUtils.createLoadingDialog(HomeActivity.this, "加载中...");
        new GetBall().execute();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(HomeActivity.this, "请您连接网络", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void widgetClick(View v) {
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.home_viewpager);
        wechatRadioGroup = (WechatRadioGroup) findViewById(R.id.dibudaohanglan);
        chart = (WechatRadioButton) findViewById(R.id.liaotian);
        chart.setRadioButtonChecked(true);
        initLocation();
    }

    private void initLocation() {
        MyLocationListenner myListener = new MyLocationListenner();
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            try {
                String province = bdLocation.getProvince();
                String city = bdLocation.getCity();
                Info.getInstance().setProvince(province);
                Info.getInstance().setCity(city);
                initProvinceDatas();
                new PrivincePlay(0).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initEvent() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(b);
        wechatRadioGroup.setViewPager(viewPager);
    }

    class PrivincePlay extends AsyncTask<Void, Void, String> {
        int a = 0;

        public PrivincePlay(int a) {
            this.a = a;
        }

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getPrivincePlay(Info.getInstance().getCode());
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                return;
            } else {
                list.clear();
                Gson gson = new Gson();
                List<ProvincePlayBean> lists = gson.fromJson(s, new TypeToken<ArrayList<ProvincePlayBean>>() {
                }.getType());

                if (1 == (Integer) (SharedPreferencesUtils.getParam(HomeActivity.this, "firstLogin", 2))) {
                    meFragment = new MeFragmentTwo();
                    chartFragment = new ChartFragment();
                    gongjuFragment = new GongjuFragment(lists);
                    faxianFragment = new FaxianFragment();
                    list.add(chartFragment);
                    list.add(gongjuFragment);
                    list.add(faxianFragment);
                    list.add(meFragment);
                    initEvent();
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    return;
                }

                if (a == 0) {
                    meFragment = new MeFragment();
                } else if (a == 1) {
                    meFragment = new MeFragmentTwo();
                }
                chartFragment = new ChartFragment();
                gongjuFragment = new GongjuFragment(lists);
                faxianFragment = new FaxianFragment();
                list.add(chartFragment);
                list.add(gongjuFragment);
                list.add(faxianFragment);
                list.add(meFragment);
                initEvent();
                WeiboDialogUtils.closeDialog(mWeiboDialog);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            new PrivincePlay(1).execute();
            b = 3;
            mWeiboDialog = WeiboDialogUtils.createLoadingDialog(HomeActivity.this, "加载中...");
        }
    }

    public void changeFragment() {
        list.add(3, new MeFragment());
        adapter.notifyDataSetChanged();
    }

    class GetBall extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().get11f5Ball();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("TAG", s);
            BallBean ballBean = new BallBean();
            Gson gson = new Gson();
            List<BallBean> list = gson.fromJson(s, new TypeToken<ArrayList<BallBean>>() {
            }.getType());
            Collections.reverse(list);
            ballBean.setIssueNumber(Integer.parseInt(list.get(list.size() - 1).getIssueNumber()) + 1 + "");
            list.add(ballBean);
            MapApplication.getInstence().setList(list);
        }
    }
}
