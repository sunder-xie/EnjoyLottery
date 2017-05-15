package com.example.administrator.enjoylottery.view.trendtype_11s5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.example.administrator.enjoylottery.bean.BallBean;
import com.example.administrator.enjoylottery.service.MapApplication;

import java.util.List;

/**
 * Created by lz on 2017/5/10.
 */

public class MainView extends View {
    List<BallBean> list = MapApplication.getInstence().getList();
    float viewWidth, viewHeight, gridWidth, gridHeight;

    int column = 12;

    public MainView(Context context) {
        super(context);
    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        //抗锯齿处理
        PaintFlagsDrawFilter drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | 3);
        canvas.setDrawFilter(drawFilter);
        mPaint.setAntiAlias(true);
        //画填充色
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                mPaint.setColor(0xffCEEBCD);
                canvas.drawRect(gridWidth, gridHeight * i, 12 * gridWidth, gridHeight * (i + 1), mPaint);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String sysDate = list.get(i).getIssueNumber();
            int date = Integer.parseInt(sysDate.substring(4, 6));
            if (date % 2 == 0) {
                mPaint.setColor(Color.parseColor("#9bd7fb"));
            } else {
                mPaint.setColor(Color.WHITE);
            }
            canvas.drawRect(0, gridHeight * i, gridWidth, gridHeight * (i + 1), mPaint);
        }
        mPaint.setColor(0xff3EB0F4);
        mPaint.setStrokeWidth((float) 1.0);

        //画格
        for (int i = 0; i < list.size() + 1; i++) {
            //画横线
            canvas.drawLine(0, i * gridHeight, viewWidth, i * gridHeight, mPaint);
        }
        for (int i = 0; i < column; i++) {
            //画竖线
            canvas.drawLine(i * gridWidth, 0, gridWidth * i, gridHeight * list.size(), mPaint);
        }

//        开始画球
        RectF rectF;
        mPaint.setAntiAlias(false);
        //球的半径
        float radius = gridWidth / 2;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNo1() != 0) {
       /*       ----------         一道华丽的分割线                --            */
                //第一个球
                mPaint.setColor(Color.parseColor("#085a8c"));
                int p = list.get(i).getNo1();
                float circleX = gridWidth * p + gridWidth / 2;
                float circleY = gridHeight * i + gridHeight / 2;
                canvas.drawCircle(circleX, circleY, radius, mPaint);
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(30);
                // 获取文字矩形中心点 设置文字居中
                Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
                float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo1() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */



            /*       ----------         一道华丽的分割线                --            */
                //第二个球
                mPaint.setColor(Color.parseColor("#085a8c"));
                p = list.get(i).getNo2();
                circleX = gridWidth * p + gridWidth / 2;
                circleY = gridHeight * i + gridHeight / 2;
                canvas.drawCircle(circleX, circleY, radius, mPaint);
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(30);
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo2() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第三个球
                mPaint.setColor(Color.parseColor("#085a8c"));
                p = list.get(i).getNo3();
                circleX = gridWidth * p + gridWidth / 2;
                circleY = gridHeight * i + gridHeight / 2;
                canvas.drawCircle(circleX, circleY, radius, mPaint);
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(30);
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo3() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第四个球
                mPaint.setColor(Color.parseColor("#085a8c"));
                p = list.get(i).getNo4();
                circleX = gridWidth * p + gridWidth / 2;
                circleY = gridHeight * i + gridHeight / 2;
                canvas.drawCircle(circleX, circleY, radius, mPaint);
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(30);
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo4() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第五个球
                mPaint.setColor(Color.parseColor("#085a8c"));
                p = list.get(i).getNo5();
                circleX = gridWidth * p + gridWidth / 2;
                circleY = gridHeight * i + gridHeight / 2;
                canvas.drawCircle(circleX, circleY, radius, mPaint);
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.WHITE);
                mPaint.setTextSize(30);
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo5() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */
            }



            /* 期号 */

            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            // 第二步 画矩形
            rectF = new RectF(0, gridHeight * i, gridWidth, gridHeight * i + gridHeight);
            // 第三步 写字 设置加粗
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(25);
            float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);

            //写字
            canvas.drawText((list.get(i).getIssueNumber()).substring((list.get(i).getIssueNumber()).length() - 2), rectF.centerX(), baseline, mPaint);
        }

        ((ScrollView) ((getParent()).getParent())).fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 12;  //11列
        gridHeight = viewWidth / 12;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight * list.size()));
    }

}
