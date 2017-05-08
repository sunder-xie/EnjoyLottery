package com.example.administrator.enjoylottery.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.adapter.GongjuAdapter;
import com.example.administrator.enjoylottery.bean.EffectiveProvinceBean;
import com.example.administrator.enjoylottery.bean.ProvincePlayBean;
import com.example.administrator.enjoylottery.view.MyExpandlerListview;
import com.example.administrator.enjoylottery.presenters.OKhttpHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class GongjuFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private TextView qieHuan;
    private MyExpandlerListview expandableListView;
    private GongjuAdapter adapter;
    private List<ProvincePlayBean> list = new ArrayList<>();
    private List<EffectiveProvinceBean> listCity = new ArrayList<>();
    private ScaleAnimation animation;
    private Boolean start = true;
    private int a = 0;
    private int pos = -2;
    private ListView listView;
    private LinearLayout layListView;
    private TextView sure;
    private String pCode;
    private List<String> listPname;

    public GongjuFragment() {}

    public GongjuFragment(List<ProvincePlayBean> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gongju_fragment, null, false);
        new getEffectiveProvince().execute();
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        ((TextView) view.findViewById(R.id.every_top_text)).setText("工 具");
        ((ImageView)view.findViewById(R.id.every_top_back)).setVisibility(View.GONE);
        listView = (ListView) view.findViewById(R.id.gongju_wheel);
        qieHuan = (TextView) view.findViewById(R.id.every_top_right);
        layListView = (LinearLayout) view.findViewById(R.id.lay_listView);
        qieHuan.setText("切换");
        sure = (TextView) view.findViewById(R.id.gongju_sure);
        expandableListView = (MyExpandlerListview) view.findViewById(R.id.gongju_expandable_listview);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = expandableListView.getExpandableListAdapter().getGroupCount();
                for (int j = 0; j < count; j++) {
                    if (j != groupPosition) {
                        expandableListView.collapseGroup(j);
                    }
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });
        expandableListView.setGroupIndicator(null);
        adapter = new GongjuAdapter(getActivity(), list);
        expandableListView.setAdapter(adapter);
        expandableListView.expandGroup(a);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (a == groupPosition){
                    return true;
                }
                a = groupPosition;
                return false;
            }
        });
        animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
    }

    private void initEvent() {
        qieHuan.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.every_top_right:
                layListView.setVisibility(View.VISIBLE);
                if (start) {
                    layListView.setAnimation(animation);
                    start = false;
                    animation.start();
                }
                break;
            case R.id.gongju_sure:
                if (pos == -2) {
                    Toast.makeText(getActivity(), "请选择一个省份", Toast.LENGTH_SHORT).show();
                    return;
                }
                pCode = listCity.get(pos).getPcode();
                layListView.setVisibility(View.GONE);
                start = true;
                new getProvincePlay().execute();
                break;
            default:
                break;
        }
    }

    //获取有效省份
    class getEffectiveProvince extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getEffectiveProvince();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Gson gson = new Gson();
                listCity = gson.fromJson(s, new TypeToken<ArrayList<EffectiveProvinceBean>>() {
                }.getType());
                listPname = new ArrayList<>();
                for (int a = 0; a < listCity.size(); a++) {
                    listPname.add(listCity.get(a).getPname());
                }
//                province = (String[])list.toArray(new String[list.size()]);
                MyAdapter adapter = new MyAdapter(listPname);
                listView.setAdapter(adapter);
            }
        }
    }

    class MyAdapter extends BaseAdapter {
        List<String> list;

        MyAdapter(List<String> list) {
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
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_gongju_listview, null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.listView_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(list.get(position));
            return convertView;
        }
    }

    class ViewHolder {
        TextView textView;
    }

    class getProvincePlay extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getPrivincePlay(pCode);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                a=0;
                list.clear();
                Gson gson = new Gson();
                list = gson.fromJson(s, new TypeToken<ArrayList<ProvincePlayBean>>() {
                }.getType());
                adapter = new GongjuAdapter(getActivity(), list);
                expandableListView.setAdapter(adapter);
                expandableListView.expandGroup(a);
            }
        }
    }
}
