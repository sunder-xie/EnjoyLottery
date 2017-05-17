package com.byl.enjoylottery.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byl.enjoylottery.activity.CreatGroupActivity;
import com.example.administrator.enjoylottery.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class EssentialInformationFragment extends BaseFragment implements View.OnClickListener, OnWheelChangedListener {
    private View view;
    private Uri uritempFile;
    private TextView tiCai,fuCai,jingCai,sure,address;
    public int caiZhong = 0;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private LinearLayout choiceHead, regionLay;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLER_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 3;
    private RelativeLayout album, camera;
    private ImageView head;
    private Intent intent;
    public Bitmap bitmap = null;
    public List<String> list = new ArrayList<>();
    public EditText groupName,groupIntroduce;
    private Uri mImageUri = Uri.fromFile(new File("/sdcard/lottery/head.jpg"));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_essential_information,null,false);
        initView();
        initEvent();
        setUpData();
        return view;
    }

    private void initView(){
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        tiCai = (TextView) view.findViewById(R.id.group_chart_ticai);
        fuCai = (TextView) view.findViewById(R.id.group_chart_fucai);
        jingCai = (TextView) view.findViewById(R.id.group_chart_jingcai);
        sure = (TextView) view.findViewById(R.id.sure_region);
        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        choiceHead = (LinearLayout) view.findViewById(R.id.xiangce_xiangji);
        album = (RelativeLayout) view.findViewById(R.id.ral_xiangce);
        camera = (RelativeLayout) view.findViewById(R.id.ral_xiangji);
        regionLay = (LinearLayout) view.findViewById(R.id.province_city);
        address = (TextView) view.findViewById(R.id.group_chart_address);
        head = (ImageView)view.findViewById(R.id.head_image);
        groupName = (EditText) view.findViewById(R.id.group_name);
        groupIntroduce = (EditText) view.findViewById(R.id.group_introduce);
    }

    private void initEvent(){
        tiCai.setOnClickListener(this);
        fuCai.setOnClickListener(this);
        jingCai.setOnClickListener(this);
        album.setOnClickListener(this);
        camera.setOnClickListener(this);
        sure.setOnClickListener(this);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        address.setOnClickListener(this);
        head.setOnClickListener(this);
  }

    @Override
    public void onClick(View v) {
        setCaiZhongColor();
        switch (v.getId()){
            case R.id.group_chart_ticai:
                tiCai.setTextColor(getResources().getColor(R.color.zitilv));
                tiCai.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                caiZhong = 1;
                break;
            case R.id.group_chart_fucai:
                fuCai.setTextColor(getResources().getColor(R.color.zitilv));
                fuCai.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                caiZhong = 2;
                break;
            case R.id.group_chart_jingcai:
                jingCai.setTextColor(getResources().getColor(R.color.zitilv));
                jingCai.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                caiZhong = 3;
                break;
            case R.id.group_chart_address:
                regionLay.setVisibility(View.VISIBLE);
                break;
            case R.id.sure_region:
                regionLay.setVisibility(View.GONE);
                address.setText(CreatGroupActivity.getContext().mCurrentProviceName + "  " + CreatGroupActivity.getContext().mCurrentCityName);
                list = CreatGroupActivity.getContext().getCode(CreatGroupActivity.getContext().mCurrentProviceName,CreatGroupActivity.getContext().mCurrentCityName);
                break;
            case R.id.head_image:
                choiceHead.setVisibility(View.VISIBLE);
                break;
            case R.id.ral_xiangce:
                choiceHead.setVisibility(View.GONE);
                choiceHead.setVisibility(View.GONE);
                Intent it = new Intent(Intent.ACTION_PICK, null);
                it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(it, GALLER_REQUEST_CODE);
                break;
            case R.id.ral_xiangji:
                choiceHead.setVisibility(View.GONE);
                choiceHead.setVisibility(View.GONE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);// 拍照后保存的路径
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri urii = mImageUri;
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                startImageZoom(urii);
                break;
            case GALLER_REQUEST_CODE:
                if (data == null) {
                    return;
                } else {
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                        startImageZoom(uri);
                    }
                }
                break;
            case CROP_REQUEST_CODE:
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        try {
                            bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uritempFile));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        head.setImageBitmap(bitmap);
                        try {
//                            uploadFile(saveBitmapFile(bitmap),uri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/sdcard/card002.jpg");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 9998);
        intent.putExtra("aspectY", 9999);
        intent.putExtra("outPut", 150);    //裁剪输出宽高
        intent.putExtra("outPutY", 150);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        }
    }

    private void updateCities() {
        try {
            int pCurrent = mViewProvince.getCurrentItem();
            // 将当前的省赋值给全局
            CreatGroupActivity.getContext().mCurrentProviceName = CreatGroupActivity.getContext().mProvinceDatas[pCurrent];
            String[] cities = CreatGroupActivity.getContext().mCitisDatasMap.get(CreatGroupActivity.getContext().mCurrentProviceName);
            if (cities == null) {
                cities = new String[]{""};
            } else if (cities.length == 0) {
                cities = new String[]{""};
                CreatGroupActivity.getContext().mCurrentCityName = "";
                CreatGroupActivity.getContext().mCurrentDistrictName = "";
            }
            mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), cities));
            mViewCity.setCurrentItem(0);
            updateAreas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAreas() {
        try {
            int pCurrent = mViewCity.getCurrentItem();
            // 将当前的市赋值给全局
            CreatGroupActivity.getContext().mCurrentCityName = CreatGroupActivity.getContext().mCitisDatasMap.get(CreatGroupActivity.getContext().mCurrentProviceName)[pCurrent];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpData() {
        CreatGroupActivity.getContext().initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
                getActivity(),CreatGroupActivity.getContext().mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(8);
        mViewCity.setVisibleItems(8);
        updateCities();
        updateAreas();
    }

    public Boolean getGo(){
        if (!groupName.getText().toString().trim().equals("")&&
                !groupIntroduce.getText().toString().trim().equals("")&&
                !address.getText().toString().trim().equals("")&&
                caiZhong!=0&&
                null!=bitmap){
            return true;
        }
        return false;
    }

    private void setCaiZhongColor(){
        fuCai.setTextColor(getResources().getColor(R.color.black));
        fuCai.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
        tiCai.setTextColor(getResources().getColor(R.color.black));
        tiCai.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
        jingCai.setTextColor(getResources().getColor(R.color.black));
        jingCai.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
    }
}
