package com.enjoylottery.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.enjoylottery.adapter.GroupChartAdapter;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.activity.CreatGroupActivity;
import com.enjoylottery.activity.SearchGroupActivity;
import com.enjoylottery.bean.GroupListBean;
import com.enjoylottery.model.ActionItem;
import com.enjoylottery.presenters.OKhttpHelper;
import com.enjoylottery.tools.SharedPreferencesUtils;
import com.enjoylottery.view.TitlePopup;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;

import static com.example.administrator.enjoylottery.R.id.every_top_right;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class ChartFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private SwipeMenuListView chartList;
    private Intent intent;
    private TextView add;
    private TitlePopup titlePopup;
    private JSONObject object;
    private List<GroupListBean.GroupDtosBean> list = new ArrayList<>();
    private GroupChartAdapter adapter;
    private SwipeRefreshLayout refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chart_fragment, null, false);
        initView();
        initEvent();
        startChart();
        return view;
    }

    private void initView() {
        add = (TextView) view.findViewById(every_top_right);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.xiala_shuaxin);
        refresh.setRefreshing(true);
        object = new JSONObject();
        try {
            object.put("userId", SharedPreferencesUtils.getParam(getActivity(), "id", ""));
            new GetAddGroup().execute();
            ((TextView) view.findViewById(R.id.every_top_text)).setText("聊 天");
            chartList = (SwipeMenuListView) view.findViewById(R.id.group_chart_list);
            chartList.setMenuCreator(creator);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        chartList.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                refresh.setEnabled(false);
            }

            @Override
            public void onSwipeEnd(int position) {
                refresh.setEnabled(true);
            }
        });
        chartList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                                                 @Override
                                                 public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Toast.makeText(getActivity(), index + "", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 1:
                                                             Toast.makeText(getActivity(), index + "", Toast.LENGTH_SHORT).show();
                                                             break;
                                                     }
                                                     return false;
                                                 }
                                             }
        );
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(R.color.zitilv,R.color.zhuanjia,R.color.renzhengticai);
        refresh.setSize(SwipeRefreshLayout.LARGE);
//        refresh.setProgressBackgroundColor(R.color.colorAccent);
        add.setText("+");
        add.setTextSize(20);
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(getActivity(), LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    // 将dp转换为px
    private int dp2px(int value) {
        // 第一个参数为我们待转的数据的单位，此处为 dp（dip）
        // 第二个参数为我们待转的数据的值的大小
        // 第三个参数为此次转换使用的显示量度（Metrics），它提供屏幕显示密度（density）和缩放信息
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

    //另一种将dp转换为px的方法
    private int dp2px(float value) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    SwipeMenuCreator creator = new SwipeMenuCreator() {
        @Override
        public void create(SwipeMenu menu) {
            // 创建“置顶”项
            SwipeMenuItem openItema = new SwipeMenuItem(getActivity());
            openItema.setBackground(R.color.edithui);
            openItema.setWidth(dp2px(90));
            openItema.setTitle("置顶");
            openItema.setTitleSize(18);
            openItema.setTitleColor(R.color.wangjimima);
            // 将创建的菜单项添加进菜单中
            menu.addMenuItem(openItema);

            // 创建“屏蔽”项
            SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
            openItem.setBackground(R.color.red);
            openItem.setWidth(dp2px(90));
            openItem.setTitle("屏蔽");
            openItem.setTitleSize(18);
            openItem.setTitleColor(R.color.background_white);
            // 将创建的菜单项添加进菜单中
            menu.addMenuItem(openItem);
        }
    };

    private static int dp2px(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private void initEvent() {
        add.setOnClickListener(this);
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {

            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), SearchGroupActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intent, 1);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CreatGroupActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        //给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(getActivity(), "查找群", R.drawable.mm_title_btn_compose_normal));
        titlePopup.addAction(new ActionItem(getActivity(), "扫一扫", R.drawable.mm_title_btn_qrcode_normal));
        titlePopup.addAction(new ActionItem(getActivity(), "创建群", R.drawable.mm_title_btn_keyboard_normal));

    }

    private void startChart(){
        chartList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RongIM.getInstance().startGroupChat(getActivity(), list.get(position).getId(), list.get(position).getName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case every_top_right:
                titlePopup.show(v);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //result就是返回数据
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //用默认浏览器打开扫描得到的地址
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(result.toString());
                    intent.setData(content_url);
                    startActivity(intent);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        new GetAddGroup().execute();
    }

    //用户所加入的群
    class GetAddGroup extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getAddGroup(object);
        }

        @Override
        protected void onPostExecute(String s) {
            if (null == s) {
                return;
            }
            try {
                JSONObject objects = new JSONObject(s);
                if (objects.getJSONArray("groupDtos").length() == 0) {
                    return;
                }
                list.clear();
                Gson gson = new Gson();
                GroupListBean bean = gson.fromJson(s, GroupListBean.class);
                list.addAll(bean.getGroupDtos());
                adapter = new GroupChartAdapter(getActivity(), list);
                chartList.setAdapter(adapter);
                refresh.setRefreshing(false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}