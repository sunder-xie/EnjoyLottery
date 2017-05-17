package com.byl.enjoylottery.view.trendtype_11s5.style2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.byl.enjoylottery.bean.BallBean;
import com.byl.enjoylottery.service.MapApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by lz on 2017/5/11.
 */

public class ViceBottomView extends View {
    int column = 33;
    float viewWidth, viewHeight, gridWidth, gridHeight;
    List<BallBean> list = MapApplication.getInstence().getList();
    int[] num1 = new int[11];
    int[] num2 = new int[11];
    int[] num3 = new int[11];
    int a, b, c, d, e, f, g, h, i, j, k;

    private void initNum() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String sysDate = formatter.format(date);

        for (BallBean bean : list) {
            if (bean.getIssueNumber().substring(0, 6).equals(sysDate)) {
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
                        i++;
                        break;
                    case 10:
                        j++;
                        break;
                    case 11:
                        k++;
                        break;
                }
            }
            num1[0] = a;
            num1[1] = b;
            num1[2] = c;
            num1[3] = d;
            num1[4] = e;
            num1[5] = f;
            num1[6] = g;
            num1[7] = h;
            num1[8] = i;
            num1[9] = j;
            num1[10] = k;
        }
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
        i = 0;
        j = 0;
        k = 0;

        for (BallBean bean : list) {
            if (bean.getIssueNumber().substring(0, 6).equals(sysDate)) {
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
                        i++;
                        break;
                    case 10:
                        j++;
                        break;
                    case 11:
                        k++;
                        break;
                }
            }
            num2[0] = a;
            num2[1] = b;
            num2[2] = c;
            num2[3] = d;
            num2[4] = e;
            num2[5] = f;
            num2[6] = g;
            num2[7] = h;
            num2[8] = i;
            num2[9] = j;
            num2[10] = k;
        }
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
        i = 0;
        j = 0;
        k = 0;
        for (BallBean bean : list) {
            if (bean.getIssueNumber().substring(0, 6).equals(sysDate)) {
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
                        i++;
                        break;
                    case 10:
                        j++;
                        break;
                    case 11:
                        k++;
                        break;
                }
            }
        }
        num3[0] = a;
        num3[1] = b;
        num3[2] = c;
        num3[3] = d;
        num3[4] = e;
        num3[5] = f;
        num3[6] = g;
        num3[7] = h;
        num3[8] = i;
        num3[9] = j;
        num3[10] = k;

    }

    public ViceBottomView(Context context) {
        super(context);
        initNum();
    }

    public ViceBottomView(Context context, @Nullable AttributeSet attrs) {
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

        mPaint.setColor(Color.parseColor("#eff0d2"));
        canvas.drawRect(0, 0, 11 * gridWidth, gridHeight, mPaint);
        mPaint.setColor(Color.parseColor("#e2f4e2"));
        canvas.drawRect(11 * gridWidth, 0, 22 * gridWidth, gridHeight, mPaint);
        mPaint.setColor(Color.parseColor("#eff0d2"));
        canvas.drawRect(22 * gridWidth, 0, 33 * gridWidth, gridHeight, mPaint);

        //画格子 -- 竖线
        mPaint.setColor(Color.parseColor("#c9c9c9"));
        for (int i = 0; i < column + 1; i++) {
            canvas.drawLine(gridWidth * i, 0, gridWidth * i, gridHeight, mPaint);
        }



        canvas.drawLine(0, 0, viewWidth, 0, mPaint);

        mPaint.setColor(Color.BLACK);

        canvas.drawLine(0, 0, 0, gridHeight, mPaint);

        canvas.drawLine(11 * gridWidth, 0, 11 * gridWidth, gridHeight, mPaint);

        canvas.drawLine(22 * gridWidth, 0, 22 * gridWidth, gridHeight, mPaint);
        //写数字
        for (int i = 0; i < 11; i++) {
            // 第一步 画矩形
            RectF rectF = new RectF(gridWidth * i, 0, gridWidth * i + gridWidth, gridHeight);
            // 第二步 写字
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.parseColor("#d1073f"));
            mPaint.setTextSize(20);
            // 获取文字矩形中心点 设置文字居中
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(num1[i] + "", rectF.centerX(), baseline, mPaint);
        }
        //写数字
        for (int i = 0; i < 11; i++) {
            // 第一步 画矩形
            RectF rectF = new RectF(gridWidth * 11 + i * gridWidth, 0, gridWidth * 11 + i * gridWidth + gridWidth, gridHeight);
            // 第二步 写字
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.parseColor("#096c56"));
            mPaint.setTextSize(20);
            // 获取文字矩形中心点 设置文字居中
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(num2[i] + "", rectF.centerX(), baseline, mPaint);
        }
        //写数字
        for (int i = 0; i < 11; i++) {
            // 第一步 画矩形
            RectF rectF = new RectF(gridWidth * 22 + i * gridWidth, 0, gridWidth * 22 + i * gridWidth + gridWidth, gridHeight);
            // 第二步 写字
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.parseColor("#d1073f"));
            mPaint.setTextSize(20);
            // 获取文字矩形中心点 设置文字居中
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(num3[i] + "", rectF.centerX(), baseline, mPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 33; //33列
        gridHeight = gridWidth * 11 / 6;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight));
    }
}
