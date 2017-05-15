package com.example.administrator.enjoylottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.enjoylottery.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class FuntionConfigurationFragment extends BaseFragment implements View.OnClickListener{

    private View view;

    private TextView free,verification,releaseLottery,releaseTrend,realMissing,realExpert,realLottery;

    public int mode = 2;

    public int relLottery = 0;
    public int relTrend = 0;
    public int realMiss = 0;
    public int realExp = 0;
    public int realLot = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancetate) {
        view = inflater.inflate(R.layout.fragment_funtion_configuration,null,false);
        initView();
        initEvent();
        return view;
    }

    private void initView(){
        free = (TextView) view.findViewById(R.id.group_chart_free);
        verification = (TextView) view.findViewById(R.id.group_chart_verification);
        releaseLottery = (TextView) view.findViewById(R.id.fabu_kaijiang_huamian);
        releaseTrend = (TextView) view.findViewById(R.id.fabu_zoushi_tuji);
        realMissing = (TextView) view.findViewById(R.id.shishi_yilou_chaxun);
        realExpert = (TextView) view.findViewById(R.id.shishi_zhuanjia_chaxun);
        realLottery = (TextView) view.findViewById(R.id.shishi_kaijiang_chaxun);
    }

    private void initEvent(){
        free.setOnClickListener(this);
        verification.setOnClickListener(this);
        releaseLottery.setOnClickListener(this);
        releaseTrend.setOnClickListener(this);
        realMissing.setOnClickListener(this);
        realExpert.setOnClickListener(this);
        realLottery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.group_chart_free:
                free.setTextColor(getResources().getColor(R.color.zitilv));
                free.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                verification.setTextColor(getResources().getColor(R.color.black));
                verification.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                mode = 0;
                break;
            case R.id.group_chart_verification:
                verification.setTextColor(getResources().getColor(R.color.zitilv));
                verification.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                free.setTextColor(getResources().getColor(R.color.black));
                free.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                mode = 1;
                break;
            case R.id.fabu_kaijiang_huamian:
                if (relLottery == 0) {
                    releaseLottery.setTextColor(getResources().getColor(R.color.zitilv));
                    releaseLottery.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                    relLottery = 1;
                }else {
                    releaseLottery.setTextColor(getResources().getColor(R.color.black));
                    releaseLottery.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                    relLottery = 0;
                }
                break;
            case R.id.fabu_zoushi_tuji:
                if (relTrend == 0) {
                    releaseTrend.setTextColor(getResources().getColor(R.color.zitilv));
                    releaseTrend.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                    relTrend = 1;
                }else {
                    releaseTrend.setTextColor(getResources().getColor(R.color.black));
                    releaseTrend.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                    relTrend = 0;
                }
                break;
            case R.id.shishi_yilou_chaxun:
                if (realMiss == 0) {
                    realMissing.setTextColor(getResources().getColor(R.color.zitilv));
                    realMissing.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                    realMiss = 1;
                }else {
                    realMissing.setTextColor(getResources().getColor(R.color.black));
                    realMissing.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                    realMiss = 0;
                }
                break;
            case R.id.shishi_zhuanjia_chaxun:
                if (realExp == 0) {
                    realExpert.setTextColor(getResources().getColor(R.color.zitilv));
                    realExpert.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));
                    realExp = 1;
                }else {
                    realExpert.setTextColor(getResources().getColor(R.color.black));
                    realExpert.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                    realExp = 0;
                }
                break;
            case R.id.shishi_kaijiang_chaxun:
                if (realLot == 0) {
                    realLottery.setTextColor(getResources().getColor(R.color.zitilv));
                    realLottery.setBackground(getResources().getDrawable(R.drawable.select_hong_fangkuang));;
                    realLot = 1;
                }else {
                    realLottery.setTextColor(getResources().getColor(R.color.black));
                    realLottery.setBackground(getResources().getDrawable(R.drawable.seletor_fangkuang));
                    realLot = 0;
                }
                break;
            default:
                break;
        }
    }

    public Boolean getNext(){
        if (mode!=2&&(relLottery != 0||relTrend != 0||realMiss != 0||realExp != 0||realLot != 0)){
            return true;
        }
        return false;
    }
}
