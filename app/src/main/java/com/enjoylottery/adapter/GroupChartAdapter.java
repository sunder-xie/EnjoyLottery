package com.enjoylottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.enjoylottery.bean.GroupListBean;
import com.enjoylottery.presenters.OKhttpHelper;

import java.util.ArrayList;
import java.util.List;

import io.rong.imageloader.core.ImageLoader;


/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class GroupChartAdapter extends BaseAdapter {

    private Context context;
    private List<GroupListBean.GroupDtosBean> list;
    private List<ImageView> listImage = new ArrayList<>();

    public GroupChartAdapter(Context context, List<GroupListBean.GroupDtosBean> list) {
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
        ViewHolder holder = null;
        if (position + 1 > listImage.size()) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_list, null, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(com.example.administrator.enjoylottery.R.id.group_name);
            holder.introduction = (TextView) convertView.findViewById(R.id.group_jianjie);
            holder.head = (ImageView) convertView.findViewById(R.id.group_head);
            holder.store = (ImageView) convertView.findViewById(R.id.group_dian);
            holder.name.setText(list.get(position).getName());
            holder.introduction.setText(list.get(position).getIntroduction());
            ImageLoader.getInstance().displayImage(OKhttpHelper.getInstance().DOMAIN +list.get(position).getTouXiangImgUrl(), holder.head);
            listImage.add(holder.head);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
            holder.head = listImage.get(position);
        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView introduction;
        ImageView head;
        ImageView store;
    }
}
