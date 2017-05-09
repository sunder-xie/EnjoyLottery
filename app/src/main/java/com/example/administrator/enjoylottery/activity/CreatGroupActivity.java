package com.example.administrator.enjoylottery.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.fragment.CompleteSubmissionFragment;
import com.example.administrator.enjoylottery.fragment.EssentialInformationFragment;
import com.example.administrator.enjoylottery.fragment.FuntionConfigurationFragment;
import com.example.administrator.enjoylottery.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CreatGroupActivity extends BaseActivity {
    private TextView cancel,information,funtion,finish,commit;
    private NoScrollViewPager viewPager;
    private List<Fragment> list = new ArrayList<>();
    private Fragment informationFg,funtionFg,finishFg;
    private FragmentPagerAdapter adapter;
    private int nowPager = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        initView();
        initEvent();
        setViewPager();
  }

    private void initView(){
        ((TextView)findViewById(R.id.every_top_text)).setText("建 群");
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
    }

    private void setViewPager(){
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

    private void initEvent(){
        cancel.setOnClickListener(this);
        commit.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //按下的如果是BACK，同时没有重复
            switch (nowPager){
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
        switch (v.getId()){
            case R.id.every_top_right:
                finish();
              break;
            case R.id.creat_group_xiayibu:
                switch (nowPager){
                    case 0:
                        viewPager.setCurrentItem(1);
                        break;
                    case 1:
                        viewPager.setCurrentItem(2);
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

    private void setBackTop(int position){
        switch (position){
            case 0:
                information.setBackgroundColor(getResources().getColor(R.color.zitilv));
                funtion.setBackgroundColor(getResources().getColor(R.color.heihui));
                finish.setBackgroundColor(getResources().getColor(R.color.heihui));
                break;
            case 1:
                information.setBackgroundColor(getResources().getColor(R.color.heihui));
                funtion.setBackgroundColor(getResources().getColor(R.color.zitilv));
                finish.setBackgroundColor(getResources().getColor(R.color.heihui));
                break;
            case 2:
                information.setBackgroundColor(getResources().getColor(R.color.heihui));
                funtion.setBackgroundColor(getResources().getColor(R.color.heihui));
                finish.setBackgroundColor(getResources().getColor(R.color.zitilv));
                break;
            default:
                break;
        }
    }
}
