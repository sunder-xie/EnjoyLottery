package com.example.administrator.enjoylottery.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;
import com.example.administrator.enjoylottery.activity.CreatGroupActivity;
import com.example.administrator.enjoylottery.model.ActionItem;
import com.example.administrator.enjoylottery.view.TitlePopup;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import static com.example.administrator.enjoylottery.R.id.every_top_right;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class ChartFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private ListView chartList;
    private Intent intent;
    private TextView add;
    private TitlePopup titlePopup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chart_fragment, null, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        ((TextView) view.findViewById(R.id.every_top_text)).setText("聊 天");
        chartList = (ListView) view.findViewById(R.id.group_chart_list);
        add = (TextView) view.findViewById(every_top_right);
        add.setText("+");
        add.setTextSize(20);
        //实例化标题栏弹窗
        titlePopup = new TitlePopup(getActivity(), LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    private void initEvent(){
        add.setOnClickListener(this);
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {

            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        intent = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intent,1);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), CreatGroupActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        //给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(getActivity(), "查找群  ", R.drawable.mm_title_btn_compose_normal));
        titlePopup.addAction(new ActionItem(getActivity(), "扫一扫  ", R.drawable.mm_title_btn_qrcode_normal));
        titlePopup.addAction(new ActionItem(getActivity(), "创建群", R.drawable.mm_title_btn_keyboard_normal));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
}