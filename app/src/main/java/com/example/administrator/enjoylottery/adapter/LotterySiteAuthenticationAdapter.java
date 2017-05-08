package com.example.administrator.enjoylottery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.bean.CaiZhanRenZhengBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class LotterySiteAuthenticationAdapter extends BaseAdapter {
    private List<CaiZhanRenZhengBean> list;
    private Context context;

    public LotterySiteAuthenticationAdapter(Context context,List<CaiZhanRenZhengBean> list){
        this.list = list;
        this.context = context;
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
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_caizhanrenzheng,null);
            holder = new ViewHolder();
            holder.head = (ImageView) convertView.findViewById(R.id.item_caizhan_image);
            holder.shiFouRenZheng = (TextView) convertView.findViewById(R.id.shifourenzheng);
            holder.caiZhanType = (TextView) convertView.findViewById(R.id.caizhanleixing);
            holder.caiZhanNumber = (TextView) convertView.findViewById(R.id.zhandianhaoma);
            holder.reason = (TextView) convertView.findViewById(R.id.weitongguo_reason);
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.caizhanrenzheng_rel);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.caiZhanNumber.setText(list.get(position).getZhandianNumber());
        if (list.get(position).getShiFouTongguo() == 0){
            holder.shiFouRenZheng.setText("未通过");
            holder.reason.setVisibility(View.VISIBLE);
            holder.reason.setText("证件号码不匹配");
        }else {
            holder.shiFouRenZheng.setText("已认证");
            holder.reason.setVisibility(View.GONE);
        }
        if (list.get(position).getType() == 1){
            holder.head.setImageResource(R.drawable.fucaihead);
            holder.caiZhanType.setText("中国福利彩票");
            holder.relativeLayout.setBackgroundResource(R.drawable.yuanjiao_caizhan_molv);
        }else {
            holder.head.setImageResource(R.drawable.ticaihead);
            holder.caiZhanType.setText("中国体育彩票");
            holder.relativeLayout.setBackgroundResource(R.drawable.caizhanrenzheng_danhong);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView head;
        TextView caiZhanType,caiZhanNumber,shiFouRenZheng,reason;
        RelativeLayout relativeLayout;
    }
}
