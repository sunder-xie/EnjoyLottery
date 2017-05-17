package com.byl.enjoylottery.listener;

/**
 * Created by lz on 2016/12/22.
 * 权限获取成功回调接口
 */
public interface PermissonCallBack {
    public void p_CallBack(String permissionName, int result);     //result  = 1 成功,-1 失败,0已拥有
}
