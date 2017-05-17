package com.enjoylottery.listener;


/**
 * Created by lz on 2017/4/8.
 * 权限组申请接口
 */
public interface PermissonsCallBack {
    public void ps_CallBack(String[] permissionNames,int result);     //result  = 1 成功,-1 失败,0已拥有
}