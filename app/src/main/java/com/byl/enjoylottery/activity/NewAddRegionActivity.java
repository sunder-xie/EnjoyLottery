package com.byl.enjoylottery.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;

import java.util.ArrayList;

import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;


/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class NewAddRegionActivity extends BaseActivity implements OnWheelChangedListener {

    private TextView titleRight;
    private LinearLayout checkAddress;
    private TextView country, sure;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private ImageView location;
    private Intent intent;
    private String coordinate;//坐标
    private EditText detailDizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_region);
        initView();
        initEvent();
        setUpData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("新建地址");
        titleRight = (TextView) findViewById(R.id.every_top_right);
        titleRight.setText("保存");
        titleRight.setBackgroundResource(R.color.save);
        checkAddress = (LinearLayout) findViewById(R.id.ziliao_lay_chengshi);
        country = (TextView) findViewById(R.id.xuanze_diqu);
        sure = (TextView) findViewById(R.id.ziliao_queding);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        location = (ImageView) findViewById(R.id.location_address);
        detailDizhi = (EditText) findViewById(R.id.detail_address);
    }

    private void initEvent() {
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        country.setOnClickListener(this);
        sure.setOnClickListener(this);
        location.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.xuanze_diqu:
                checkAddress.setVisibility(View.VISIBLE);
                break;
            case R.id.ziliao_queding:
                checkAddress.setVisibility(View.GONE);
                country.setText(mCurrentProviceName + "  " + mCurrentCityName + "  " + mCurrentDistrictName);
                country.setTextColor(Color.BLACK);
                break;
            case R.id.location_address:
                intent = new Intent(NewAddRegionActivity.this, MapBiaojiActivity.class);
                if ("地区信息".equals(country.getText().toString().trim())){
                    Toast.makeText(NewAddRegionActivity.this,"请选择一个地区",Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("city",mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
                intent.putExtra("jiedao",detailDizhi.getText().toString().trim());
                startActivityForResult(intent, 1);
                break;
            case R.id.every_top_right:
                ArrayList<String> list = new ArrayList<>();
                list = getCode(mCurrentProviceName,mCurrentCityName,mCurrentDistrictName);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (data!=null) {
                detailDizhi.setText(data.getStringExtra("address") != null ?  data.getStringExtra("address") : "街道门派信息");
                coordinate = data.getStringExtra("coordinate");
            }
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            updateZipCode();
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        try {
            int pCurrent = mViewProvince.getCurrentItem();
            // 将当前的省赋值给全局
            mCurrentProviceName = mProvinceDatas[pCurrent];
            String[] cities = mCitisDatasMap.get(mCurrentProviceName);
            if (cities == null) {
                cities = new String[]{""};
            } else if (cities.length == 0) {
                cities = new String[]{""};
                mCurrentCityName = "";
                mCurrentDistrictName = "";
            }
            mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
            mViewCity.setCurrentItem(0);
            updateAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        try {
            int pCurrent = mViewCity.getCurrentItem();
            // 将当前的市赋值给全局
            mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
            String[] areas = mDistrictDatasMap.get(mCurrentCityName);

            if (areas == null) {
                areas = new String[]{""};
            } else if (areas.length == 0) {
                areas = new String[]{""};
                mCurrentDistrictName = "";
            }
            mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
            mViewDistrict.setCurrentItem(0);
            updateZipCode();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 将变化的区和邮政编码更新到全局
     */
    private void updateZipCode() {
        try {
            int pCurrent = mViewDistrict.getCurrentItem();
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[pCurrent];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
                NewAddRegionActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }
}
