package com.example.administrator.enjoylottery.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.activity.ViewActivity;
import com.example.administrator.enjoylottery.bean.ElevenFivePictureBean;
import com.example.administrator.enjoylottery.listener.CheckMuban;
import com.example.administrator.enjoylottery.listener.ItemTouchHelperAdapter;
import com.example.administrator.enjoylottery.listener.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ChangyongZoushiAdapter extends RecyclerView.Adapter<ChangyongZoushiAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    private Context context;
    private List<ElevenFivePictureBean> list;
    private CheckMuban checkMuban;
    private Boolean aBoolean;

    public ChangyongZoushiAdapter(Context context,List<ElevenFivePictureBean> list, Boolean aBoolean){
        this.context = context;
        this.list = list;
        this.aBoolean = aBoolean;
    }

    public void setCheckMuban(CheckMuban checkMuban){
        this.checkMuban = checkMuban;
    }

    @Override
    public ChangyongZoushiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridview_elevenfive,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChangyongZoushiAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getPicture());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.startActivity(new Intent(context, ViewActivity.class));
            }
        });
        holder.textView.setText(list.get(position).getName());
        holder.check.setText("-");
        holder.check.setTag(position);
        holder.check.setOnClickListener(listener);
        if (!aBoolean){
            holder.check.setVisibility(View.GONE);
        }else {
            holder.check.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView,check;
        public ViewHolder(View itemView) {
            super(itemView);
             relativeLayout = (RelativeLayout) itemView.findViewById(R.id.lay_gridview);
             imageView = (ImageView) itemView.findViewById(R.id.muban_muban);
             textView = (TextView) itemView.findViewById(R.id.muban_name);
             check = (TextView) itemView.findViewById(R.id.denglu_quxiao_shoujihao);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkMuban!=null){
                v.setVisibility(View.VISIBLE);
                checkMuban.appear(v, (Integer) v.getTag());
            }
        }
    };

    private String mubanName(int position){
        String a;
        if (position < 9){
            a = "00" + (position + 1);
        }else {
            a = "0" + (position + 1);
        }
        return a;
    }

    public void getCheck(Boolean aBoolean){
        this.aBoolean = aBoolean;
    }
}
