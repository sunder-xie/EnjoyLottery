package com.example.administrator.enjoylottery.presenters;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 网络请求类
 */
public class OKhttpHelper {
    private static OKhttpHelper instance = null;
    private int result;

    private static final String map = "http://api.map.baidu.com/geocoder?";
    public static final String DOMAIN = "http://192.168.1.126/webappProject/";
//    public static final String DOMAIN = "http://36.7.190.20:1881/webappProject/";
    //球号
    private static final String GET_BALL = DOMAIN+"outer/getLotteryList?id=4028b8815b13e2bb015b13fad49a0001";
    //获取省份对应的玩法数据
    private static final String GET_PLAY_TYPE = DOMAIN + "outer/getLotteryPlayListOfProvince?";
    //获取切换省份的有效省份
    private static final String GET_EFFECTIVE_PROVINCE = DOMAIN + "outer/getProvinceOfZST";
    //用户注册
    private static final String Register = DOMAIN + "outerLbuyerOrexpert/saveFromApp?";
    //获取验证码
    private static final String VerificationCode = DOMAIN + "outerLbuyerOrexpert/getYanzhengmaForRegister?";
    //登录
    private static final String LOGIN = DOMAIN + "outerLbuyerOrexpert/login?";
    //修改密码前校验原密码接口
    private static final String MODIFY_OLD_PWD = DOMAIN + "outerLbuyerOrexpert/checkPassword?";
    //原密码修改成功，调用修改密码接口
    private static final String MODIFY_NEW_PWD = DOMAIN + "outerLbuyerOrexpert/updatePassword?";
    //修改用户信息接口
    private static final String MODIFY_USER_INFORMATION = DOMAIN + "outerLbuyerOrexpert/updateUser?";
    //获取认证的材料站接口
    private static final String GET_HAVE_LOTTERY = DOMAIN + "outerLotteryStation/getLotteryStaionOfRenzheng?";
    //创建群
    private static final String ESDABLISH_GROUP = DOMAIN + "createGroup?";

    public static OKhttpHelper getInstance() {
        if (instance == null) {
            instance = new OKhttpHelper();
        }
        return instance;
    }

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();

    //Http请求方式：get
    @Nullable
    private String get(String url, String json) throws IOException {
        String key = "";
        String value = "";
        StringBuilder buffer = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            buffer = new StringBuilder(url);
            while (iterator.hasNext()) {
                buffer.append("&");
                key = (String) iterator.next();
                buffer.append(key).append("=");
                value = jsonObject.getString(key);
                buffer.append(value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request request = new Request.Builder().url(String.valueOf(buffer)).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else {
            return "";
        }
    }

    public String getMap(String address, String output, String key, String city) {
        try {
            JSONObject object = new JSONObject();
            object.put("address", address);
            object.put("output", output);
            object.put("key", key);
            object.put("city", city);
            String objets = OKhttpHelper.getInstance().get(map, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objets);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取省份对应的玩法数据
    public String getPrivincePlay(String provinceCode) {
        try {
            JSONObject object = new JSONObject();
            object.put("provinceCode", provinceCode);
            String objects = OKhttpHelper.getInstance().get(GET_PLAY_TYPE, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONArray result = (JSONArray) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取有效省份
    public String getEffectiveProvince() {
        try {
            JSONObject object = new JSONObject();
            String objects = OKhttpHelper.getInstance().get(GET_EFFECTIVE_PROVINCE, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONArray result = (JSONArray) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //用户注册
    public String getRegister(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(Register, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取验证码
    public String getVerificationCode(String telephone) {
        try {
            JSONObject object = new JSONObject();
            object.put("telephone", telephone);
            String objects = OKhttpHelper.getInstance().get(VerificationCode, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //登录
    public String login(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(LOGIN, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改密码前校验原密码
    public String getModifyOldPwd(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(MODIFY_OLD_PWD, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //原密码修改成功，调用修改密码接口
    public String getModifyNewPwd(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(MODIFY_NEW_PWD, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改用户信息接口
    public String getModifyUserInformation(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(MODIFY_USER_INFORMATION, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取用户认证的彩票站接口
    public String getHaveLottery(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(GET_HAVE_LOTTERY, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取用户认证的彩票站接口
    public String getEsdablishGroup(JSONObject object) {
        try {
            String objects = OKhttpHelper.getInstance().get(ESDABLISH_GROUP, object.toString());
            JSONTokener jsonTokener = new JSONTokener(objects);
            JSONObject result = (JSONObject) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //获取球
    public String get11f5Ball() {
        try {
            String json = OKhttpHelper.getInstance().get(GET_BALL,"{}");
            JSONTokener jsonTokener = new JSONTokener(json);
            JSONArray result = (JSONArray) jsonTokener.nextValue();
            return result + "";
        } catch (JSONException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
