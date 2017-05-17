package com.enjoylottery.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.enjoylottery.listener.PermissonCallBack;
import com.enjoylottery.listener.PermissonsCallBack;
import com.enjoylottery.model.CityModel;
import com.enjoylottery.model.DistrictModel;
import com.enjoylottery.model.Info;
import com.enjoylottery.model.ProvinceModel;
import com.enjoylottery.service.XmlParserHandler;
import com.example.administrator.enjoylottery.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{
    PermissonCallBack p_callback;
    PermissonsCallBack ps_callback;

    //用户同意权限标识
    public static final int U_ALLOW = PackageManager.PERMISSION_GRANTED;
    //拒绝
    public static final int U_DENIED = PackageManager.PERMISSION_DENIED;
    /**
     * 已知权限列表
     * 如果申请的权限为当前权限集合等级为最高，那么只需要申请一个最高权限即可
     */
    //GPS权限
    public static final String P_GPS = Manifest.permission.ACCESS_FINE_LOCATION;
    //相机
    public static final String P_CAMERA = Manifest.permission.CAMERA;
    //内外置SD卡读写权限
    public static final String P_SD = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    //拨打电话权限
    public static final String P_CALL = Manifest.permission.CALL_PHONE;

    long lastClick = 0;
    /**
     * 所有省
     */
    public String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    public Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * key - 省 value - code
     */
    protected Map<String, String> mProvinceCode = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    public String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    public String mCurrentCityName;
    /**
     * 当前区的名称
     */
    public String mCurrentDistrictName ="";

    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode ="";

    /**
    *  当前省的邮政编码
    */
    protected String provinceCode ="";
    List<ProvinceModel> provinceList = null;
    List<CityModel> cityList = null;
    List<DistrictModel> districtList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.overridePendingTransition(R.anim.slide_left_in,
                R.anim.fade_out_long_animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }


    /**
     * 隐藏键盘
     */
    public void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == U_ALLOW) {
                    p_callback.p_CallBack(permissions[0], 1);
                } else {
                    p_callback.p_CallBack(permissions[0], -1);
                }
                break;

            case 200:
                List<String> a_permissions = new ArrayList<>();
                List<String> d_permissions = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == 0) {
                        a_permissions.add(permissions[i]);
                    } else {
                        d_permissions.add(permissions[i]);
                    }
                }
                if (a_permissions.size() == permissions.length) {
                    ps_callback.ps_CallBack(permissions, 1);
                } else {
                    String[] str_permissions = new String[d_permissions.size()];
                    d_permissions.toArray(str_permissions);
                    ps_callback.ps_CallBack(str_permissions, -1);

                }
                break;
        }
    }


    // 申请单个权限
    public void ApplyPermisson(String permissionName, PermissonCallBack callBack) {
        this.p_callback = callBack;
        try {
            if (ActivityCompat.checkSelfPermission(this, permissionName) == U_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{permissionName}, 100);
            } else if (ActivityCompat.checkSelfPermission(this, permissionName) == U_ALLOW) {
//                ToastUtil.longToast(UmengBaseActivity.this, "已拥有该权限，无需再次申请");
                p_callback.p_CallBack(permissionName, 0);
            } else {
//                ToastUtil.longToast(UmengBaseActivity.this, "权限申请失败");
                p_callback.p_CallBack(permissionName, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //申请权限组
    // update by lz on 20170408
    public void ApplyPermissons(String[] permissonNames, PermissonsCallBack callBack) {
        this.ps_callback = callBack;
        try {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < permissonNames.length; i++) {
                if ((ActivityCompat.checkSelfPermission(this, permissonNames[i]) == U_DENIED)) {
                    list.add(permissonNames[i]);
                }
            }
            if (list.size() > 0) {
                String[] rPermissons = new String[list.size()];
                list.toArray(rPermissons);
                ActivityCompat.requestPermissions(this, rPermissons, 200);
            } else ps_callback.ps_CallBack(permissonNames, 0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析省市区的XML数据
     */
    public void initProvinceDatas(){
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }

            for (int a = 0; a < provinceList.size(); a++)
            {
                if (Info.getInstance().getProvince().equals(provinceList.get(a).getName()))
                {
                    Info.getInstance().setCode(provinceList.get(a).getCode());
                }
            }

            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    public ArrayList<String> getCode(String province, String city, String country){
        ArrayList<String> list = new ArrayList<>();
        String provinceCode = "";
        String cityCode = "";
        String countryCode = "";
        int c = 0, d = 0;
        if (null!=province) {
            for (int a = 0; a < provinceList.size(); a++) {
                if (province.equals(provinceList.get(a).getName())) {
                    provinceCode = provinceList.get(a).getCode();
                    c = a;
                    break;
                }
            }
        }

        if (null!=city) {
            for (int a = 0; a < provinceList.get(c).getCityList().size(); a++) {
                if (city.equals(provinceList.get(c).getCityList().get(a).getName())) {
                    cityCode = provinceList.get(c).getCityList().get(a).getCode();
                    d = a;
                    break;
                }
            }
        }

        if (null!=country) {
            for (int a = 0; a < provinceList.get(c).getCityList().get(d).getDistrictList().size(); a++) {
                if (country.equals(provinceList.get(c).getCityList().get(d).getDistrictList().get(a).getName())) {
                    countryCode = provinceList.get(c).getCityList().get(d).getDistrictList().get(a).getZipcode();
                    break;
                }
            }
        }
        list.add(provinceCode);
        list.add(cityCode);
        list.add(countryCode);
        return list;
    }

    public ArrayList<String> getCode(String province, String city){
        ArrayList<String> list = new ArrayList<>();
        String provinceCode = "";
        String cityCode = "";
        int c = 0;
        if (null!=province) {
            for (int a = 0; a < provinceList.size(); a++) {
                if (province.equals(provinceList.get(a).getName())) {
                    provinceCode = provinceList.get(a).getCode();
                    c = a;
                    break;
                }
            }
        }

        if (null!=city) {
            for (int a = 0; a < provinceList.get(c).getCityList().size(); a++) {
                if (city.equals(provinceList.get(c).getCityList().get(a).getName())) {
                    cityCode = provinceList.get(c).getCityList().get(a).getCode();
                    break;
                }
            }
        }

        list.add(provinceCode);
        list.add(cityCode);
        return list;
    }

    public void onBack(View view){
        finish();
    }

    public abstract void widgetClick(View v);
    /** * [防止快速点击] * * @return */
    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 500) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (fastClick()){
            widgetClick(v);
        }else {
            Toast.makeText(BaseActivity.this,"点击过快",Toast.LENGTH_SHORT).show();
        }
    }

    public void showToastShort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
