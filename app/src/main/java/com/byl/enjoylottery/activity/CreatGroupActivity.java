package com.byl.enjoylottery.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.byl.enjoylottery.bean.CreatGroupBean;
import com.byl.enjoylottery.fragment.EssentialInformationFragment;
import com.byl.enjoylottery.fragment.FuntionConfigurationFragment;
import com.byl.enjoylottery.tools.SharedPreferencesUtils;
import com.byl.enjoylottery.view.NoScrollViewPager;
import com.byl.enjoylottery.view.WeiboDialogUtils;
import com.example.administrator.enjoylottery.R;
import com.byl.enjoylottery.fragment.CompleteSubmissionFragment;
import com.byl.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CreatGroupActivity extends BaseActivity {
    private TextView cancel, information, funtion, finish, commit;
    private NoScrollViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private Fragment informationFg, funtionFg, finishFg;
    private FragmentPagerAdapter adapter;
    private int nowPager = 0;
    private static CreatGroupActivity activity;
    private Dialog mWeiboDialog;
    public CreatGroupBean bean = null;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    viewPager.setCurrentItem(2);
                    ((CompleteSubmissionFragment)finishFg).setGroupInformation();
                    showToastShort("建群成功");
                    break;
                case 2:
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    showToastShort("建群失败");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        initView();
        initEvent();
        setViewPager();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("建 群");
        cancel = (TextView) findViewById(R.id.every_top_right);
        cancel.setText("取消");
        viewPager = (NoScrollViewPager) findViewById(R.id.creat_group_viewpager);
        information = (TextView) findViewById(R.id.creat_group_jibenxinxi);
        funtion = (TextView) findViewById(R.id.creat_group_gongnengpeizhi);
        finish = (TextView) findViewById(R.id.creat_group_wanchengtijiao);
        commit = (TextView) findViewById(R.id.creat_group_xiayibu);
        informationFg = new EssentialInformationFragment();
        funtionFg = new FuntionConfigurationFragment();
        finishFg = new CompleteSubmissionFragment();
        list.add(informationFg);
        list.add(funtionFg);
        list.add(finishFg);
        activity = this;
    }

    private void setViewPager() {
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        viewPager.setNoScroll(true);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nowPager = position;
                setBackTop(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent() {
        cancel.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //按下的如果是BACK，同时没有重复
            switch (nowPager) {
                case 0:
                    finish();
                    break;
                case 1:
                    viewPager.setCurrentItem(0);
                    break;
                case 2:
                    viewPager.setCurrentItem(1);
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.every_top_right:
                finish();
                break;
            case R.id.creat_group_xiayibu:
                switch (nowPager) {
                    case 0:
                        if (((EssentialInformationFragment) informationFg).getGo()) {
                            viewPager.setCurrentItem(1);
                        } else {
                            Toast.makeText(getContext(), "必填项不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        String uri = OKhttpHelper.getInstance().ESDABLISH_GROUP;
                        EssentialInformationFragment informationFgNew = (EssentialInformationFragment)informationFg;
                        FuntionConfigurationFragment funtionFgNew = (FuntionConfigurationFragment)funtionFg;
                        if (((FuntionConfigurationFragment) funtionFg).getNext()) {
                            mWeiboDialog = WeiboDialogUtils.createLoadingDialog(CreatGroupActivity.this, "加载中...");
                            AsyncHttpClient client = new AsyncHttpClient();
                            RequestParams params = new RequestParams();
                            try {
                                params.put("name",informationFgNew.groupName.getText().toString());
                                params.put("introduction",informationFgNew.groupIntroduce.getText().toString());
                                params.put("touXiangImg",informationFgNew.saveBitmapFile(informationFgNew.bitmap));
                                params.put("ownerId", SharedPreferencesUtils.getParam(CreatGroupActivity.this,"id",""));
                                params.put("lotteryType", informationFgNew.caiZhong + "");
                                params.put("province", informationFgNew.list.get(0));
                                params.put("city", informationFgNew.list.get(1));
                                params.put("joinType", funtionFgNew.mode);
                                params.put("fabuKj", funtionFgNew.relLottery);
                                params.put("fabuZs", funtionFgNew.relTrend);
                                params.put("ssYlChaxun", funtionFgNew.realMiss);
                                params.put("ssZjChaxun", funtionFgNew.realExp);
                                params.put("ssKjChaxun", funtionFgNew.realLot);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            client.post(uri, params, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                    try {
                                        String json = new String(bytes, "UTF-8");
                                        JSONObject object = new JSONObject(json);
                                        Gson gson = new Gson();
                                        bean = gson.fromJson(json,CreatGroupBean.class);
                                        Message msg = new Message();
                                        if (object.getBoolean("flag")){
                                            msg.what = 1;
                                        }else {
                                            msg.what = 2;
                                        }
                                        handler.sendMessage(msg);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                    Message msg = new Message();
                                    msg.what = 2;
                                    handler.sendMessage(msg);
                                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "必填项不能为空", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 2:
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void setBackTop(int position) {
        switch (position) {
            case 0:
                information.setBackgroundColor(getResources().getColor(R.color.zitilv));
                funtion.setBackgroundColor(getResources().getColor(R.color.heihui));
                finish.setBackgroundColor(getResources().getColor(R.color.heihui));
                commit.setText("下一步");
                break;
            case 1:
                information.setBackgroundColor(getResources().getColor(R.color.heihui));
                funtion.setBackgroundColor(getResources().getColor(R.color.zitilv));
                finish.setBackgroundColor(getResources().getColor(R.color.heihui));
                commit.setText("下一步");
                break;
            case 2:
                information.setBackgroundColor(getResources().getColor(R.color.heihui));
                funtion.setBackgroundColor(getResources().getColor(R.color.heihui));
                finish.setBackgroundColor(getResources().getColor(R.color.zitilv));
                commit.setText("完　成");
                break;
            default:
                break;
        }
    }

    public static CreatGroupActivity getContext() {
        return activity;
    }
}
