package com.example.administrator.enjoylottery.view.trendtype_11s5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.enjoylottery.bean.BallBean;
import com.example.administrator.enjoylottery.service.MapApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by lz on 2017/5/12.
 */

public class MainBottomView extends View {
    int column = 12;
    float viewWidth, viewHeight, gridWidth, gridHeight;
    List<BallBean> list = MapApplication.getInstence().getList();
    int a, b, c, d, e, f, g, h, j, k, l;
    int[] nums = new int[11];

    public MainBottomView(Context context) {
        super(context);
        initNum();
    }

    //获取出现次数（以下为弱智代码,请勿模仿）
    private void initNum() {

        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String sysDate = formatter.format(date);

        for (BallBean bean : list) {
            if (sysDate.equals(bean.getIssueNumber().substring(0, 6))) {
                switch (bean.getNo1()) {
                    case 1:
                        a++;
                        break;
                    case 2:
                        b++;
                        break;
                    case 3:
                        c++;
                        break;
                    case 4:
                        d++;
                        break;
                    case 5:
                        e++;
                        break;
                    case 6:
                        f++;
                        break;
                    case 7:
                        g++;
                        break;
                    case 8:
                        h++;
                        break;
                    case 9:
                        j++;
                        break;
                    case 10:
                        k++;
                        break;
                    case 11:
                        l++;
                        break;
                }
                switch (bean.getNo2()) {
                    case 1:
                        a++;
                        break;
                    case 2:
                        b++;
                        break;
                    case 3:
                        c++;
                        break;
                    case 4:
                        d++;
                        break;
                    case 5:
                        e++;
                        break;
                    case 6:
                        f++;
                        break;
                    case 7:
                        g++;
                        break;
                    case 8:
                        h++;
                        break;
                    case 9:
                        j++;
                        break;
                    case 10:
                        k++;
                        break;
                    case 11:
                        l++;
                        break;
                }
                switch (bean.getNo3()) {
                    case 1:
                        a++;
                        break;
                    case 2:
                        b++;
                        break;
                    case 3:
                        c++;
                        break;
                    case 4:
                        d++;
                        break;
                    case 5:
                        e++;
                        break;
                    case 6:
                        f++;
                        break;
                    case 7:
                        g++;
                        break;
                    case 8:
                        h++;
                        break;
                    case 9:
                        j++;
                        break;
                    case 10:
                        k++;
                        break;
                    case 11:
                        l++;
                        break;
                }
                switch (bean.getNo4()) {
                    case 1:
                        a++;
                        break;
                    case 2:
                        b++;
                        break;
                    case 3:
                        c++;
                        break;
                    case 4:
                        d++;
                        break;
                    case 5:
                        e++;
                        break;
                    case 6:
                        f++;
                        break;
                    case 7:
                        g++;
                        break;
                    case 8:
                        h++;
                        break;
                    case 9:
                        j++;
                        break;
                    case 10:
                        k++;
                        break;
                    case 11:
                        l++;
                        break;
                }
                switch (bean.getNo5()) {
                    case 1:
                        a++;
                        break;
                    case 2:
                        b++;
                        break;
                    case 3:
                        c++;
                        break;
                    case 4:
                        d++;
                        break;
                    case 5:
                        e++;
                        break;
                    case 6:
                        f++;
                        break;
                    case 7:
                        g++;
                        break;
                    case 8:
                        h++;
                        break;
                    case 9:
                        j++;
                        break;
                    case 10:
                        k++;
                        break;
                    case 11:
                        l++;
                        break;
                }
            }
            nums[0] = a;
            nums[1] = b;
            nums[2] = c;
            nums[3] = d;
            nums[4] = e;
            nums[5] = f;
            nums[6] = g;
            nums[7] = h;
            nums[8] = j;
            nums[9] = k;
            nums[10] = l;
        }
    }


    public MainBottomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initNum();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        //抗锯齿处理
        PaintFlagsDrawFilter drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | 3);
        canvas.setDrawFilter(drawFilter);
        mPaint.setAntiAlias(true);
        //画背景
        mPaint.setColor(Color.parseColor("#085a8c"));
        canvas.drawRect(0, 0, viewWidth, gridHeight, mPaint);
        //竖线
        mPaint.setColor(Color.WHITE);
        for (int i = 0; i < column; i++) {
            canvas.drawLine(gridWidth * i, 0, gridWidth * i, gridHeight, mPaint);
        }


        // 第一步 画矩形
        RectF rectF = new RectF(0, 0, gridWidth, gridHeight);
        // 第二步 写字
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(25);
        // 获取文字矩形中心点 设置文字居中
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        //写字
        canvas.drawText("次", rectF.centerX(), baseline, mPaint);

        //写数字
        for (int i = 1; i < 12; i++) {
            // 第一步 画矩形
            rectF = new RectF(gridWidth * i, 0, gridWidth * i + gridWidth, gridHeight);
            // 第二步 写字
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(25);
            // 获取文字矩形中心点 设置文字居中
            fontMetrics = mPaint.getFontMetricsInt();
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(nums[i - 1] + "", rectF.centerX(), baseline, mPaint);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 12;  //12列
        gridHeight = viewWidth / 12;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight));
    }
}
