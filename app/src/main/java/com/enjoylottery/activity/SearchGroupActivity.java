package com.enjoylottery.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.enjoylottery.bean.ApplyGroupListBean;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.adapter.SerchGroupAdapter;
import com.enjoylottery.model.ActionItem;
import com.enjoylottery.presenters.OKhttpHelper;
import com.enjoylottery.view.TitlePopup;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class SearchGroupActivity extends BaseActivity {

    private ListView listView;
    private TextView checkClass,sure;
    private TitlePopup titlePopup;
    private String key;
    private JSONObject object;
    private EditText editText;
    private SerchGroupAdapter adapter;
    private List<ApplyGroupListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);
        initView();
        initEvent();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.apply_group);
        checkClass = (TextView) findViewById(R.id.group_sousuo_leixing);
        editText = (EditText) findViewById(R.id.sousuo_content);
        sure = (TextView) findViewById(R.id.sousuo_queding);
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {

            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position) {
                    case 0:
                        key = "province";
                        checkClass.setText("省份名称");
                        editText.setHint("请输入省份名称");
                        break;
                    case 1:
                        key = "city";
                        checkClass.setText("城市名称");
                        editText.setHint("请输入城市名称");
                        break;
                    case 2:
                        key = "lotteryType";
                        checkClass.setText("彩种类型");
                        editText.setHint("请输入体彩/福彩/竞彩");
                        break;
                    case 3:
                        key = "id";
                        checkClass.setText("群       号");
                        editText.setHint("请输入群号");
                        break;
                    case 4:
                        key = "name";
                        checkClass.setText("群       名");
                        editText.setHint("请输入群名");
                        break;
                }
            }
        });
        titlePopup.addAction(new ActionItem(this, "省份名称"));
        titlePopup.addAction(new ActionItem(this, "城市名称"));
        titlePopup.addAction(new ActionItem(this, "彩种类型"));
        titlePopup.addAction(new ActionItem(this, "群       号"));
        titlePopup.addAction(new ActionItem(this, "群       名"));
    }

    private void initEvent(){
        checkClass.setOnClickListener(this);
        sure.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.group_sousuo_leixing:
                titlePopup.show(v);
                break;
            case R.id.sousuo_queding:
                if ("".equals(editText.getText().toString().trim())){
                    showToastShort("搜索内容为空");
                    return;
                }
                object = new JSONObject();
                try {
                    object.put(key,editText.getText().toString());
                    new QueryGroupList().execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    class QueryGroupList extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... params) {
            return OKhttpHelper.getInstance().getQueryGroup(object);
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null){
                return;
            }
            try {
                JSONObject objects = new JSONObject(s);
                if (objects.getJSONArray("groupDtos").length() == 0) {
                    return;
                }
                list.clear();
                Gson gson = new Gson();
                ApplyGroupListBean bean = gson.fromJson(s, ApplyGroupListBean.class);
               // list.addAll(bean.getGroupDtos());
               // adapter = new GroupChartAdapter(this, list);
               // listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
