package com.enjoylottery.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.enjoylottery.bean.ApplyGroupListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class SerchGroupAdapter extends BaseAdapter {
    private Context context;
    private List<ApplyGroupListBean> list;

    public SerchGroupAdapter(Context context,List<ApplyGroupListBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
