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

/**
 * Created by lz on 2017/5/11.
 */

public class ViceTopView extends View {
    int column = 33;
    float viewWidth, viewHeight, gridWidth, gridHeight;

    public ViceTopView(Context context) {
        super(context);
    }

    public ViceTopView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setColor(Color.parseColor("#EFF0D2"));
        canvas.drawRect(0, 0, gridWidth * 11, viewHeight, mPaint);
        mPaint.setColor(Color.parseColor("#E2F4E2"));
        canvas.drawRect(gridWidth * 11, 0, gridWidth * 22, viewHeight, mPaint);
        mPaint.setColor(Color.parseColor("#EFF0D2"));
        canvas.drawRect(gridWidth * 22, 0, gridWidth * 33, viewHeight, mPaint);

        //画格子 -- 横线
        mPaint.setColor(Color.parseColor("#C9C9C9"));
        for (int i = 1; i < 2; i++) {
            canvas.drawLine(0, gridHeight * i, viewWidth, gridHeight * i, mPaint);
        }
        //画格子 -- 竖线

        for (int i = 1; i < column + 2; i++) {
            canvas.drawLine(gridWidth * i, gridHeight, gridWidth * i, viewHeight, mPaint);
        }

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float) 1.0);


        canvas.drawLine(0, 0, 0, gridHeight, mPaint);

        canvas.drawLine(0, gridHeight, 0, gridHeight*2, mPaint);

        canvas.drawLine(gridWidth * 11, 0, gridWidth * 11, viewHeight, mPaint);

        canvas.drawLine(gridWidth * 22, 0, gridWidth * 22, viewHeight, mPaint);

        //写字部分
        // 五码分布图
        // 第一步 画矩形
        RectF rectF = new RectF(gridWidth, 0, gridWidth * 11, gridHeight);
        // 第二步 写字
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#D1073F"));
        mPaint.setTextSize(30);
        mPaint.setFakeBoldText(true);
        // 获取文字矩形中心点 设置文字居中
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        //写字
        canvas.drawText("第一位走势", rectF.centerX(), baseline, mPaint);


        rectF = new RectF(gridWidth * 11, 0, gridWidth * 22, gridHeight);
        fontMetrics = mPaint.getFontMetricsInt();
        baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#096c56"));
        //写字
        canvas.drawText("第二位走势", rectF.centerX(), baseline, mPaint);

        rectF = new RectF(gridWidth * 22, 0, gridWidth * 33, gridHeight);
        fontMetrics = mPaint.getFontMetricsInt();
        baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#D1073F"));
        //写字
        canvas.drawText("第三位走势", rectF.centerX(), baseline, mPaint);

        //写数字
        for (int i = 0; i < 11; i++) {
            mPaint.setAntiAlias(true);
            mPaint.setFakeBoldText(true);
            mPaint.setColor(Color.parseColor("#d1073f"));
            mPaint.setTextSize(22);
            // 第一步 画矩形
            rectF = new RectF(gridWidth * i, gridHeight, gridWidth * i + gridWidth, gridHeight + gridHeight);
            // 第二步 写字

            // 获取文字矩形中心点 设置文字居中
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(i + 1 + "", rectF.centerX(), baseline, mPaint);


            // 第一步 画矩形
            rectF = new RectF(gridWidth * 11 + i * gridWidth, gridHeight, gridWidth * 11 + i * gridWidth + gridWidth, gridHeight * 2);
            // 第二步 写字
            mPaint.setColor(Color.parseColor("#096c56"));
            // 获取文字矩形中心点 设置文字居中
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(i + 1 + "", rectF.centerX(), baseline, mPaint);


            // 第一步 画矩形
            rectF = new RectF(gridWidth * 22 + i * gridWidth, gridHeight, gridWidth * 22 + i * gridWidth + gridWidth, gridHeight * 2);
            // 第二步 写字
            mPaint.setColor(Color.parseColor("#d1073f"));
            // 获取文字矩形中心点 设置文字居中
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(i + 1 + "", rectF.centerX(), baseline, mPaint);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 33; //33列
        gridHeight = gridWidth * 11 / 6;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight * 2));
    }
}
