package com.byl.enjoylottery.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class LotteryAuthenticationBean {

    /**
     * message : 获取成功
     * flag : true
     * stations : []
     */

    private String message;
    private boolean flag;
    private List<?> stations;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<?> getStations() {
        return stations;
    }

    public void setStations(List<?> stations) {
        this.stations = stations;
    }
}
