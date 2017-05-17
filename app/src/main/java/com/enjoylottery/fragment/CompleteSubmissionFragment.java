package com.enjoylottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoylottery.activity.CreatGroupActivity;
import com.enjoylottery.tools.SharedPreferencesUtils;
import com.example.administrator.enjoylottery.R;
import com.enjoylottery.presenters.OKhttpHelper;

import io.rong.imageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class CompleteSubmissionFragment extends BaseFragment {

    private View view;
    private TextView groupNumber,share;
    private ImageView qrCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complete_submission,null,false);
        initView();
        return view;
    }

    private void initView(){
        groupNumber = (TextView) view.findViewById(R.id.group_number);
        qrCode = (ImageView) view.findViewById(R.id.qr_code);
        share = (TextView) view.findViewById(R.id.fenxiang_qr_code);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去微信分享二维码
            }
        });
    }

    public void setGroupInformation(){
        if (null!=((CreatGroupActivity)getActivity()).bean){
            groupNumber.setText(((CreatGroupActivity)getActivity()).bean.getGroup().getGroupNumber());
            ImageLoader.getInstance().displayImage(OKhttpHelper.DOMAIN + ((CreatGroupActivity)getActivity()).bean.getGroup().getGroupQRImg(),qrCode);
            SharedPreferencesUtils.getParam(getActivity(),"qrCodeOne",OKhttpHelper.DOMAIN + ((CreatGroupActivity)getActivity()).bean.getGroup().getGroupQRImg());
        }
    }
}
