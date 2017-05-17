package com.enjoylottery.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.enjoylottery.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FaxianFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private ImageView gouWuIv,zhanDianIv,saoIv;
    private TextView gouWuTv,zhanDianTv,saoTv;
    private RelativeLayout gouWuLay,zhanDianLay,saoLay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faxian_fragment,null,false);
        initView();
        return view;
    }

    public void initView(){
        ((TextView)view.findViewById(R.id.every_top_text)).setText("专 家");
        gouWuIv = (ImageView) view.findViewById(R.id.item_iv_faxian);
        zhanDianIv = (ImageView) view.findViewById(R.id.item_iv_faxian_fujinzhandian);
        saoIv = (ImageView) view.findViewById(R.id.item_iv_faxian_sao);
        gouWuTv = (TextView) view.findViewById(R.id.item_tv_faxian);
        zhanDianTv = (TextView) view.findViewById(R.id.item_tv_faxian_fujinzhandian);
        saoTv = (TextView) view.findViewById(R.id.item_tv_faxian_sao);
        gouWuLay = (RelativeLayout) view.findViewById(R.id.item_lay_faxian);
        zhanDianLay = (RelativeLayout) view.findViewById(R.id.item_lay_faxian_fujinzhandian);
        saoLay = (RelativeLayout) view.findViewById(R.id.item_lay_faxian_sao);
        gouWuLay.setOnClickListener(this);
        zhanDianLay.setOnClickListener(this);
        saoLay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_lay_faxian:

                break;
            case R.id.item_lay_faxian_fujinzhandian:

                break;
            case R.id.item_lay_faxian_sao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,1);
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
