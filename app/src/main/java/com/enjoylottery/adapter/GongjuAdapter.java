package com.enjoylottery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enjoylottery.activity.ZoushituHomeActivity;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.bean.ProvincePlayBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class GongjuAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ProvincePlayBean> list;

    public GongjuAdapter(Context context, List<ProvincePlayBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.size() == 0 ? 0 : 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gongju_group, null, false);
            holder = new GroupHolder();
            holder.head = (ImageView) convertView.findViewById(R.id.item_iv_faxian_eleven);
            holder.tc = (TextView) convertView.findViewById(R.id.item_tv_faxian_eleven);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        if (list.size()!=0) {
            holder.tc.setText(list.get(groupPosition).getLotteryTypeName() + "高频" + "-" + list.get(groupPosition).getLotteryPlayName());
        }
            return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gongju_child, null, false);
            childHolder = new ChildHolder();
            childHolder.horizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.eleven_five_horizontal_scrollview);
            childHolder.zouShitu = (LinearLayout) convertView.findViewById(R.id.gongju_zoushitu);
            childHolder.yiLou = (LinearLayout) convertView.findViewById(R.id.gongju_yilou);
            convertView.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.zouShitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupPosition == 0){
                    Intent intent = new Intent(context, ZoushituHomeActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder {
        ImageView head;
        TextView tc;
    }

    class ChildHolder {
        HorizontalScrollView horizontalScrollView;
        LinearLayout zouShitu, yiLou;
    }
}
