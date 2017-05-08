package com.example.administrator.enjoylottery.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.bean.MapBean;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class MapBiaojiActivity extends BaseActivity implements OnGetGeoCoderResultListener {
    private MapView mMapView = null;
    private BaiduMap bdMap;
    private LocationClient locClient;
    private Boolean isFirstLoc = true;
    private GeoCoder mSearch;
    private String city;
    private String jiedao;
    private LatLng latLng;
    private String address;
    private String latitude, longitude;
    private Double latitudeD, longitudeD;
    private Marker marker1;
    private TextView sure;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acativity_map_biaoji);
//        locClient = new LocationClient(this);
//        locClient.registerLocationListener(locListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);// 打开gps
//        option.setCoorType("bd09ll");// 设置坐标类型
//        option.setAddrType("all");
//        option.setScanSpan(1000);//
//        locClient.setLocOption(option);
//        locClient.start();
        initView();
    }

    private void initView() {
        mSearch = GeoCoder.newInstance();
        city = getIntent().getStringExtra("city");
        jiedao = getIntent().getStringExtra("jiedao");
        mMapView = (MapView) findViewById(R.id.ziliao_baiduditu);
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        bdMap = mMapView.getMap();
        bdMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(17).build()));
//        bdMap.setMapStatus(msu);
//        bdMap.setMyLocationEnabled(true);
        ((TextView) findViewById(R.id.every_top_text)).setText("标记位置");
        ((TextView) findViewById(R.id.every_top_right)).setText("确定");
        bdMap.setOnMapClickListener(new MyOnMapClickListener());
        new getMap().execute();
        sure = (TextView) findViewById(R.id.every_top_right);
        sure.setOnClickListener(this);
    }

    private void addMarkerOverlay(LatLng point) {
        bdMap.clear();
        // 定义marker坐标点
        // 构建markerOption，用于在地图上添加marker
        OverlayOptions options = new MarkerOptions()//
                .position(point)// 设置marker的位置
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka))// 设置marker的图标
                .zIndex(9)// 設置marker的所在層級
                .draggable(true);// 设置手势拖拽
        // 在地图上添加marker，并显示
        marker1 = (Marker) bdMap.addOverlay(options);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            //没有检索到结果
        } else {
            LatLng ll = geoCodeResult.getLocation();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(ll);
            bdMap.animateMapStatus(msu);
        }
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    class getMap extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getMap(city+jiedao, "json", "PB7iPifS42zSLLYHEGiTxRAyqSWwsQXe", city);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
            }
            Gson gson = new Gson();
            MapBean bean = new MapBean();
            bean = gson.fromJson(s, MapBean.class);
            LatLng ll = new LatLng(bean.getResult().getLocation().getLat(),
                    bean.getResult().getLocation().getLng());
            // MapStatusUpdate描述地图将要发生的变化
            // MapStatusUpdateFactory生成地图将要反生的变化
            displayInfoWindow(ll);
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(ll);
            bdMap.animateMapStatus(msu);
        }
    }

    class MyOnMapClickListener implements BaiduMap.OnMapClickListener{
        @Override
        public void onMapClick(LatLng latLng) {
            displayInfoWindow(latLng);
            reverseGeoCode(latLng);
            MapBiaojiActivity.this.latLng = latLng;
            latitude = latLng.latitude + "";
            longitude = latLng.longitude + "";
        }

        @Override
        public boolean onMapPoiClick(MapPoi mapPoi) {
            MapBiaojiActivity.this.latLng = mapPoi.getPosition();
            address = mapPoi.getName();
            displayInfoWindow(mapPoi.getPosition());
            MapBiaojiActivity.this.latLng = mapPoi.getPosition();
            latitude = latLng.latitude + "";
            longitude = latLng.longitude + "";
            Toast.makeText(MapBiaojiActivity.this, "位置：" + mapPoi.getName().toString(),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 反地理编码得到地址信息
     */
    private void reverseGeoCode(LatLng latLng) {
        // 创建地理编码检索实例
        GeoCoder geoCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            // 反地理编码查询结果回调函数
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null
                        || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                    Toast.makeText(MapBiaojiActivity.this, "抱歉，未能找到结果",
                            Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MapBiaojiActivity.this, "位置：" + result.getAddress(), Toast.LENGTH_LONG).show();
                address = result.getAddress();
            }

            // 地理编码查询结果回调函数
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有检测到结果
                }
            }
        };
        // 设置地理编码检索监听者
        geoCoder.setOnGetGeoCodeResultListener(listener);
        //
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
        // 释放地理编码检索实例
        // geoCoder.destroy();
    }

    /**
     * 显示弹出窗口覆盖物
     */
    private void displayInfoWindow(final LatLng latLng) {
        // 创建infowindow展示的view
        ImageView img = new ImageView(getApplicationContext());
        img.setBackgroundResource(R.drawable.icon_marka);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromView(img);

        // 创建infowindow
        InfoWindow infoWindow = new InfoWindow(bitmapDescriptor, latLng, -47,
                new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {

                    }
                });

        // 显示InfoWindow
        bdMap.showInfoWindow(infoWindow);
    }

    /**
     * 定位监听器
     */
    BDLocationListener locListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || bdMap == null) {
                return;
            }
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())//
                    .direction(100)// 方向
                    .latitude(location.getLatitude())//
                    .longitude(location.getLongitude())//
                    .build();
            // 设置定位数据
            bdMap.setMyLocationData(locData);
            latitudeD = location.getLatitude();
            longitudeD = location.getLongitude();
            // 第一次定位的时候，那地图中心店显示为定位到的位置
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                // MapStatusUpdate描述地图将要发生的变化
                // MapStatusUpdateFactory生成地图将要反生的变化
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(ll);
                bdMap.animateMapStatus(msu);
                // bdMap.setMyLocationEnabled(false);
                Toast.makeText(getApplicationContext(), location.getAddrStr(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.every_top_right:
                intent = new Intent();
                intent.putExtra("address",address);
                intent.putExtra("coordinate",longitude + "," +latitude);
                setResult(RESULT_OK,intent);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
//        locClient.stop();
        bdMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        mSearch.destroy();
    }
}
