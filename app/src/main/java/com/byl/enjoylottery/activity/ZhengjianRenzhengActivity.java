package com.byl.enjoylottery.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.byl.enjoylottery.view.WeiboDialogUtils;
import com.example.administrator.enjoylottery.R;
import com.byl.enjoylottery.tools.IDCardValidate;
import com.byl.enjoylottery.tools.SharedPreferencesUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class ZhengjianRenzhengActivity extends BaseActivity implements TextWatcher {
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLER_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 3;
    private LinearLayout linearLayout;
    private RelativeLayout xiangJiRel, xiangCeRel;
    private ImageView idCardZheng, idCardBack, fucaiImg;
    private int a;
    private Boolean go = false;
    private Uri uritempFile;
    private EditText userName, idCard;
    private File idzhengPh, idbeiPh, fcPh;
    private TextView next;
    private Bundle bundle;
    private Uri mImageUri = Uri.fromFile(new File("/sdcard/card001.jpg"));
    private static ZhengjianRenzhengActivity activity;
    private String lotteryType;
    private String stationNumber;
    private String province;
    private String city;
    private String country;
    private String address;
    private String coordinate;
    private String isBylStation;
    private String bylStationCode;
    private Dialog mWeiboDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhengjianrenzheng);
        initView();
        initEvent();
        activity = this;
    }

    private void initView() {
        ((TextView) findViewById(R.id.every_top_text)).setText("彩站注册");
        ((TextView) findViewById(R.id.tianxie_ziliao)).setTextColor(getResources().getColor(R.color.zitilv));
        ((ImageView) findViewById(R.id.jiantou_one)).setImageResource(R.drawable.yuanjiantoulv);
        ((TextView) findViewById(R.id.zhengjian_renzheng)).setTextColor(getResources().getColor(R.color.zitilv));
        linearLayout = (LinearLayout) findViewById(R.id.xiangce_xiangji);
        idCardZheng = (ImageView) findViewById(R.id.idcard_zhengmian);
        idCardBack = (ImageView) findViewById(R.id.idcard_beimian);
        xiangCeRel = (RelativeLayout) findViewById(R.id.ral_xiangce);
        xiangJiRel = (RelativeLayout) findViewById(R.id.ral_xiangji);
        fucaiImg = (ImageView) findViewById(R.id.fucai_daixiao);
        userName = (EditText) findViewById(R.id.renzheng_username);
        idCard = (EditText) findViewById(R.id.renzheng_idcard);
        next = (TextView) findViewById(R.id.ziliao_xiayibu);
        bundle = getIntent().getExtras();
        lotteryType = bundle.getString("lotteryType");
        stationNumber = bundle.getString("stationNumber");
        address = bundle.getString("address");
        coordinate = bundle.getString("coordinate");//经纬度
        isBylStation = bundle.getString("isBylStation");
        bylStationCode = bundle.getString("bylStationCode");
        province = bundle.getStringArrayList("everyCode").get(0);
        city = bundle.getStringArrayList("everyCode").get(1);
        country = bundle.getStringArrayList("everyCode").get(2);
        uritempFile = Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/" + "big.jpg");
    }

    public void initEvent() {
        idCardZheng.setOnClickListener(this);
        idCardBack.setOnClickListener(this);
        fucaiImg.setOnClickListener(this);
        xiangCeRel.setOnClickListener(this);
        xiangJiRel.setOnClickListener(this);
        userName.addTextChangedListener(this);
        idCard.addTextChangedListener(this);
        next.setOnClickListener(this);
    }

    private void nextGo() {
        if (!userName.getText().toString().trim().equals("") && !idCard.getText().toString().trim().equals("") && idzhengPh != null && idbeiPh != null && fcPh != null) {
            next.setBackgroundResource(R.drawable.dianjizhuce);
            go = true;
        } else {
            next.setBackgroundResource(R.color.edithui);
            go = false;
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ziliao_xiayibu:
                String idcard = new IDCardValidate().validate_effective(idCard.getText().toString().trim());
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(idcard);
                if(!isNum.matches()){
                    showToastShort("身份证号码输入不正确");
                    return;
                }
                if (go) {
                    Intent intent = new Intent(ZhengjianRenzhengActivity.this, ZhanghuZhuceActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.idcard_zhengmian:
                hideInputMethod();
                linearLayout.setVisibility(View.VISIBLE);
                a = 0;
                break;
            case R.id.idcard_beimian:
                hideInputMethod();
                linearLayout.setVisibility(View.VISIBLE);
                a = 1;
                break;
            case R.id.fucai_daixiao:
                hideInputMethod();
                linearLayout.setVisibility(View.VISIBLE);
                a = 2;
                break;
            case R.id.ral_xiangce:
                linearLayout.setVisibility(View.GONE);
                Intent it = new Intent(Intent.ACTION_PICK, null);
                it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(it, GALLER_REQUEST_CODE);
                break;
            case R.id.ral_xiangji:
                linearLayout.setVisibility(View.GONE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);// 拍照后保存的路径
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 16);
        intent.putExtra("aspectY", 9);
        intent.putExtra("outPut", 290);    //裁剪输出宽高
        intent.putExtra("outPutY", 160);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CROP_REQUEST_CODE);
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
                        if (a == 0) {
                            idCardZheng.setImageBitmap(bitmap);
                            idzhengPh = saveBitmapFile(bitmap,"000.jpg");
                            nextGo();
                        } else if (a == 1) {
                            idCardBack.setImageBitmap(bitmap);
                            idbeiPh = saveBitmapFile(bitmap, "001.jpg");
                            nextGo();
                        } else if (a == 2) {
                            fucaiImg.setImageBitmap(bitmap);
                            fcPh = saveBitmapFile(bitmap, "002.jpg");
                            nextGo();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public File saveBitmapFile(Bitmap bitmap, String id) {
        File file = new File(Environment.getExternalStorageDirectory() + "/mnt/sdcard/" + id);//将要保存图片的路径
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!userName.getText().toString().trim().equals("")) {
            String name = userName.getText().toString().trim();
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            for (int a = 0; a < name.length(); a++) {
                String str = name.substring(a, a + 1);
                Matcher m = p.matcher(str);
                if (!m.matches()) {
                    Toast.makeText(ZhengjianRenzhengActivity.this, "请输入汉字", Toast.LENGTH_SHORT).show();
                    userName.setText(null);
                    break;
                }
            }
        }
        nextGo();
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public String uploadFile(String phone,String yuanzheng,String caidianName) throws Exception {
        if (fcPh.exists()&&idzhengPh.exists()&&idbeiPh.exists()) {
            String url = "http://192.168.1.126/webappProject/outerLotteryStation/submitFromAppLotteryStaion";
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("daixiaoImgFile", fcPh);
            params.put("idNumberFrontImgFile", idzhengPh);
            params.put("idNumberBackImgFile", idbeiPh);
            params.put("telephone", phone);
            params.put("yanzhengma", yuanzheng);
            params.put("stationName", caidianName);
            params.put("code", userName.getText().toString().trim());
            params.put("idNumber", idCard.getText().toString().trim());
            params.put("userId", SharedPreferencesUtils.getParam(ZhengjianRenzhengActivity.this,"id",""));
            params.put("lotteryType", lotteryType);
            params.put("stationNumber", stationNumber);
            params.put("province", province);
            params.put("city", city);
            params.put("country", country);
            params.put("address", address);
            params.put("coordinate", coordinate);
            params.put("isBylStation", isBylStation);
            if (isBylStation.equals("1")) {
                params.put("bylStationCode", bylStationCode);
            }
            // 上传文件
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try {
                        String json = new String(bytes, "UTF-8");
                        WeiboDialogUtils.closeDialog(mWeiboDialog);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                }

                @Override
                public void onRetry(int retryNo) {
                    super.onRetry(retryNo);
                }
            });
        } else {
            Toast.makeText(ZhengjianRenzhengActivity.this,"文件不存在",Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    public static ZhengjianRenzhengActivity getContext(){
        return activity;
    }

    public void getA(String phone,String yanzhengma,String caidianming){
        try {
            mWeiboDialog = WeiboDialogUtils.createLoadingDialog(ZhengjianRenzhengActivity.this, "加载中...");
            uploadFile(phone,yanzhengma,caidianming);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
