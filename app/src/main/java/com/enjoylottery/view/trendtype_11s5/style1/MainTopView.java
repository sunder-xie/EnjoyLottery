package com.enjoylottery.view.trendtype_11s5.style1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lz on 2017/5/11.
 */

public class MainTopView extends View {

    int column = 12;
    float viewWidth, viewHeight, gridWidth, gridHeight;
    String[] nums = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};

    public MainTopView(Context context) {
        super(context);
    }

    public MainTopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        canvas.drawRect(0, 0, viewWidth, viewHeight, mPaint);
        //画格子 -- 横线
        mPaint.setColor(Color.WHITE);
        for (int i = 1; i < 2; i++) {
            canvas.drawLine(gridWidth, gridHeight * i, viewWidth, gridHeight * i, mPaint);
        }
        //画格子 -- 竖线
        canvas.drawLine(gridWidth, 0, gridWidth, viewHeight, mPaint);
        for (int i = 2; i < column + 1; i++) {
            canvas.drawLine(gridWidth * i, gridHeight, gridWidth * i, viewHeight, mPaint);
        }

        //写字部分
        // 五码分布图
        // 第一步 画矩形
        RectF rectF = new RectF(gridWidth, 0, viewWidth, gridHeight);
        // 第二步 写字
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);
        // 获取文字矩形中心点 设置文字居中
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        //写字
        canvas.drawText("开奖号码分布图", rectF.centerX(), baseline, mPaint);

        //写数字
        for (int i = 1; i < nums.length + 1; i++) {
            // 第一步 画矩形
            rectF = new RectF(gridWidth * i, gridHeight, gridWidth * i + gridWidth, gridHeight + gridHeight);
            // 第二步 写字
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(25);
            // 获取文字矩形中心点 设置文字居中
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(nums[i - 1], rectF.centerX(), baseline, mPaint);
        }

        //写期号

        // 第一步 画矩形
        rectF = new RectF(0, 0, gridWidth, gridHeight);
        // 第二步 写字
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(25);
        // 获取文字矩形中心点 设置文字居中
        baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("期", rectF.centerX(), baseline, mPaint);


        // 第一步 画矩形
        rectF = new RectF(0, gridHeight, gridWidth, gridHeight * 2);
        // 第二步 写字
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(25);
        // 获取文字矩形中心点 设置文字居中
        baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("号", rectF.centerX(), baseline, mPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 12;  //12列
        gridHeight = viewWidth / 12;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight * 2));
    }
}
