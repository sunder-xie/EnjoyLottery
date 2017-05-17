package com.enjoylottery.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    //private ArrayList<OpilistItem> lists;
    private ArrayList<Fragment> mList;
    private Context mContext;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mList = new ArrayList<Fragment>();
    }

    public void setLists(ArrayList<Fragment> lists) {
        this.mList = lists;
    }

    public void UpdateList(ArrayList<Fragment> arrayList) {
        this.mList.clear();
        this.mList.addAll(arrayList);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }
}
