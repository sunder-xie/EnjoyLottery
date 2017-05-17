package com.enjoylottery.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enjoylottery.bean.PersonInformationBean;
import com.enjoylottery.view.WeiboDialogUtils;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.presenters.OKhttpHelper;
import com.enjoylottery.tools.SharedPreferencesUtils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import io.rong.imageloader.core.ImageLoader;
import widget.OnWheelChangedListener;
import widget.WheelView;
import widget.adapters.ArrayWheelAdapter;

import static com.enjoylottery.tools.SharedPreferencesUtils.getParam;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class PersonalInformationActivity extends BaseActivity implements OnWheelChangedListener {

    private ImageView head;
    private RelativeLayout headRel, weChatRel, myAddressRel, sexRel, regionRel, signatureRel, album, camera;
    private TextView tvWeChat, tvSex, tvRegion, tvSignature, sure;
    private LinearLayout choiceHead, regionLay;
    private Uri uritempFile;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private AlertDialog alertDialog;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLER_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 3;
    private Uri mImageUri = Uri.fromFile(new File("/sdcard/lottery/head.jpg"));
    private Intent intent;
    private Dialog dialog;
    private JSONObject object;
    private JSONObject jsonObject;
    private ArrayList<String> list = new ArrayList<>();
    private String uri = "http://36.7.190.20:1881/webappProject/outerLbuyerOrexpert/uploadFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        initView();
        initShow();
        initEvent();
        setUpData();
    }

    private void initView() {
        uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        ((TextView) findViewById(R.id.every_top_text)).setText("个人信息");
        head = (ImageView) findViewById(R.id.head_image);
        ImageLoader.getInstance().displayImage(OKhttpHelper.DOMAIN + SharedPreferencesUtils.getParam(this,"touXiangUrl",""),head);
        headRel = (RelativeLayout) findViewById(R.id.rel_head);
        weChatRel = (RelativeLayout) findViewById(R.id.rel_wechat);
        tvWeChat = (TextView) findViewById(R.id.tv_wechat);
        myAddressRel = (RelativeLayout) findViewById(R.id.rel_myaddress);
        sexRel = (RelativeLayout) findViewById(R.id.rel_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        regionRel = (RelativeLayout) findViewById(R.id.rel_region);
        tvRegion = (TextView) findViewById(R.id.tv_region);
        signatureRel = (RelativeLayout) findViewById(R.id.rel_signature);
        tvSignature = (TextView) findViewById(R.id.tv_signature);
        choiceHead = (LinearLayout) findViewById(R.id.xiangce_xiangji);
        album = (RelativeLayout) findViewById(R.id.ral_xiangce);
        camera = (RelativeLayout) findViewById(R.id.ral_xiangji);
        regionLay = (LinearLayout) findViewById(R.id.province_city);
        sure = (TextView) findViewById(R.id.sure_region);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        alertDialog = new AlertDialog.Builder(this).create();
    }

    private void initShow(){
        String sex = (String) getParam(PersonalInformationActivity.this, "sex", "");
        tvSex.setText("".equals(sex)?"":sex.equals("0")?"女":"男");
        String a = (String) SharedPreferencesUtils.getParam(PersonalInformationActivity.this,"provinceName","");
        String b = (String) SharedPreferencesUtils.getParam(PersonalInformationActivity.this,"cityCodeName","");
        tvRegion.setText(a + b);
        tvWeChat.setText(getIntent().getStringExtra("cailiaoHao").substring(4,getIntent().getStringExtra("cailiaoHao").length()));
        String provinceName = (String) SharedPreferencesUtils.getParam(PersonalInformationActivity.this, "provinceName", "");
        String cityCodeName = (String) SharedPreferencesUtils.getParam(PersonalInformationActivity.this, "cityCodeName", "");
        tvRegion.setText(provinceName + cityCodeName);
    }

    private void initEvent() {
        head.setOnClickListener(this);
        headRel.setOnClickListener(this);
        weChatRel.setOnClickListener(this);
        myAddressRel.setOnClickListener(this);
        sexRel.setOnClickListener(this);
        regionRel.setOnClickListener(this);
        signatureRel.setOnClickListener(this);
        album.setOnClickListener(this);
        camera.setOnClickListener(this);
        sure.setOnClickListener(this);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.head_image:
                intent = new Intent(PersonalInformationActivity.this, HeadActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_head:
                choiceHead.setVisibility(View.VISIBLE);
                break;
            case R.id.rel_wechat:
                intent = new Intent(PersonalInformationActivity.this, LotteryChatNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_myaddress:
                intent = new Intent(this, RegionActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_sex:
                object = new JSONObject();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setContentView(R.layout.alert_dialog);
                ((TextView) window.findViewById(R.id.tv_item1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        tvSex.setText("男");
                        try {
                            object.put("sex", "1");
                            object.put("id", getParam(PersonalInformationActivity.this, "id", ""));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog = WeiboDialogUtils.createLoadingDialog(PersonalInformationActivity.this, "修改中");
                        new ModifySex().execute();
                        alertDialog.cancel();
                    }
                });

                ((TextView) window.findViewById(R.id.tv_item2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        tvSex.setText("女");
                        try {
                            object.put("sex", "0");
                            object.put("id", getParam(PersonalInformationActivity.this, "id", ""));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dialog = WeiboDialogUtils.createLoadingDialog(PersonalInformationActivity.this, "修改中");
                        new ModifySex().execute();
                        alertDialog.cancel();
                    }
                });
                break;
            case R.id.rel_region:
                regionLay.setVisibility(View.VISIBLE);
                break;
            case R.id.rel_signature:
                intent = new Intent(this, PersonalizedSignatureActivity.class);
                intent.putExtra("signature", tvSignature.getText());
                startActivity(intent);
                break;
            case R.id.ral_xiangce:
                choiceHead.setVisibility(View.GONE);
                Intent it = new Intent(Intent.ACTION_PICK, null);
                it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(it, GALLER_REQUEST_CODE);
                break;
            case R.id.ral_xiangji:
                choiceHead.setVisibility(View.GONE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);// 拍照后保存的路径
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            case R.id.sure_region:
                regionLay.setVisibility(View.GONE);
                tvRegion.setText(mCurrentProviceName + mCurrentCityName);
                list = getCode(mCurrentProviceName,mCurrentCityName);
                object = new JSONObject();
                try {
                    object.put("provinceCode",list.get(0));
                    object.put("cityCode",list.get(1));
                    object.put("id", SharedPreferencesUtils.getParam(PersonalInformationActivity.this, "id", ""));
                } catch (JSONException e) {


                    e.printStackTrace();
                }
                new ModifySex().execute();
                SharedPreferencesUtils.setParam(PersonalInformationActivity.this, "provinceName", mCurrentProviceName);
                SharedPreferencesUtils.setParam(PersonalInformationActivity.this, "cityCodeName", mCurrentCityName);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        head.setImageBitmap(bitmap);
                        try {
                            uploadFile(saveBitmapFile(bitmap),uri);
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
        if (!file.exists()) {
            file.mkdirs();
        }
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

    private void updateAreas() {
        try {
            int pCurrent = mViewCity.getCurrentItem();
            // 将当前的市赋值给全局
            mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
                PersonalInformationActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(8);
        mViewCity.setVisibleItems(8);
        updateCities();
        updateAreas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        String signature = (String) SharedPreferencesUtils.getParam(PersonalInformationActivity.this,"signature","");
        tvSignature.setText("".equals(signature)?"空间主人太懒，什么都没有留下...":signature);
    }

    class ModifySex extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyUserInformation(object);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null) {
                return;
            }
            Gson gson = new Gson();
            PersonInformationBean bean = gson.fromJson(s, PersonInformationBean.class);
            if (!bean.getFlag()) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                WeiboDialogUtils.closeDialog(dialog);
            } else {
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
                SharedPreferencesUtils.setParam(PersonalInformationActivity.this, "sex", bean.getUserDto().getSex());
                WeiboDialogUtils.closeDialog(dialog);
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    showToastShort("修改失败");
                    break;
                case 2:
                    showToastShort("修改成功");
                    break;
            }
        }
    };

    private String uploadFile(File file, String url) throws Exception {
        if (file.exists()) {
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("file", file);
            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try {
                        String a = (String) getParam(PersonalInformationActivity.this,"lastTouXiang","");
                        String id = (String) getParam(PersonalInformationActivity.this,"id","");
                        String json = new String(bytes, "UTF-8");
                        JSONObject object = new JSONObject(json);
                        jsonObject = new JSONObject();
                        jsonObject.put("id",id);
                        jsonObject.put("lastTouXiang",a);
                        jsonObject.put("touXiang",object.get("id"));
                        new ModifyImage().execute();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }

                @Override
                public void onRetry(int retryNo) {
                    super.onRetry(retryNo);
                }
            });
        } else {
            Toast.makeText(PersonalInformationActivity.this,"文件不存在",Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    class ModifyImage extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getModifyUserInformation(jsonObject);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == null){
                return;
            }
            Gson gson = new Gson();
            PersonInformationBean bean = gson.fromJson(s,PersonInformationBean.class);
            SharedPreferencesUtils.setParam(PersonalInformationActivity.this,"lastTouXiang",bean.getUserDto().getTouXiang());
            SharedPreferencesUtils.setParam(PersonalInformationActivity.this,"touXiangUrl",bean.getUserDto().getTouXiangUrl());
            setResult(RESULT_OK);
            finish();
        }
    }
}