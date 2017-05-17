package com.enjoylottery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;

import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class CaizhanRegistrationActivity extends BaseActivity implements OnWheelChangedListener,TextWatcher{
    private EditText bianHao, detailDizhi, baiYinlinEdit;
    private TextView shengShi, mapBiaoji, jixu, sure;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private LinearLayout cityLay;
    private Intent intent;
    private Boolean go = false;
    private RadioGroup radioGroup , radioCaiZhong; // 是否是佰艺霖客户
    private RadioButton yesRB,noRB,tiCai,fuCai;
    private String caizhong;
    private String a; //体彩：1，福彩：2
    private String b; //是：1，否：0
    private String coordinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caizhan);
        initView();
        initEvent();
        setUpData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("彩站注册");
        ((TextView) findViewById(R.id.tianxie_ziliao)).setTextColor(getResources().getColor(R.color.zitilv));
        bianHao = (EditText) findViewById(R.id.ziliao_dianbianhao);
        detailDizhi = (EditText) findViewById(R.id.ziliao_xiangxidizhi);
        shengShi = (TextView) findViewById(R.id.ziliao_shengfenshi);
        mapBiaoji = (TextView) findViewById(R.id.ziliao_ditubiaoji);
        jixu = (TextView) findViewById(R.id.ziliao_xiayibu);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        sure = (TextView) findViewById(R.id.ziliao_queding);
        cityLay = (LinearLayout) findViewById(R.id.ziliao_lay_chengshi);
        baiYinlinEdit = (EditText) findViewById(R.id.baiyilin_tongxinghao);
        radioGroup = (RadioGroup) findViewById(R.id.radio_baiyinlin_user);
        radioCaiZhong = (RadioGroup) findViewById(R.id.ticaidian_leixing);
        yesRB = (RadioButton) findViewById(R.id.radiobutton_yes);
        noRB = (RadioButton) findViewById(R.id.radiobutton_no);
        tiCai = (RadioButton) findViewById(R.id.radio_ticai);
        fuCai = (RadioButton) findViewById(R.id.radio_fucai);
    }

    public void getGo(){
        if ((tiCai.isChecked() || fuCai.isChecked())&&!bianHao.getText().toString().trim().equals("")&&!detailDizhi.getText().toString().trim().equals("")&&!shengShi.getText().toString().trim().equals("")&&mapBiaoji.getText().toString().contains("已")) {
            jixu.setBackgroundResource(R.drawable.dianjizhuce);
            go = true;
        }else {
            jixu.setBackgroundResource(R.color.edithui);
            go =false;
        }
    }

    public void initEvent() {
        shengShi.setOnClickListener(this);
        mapBiaoji.setOnClickListener(this);
        sure.setOnClickListener(this);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        detailDizhi.addTextChangedListener(this);
        bianHao.addTextChangedListener(this);
        mapBiaoji.addTextChangedListener(this);
        jixu.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiobutton_yes:
                        baiYinlinEdit.setVisibility(View.VISIBLE);
                        b="1";
                        break;
                    case R.id.radiobutton_no:
                        baiYinlinEdit.setText("");
                        b="0";
                        baiYinlinEdit.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });
        radioCaiZhong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_ticai:
                        caizhong = "ticai";
                        a = "1";
                        getGo();
                        break;
                    case R.id.radio_fucai:
                        caizhong = "fucai";
                        a = "2";
                        getGo();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ziliao_shengfenshi:
                hideInputMethod();
                cityLay.setVisibility(View.VISIBLE);
                break;
            case R.id.ziliao_ditubiaoji:
                intent = new Intent(CaizhanRegistrationActivity.this, MapBiaojiActivity.class);
                if (shengShi.getText().toString().trim().equals("")){
                    Toast.makeText(CaizhanRegistrationActivity.this,"请选择一个地区",Toast.LENGTH_SHORT).show();
                    return;
                }else if (detailDizhi.getText().toString().trim().equals("")) {
                    Toast.makeText(CaizhanRegistrationActivity.this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("city",mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
                intent.putExtra("jiedao",detailDizhi.getText().toString().trim());
                startActivityForResult(intent, 1);
                break;
            case R.id.ziliao_queding:
                //mCurrentZipCode
                cityLay.setVisibility(View.GONE);
                shengShi.setText(mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
                getGo();
                break;
            case R.id.ziliao_xiayibu:
                if (go == true){
                    Bundle bundle = new Bundle();
                    bundle.putString("lotteryType",a);
                    bundle.putString("stationNumber",bianHao.getText().toString());
                    bundle.putStringArrayList("everyCode",getCode(mCurrentProviceName,mCurrentCityName,mCurrentDistrictName));
                    bundle.putString("address",detailDizhi.getText().toString());
                    bundle.putString("coordinate",coordinate);
                    bundle.putString("isBylStation",b);
                    bundle.putString("bylStationCode",baiYinlinEdit.getText().toString());
                    intent = new Intent(CaizhanRegistrationActivity.this,ZhengjianRenzhengActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            default:
                break;
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

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
                CaizhanRegistrationActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
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
            }else if (cities.length == 0){
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
            }else if (areas.length == 0){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (data!=null) {
                mapBiaoji.setText(data.getStringExtra("address") != null ? "已标记：" + data.getStringExtra("address") : "未标记");
                coordinate = data.getStringExtra("coordinate");
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        getGo();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
