package com.byl.enjoylottery.model;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class Info {

    private static Info instance = new Info();

    private Info(){}

    public static Info getInstance() {
        return instance;
    }

    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
