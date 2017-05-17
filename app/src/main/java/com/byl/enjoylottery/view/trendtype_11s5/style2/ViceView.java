package com.byl.enjoylottery.view.trendtype_11s5.style2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.byl.enjoylottery.bean.BallBean;
import com.byl.enjoylottery.service.MapApplication;

import java.util.List;

/**
 * Created by lz on 2017/5/9.
 * 副图
 */

public class ViceView extends View {
    List<BallBean> list = MapApplication.getInstence().getList();
    Context context;
    //控件宽度
    float viewWidth;
    //控件高度
    float viewHeight;
    //一个格子的宽度
    float gridWidth;
    //一个格子的高度
    float gridHeight;
    //行数
    int rowNum = list.size();
    //列数
    int column = 33;

    public ViceView(Context context) {
        super(context);
        this.context = context;
    }

    public ViceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint mPaint = new Paint();
        //抗锯齿处理
        PaintFlagsDrawFilter drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | 3);
        canvas.setDrawFilter(drawFilter);
        mPaint.setAntiAlias(true);

        //背景
        mPaint.setColor(Color.parseColor("#eff0d2"));
        canvas.drawRect(0, 0, 11 * gridWidth, rowNum * gridHeight, mPaint);

        mPaint.setColor(Color.parseColor("#e2f4e2"));
        canvas.drawRect(11 * gridWidth, 0, 22 * gridWidth, rowNum * gridHeight, mPaint);

        mPaint.setColor(Color.parseColor("#eff0d2"));
        canvas.drawRect(22 * gridWidth, 0, 33 * gridWidth, rowNum * gridHeight, mPaint);


        //画格

        //画竖线
        for (int i = 0; i < column; i++) {
            mPaint.setStrokeWidth((float) 1.0);
            mPaint.setColor(Color.parseColor("#C9C9C9"));
            canvas.drawLine(i * gridWidth, 0, gridWidth * i, gridHeight * rowNum, mPaint);
        }


        //画横线
        for (int i = 0; i < rowNum; i++) {
            mPaint.setStrokeWidth((float) 1.0);
            mPaint.setColor(Color.parseColor("#C9C9C9"));
            canvas.drawLine(0, i * gridHeight, viewWidth, i * gridHeight, mPaint);
        }

        for (int i = 0; i < column; i++) {
            if (i % 11 == 0) {
                mPaint.setStrokeWidth((float) 1.0);
                mPaint.setColor(Color.BLACK);
                canvas.drawLine(i * gridWidth, 0, gridWidth * i, gridHeight * rowNum, mPaint);
            }
        }

        for (int i = 0; i < rowNum; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth((float) 1.0);
                mPaint.setColor(Color.BLACK);
                canvas.drawLine(0, i * gridHeight, viewWidth, i * gridHeight, mPaint);
            }
        }

        //画折线 -- 保证球覆盖在线上 所以单独先划折线
        for (int i = 1; i < rowNum - 1; i++) {
            // 第一列
            int pos = list.get(i - 1).getNo1();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(2);
            float x1 = gridWidth * (pos - 1) + gridWidth / 2;
            float y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo1();
            float x2 = gridWidth * (pos - 1) + gridWidth / 2;
            float y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);

            // 第二列
            pos = list.get(i - 1).getNo2();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(2);
            x1 = gridWidth * (pos - 1) + gridWidth * 11 + gridWidth / 2;
            y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo2();
            x2 = gridWidth * (pos - 1) + gridWidth * 11 + gridWidth / 2;
            y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);

            //第三列
            pos = list.get(i - 1).getNo3();
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth(2);
            x1 = gridWidth * (pos - 1) + gridWidth * 22 + gridWidth / 2;
            y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo3();
            x2 = gridWidth * (pos - 1) + gridWidth * 22 + gridWidth / 2;
            y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);
        }
        //画数字
        // 前三位走势图
        RectF rectF;
        mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(false);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Humanist777BoldBT.ttf");
        mPaint.setTypeface(typeface);
        mPaint.setLetterSpacing((float) -0.25);
        mPaint.setTextSize(45);
        mPaint.setFakeBoldText(true);
        for (int i = 0; i < rowNum - 1; i++) {
            // 因为绘制图形过程中 后绘制的图形 如果绘制区域和其他坐标重叠会有一个叠加 为了保证重叠区域的方向一致 所以先从最右侧画起
                     /* 第三位 */
            int p = list.get(i).getNo3() - 1;
            // 第二步 画矩形
            rectF = new RectF(gridWidth * p + gridWidth * 22, gridHeight * i, gridWidth * p + gridWidth * 22 + gridWidth, gridHeight * i + gridHeight);
            // 第三步 写字 设置加粗
            mPaint.setColor(Color.parseColor("#65185B"));
            // 获取文字矩形中心点 设置文字居中
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(list.get(i).getNo3() + "", rectF.centerX(), baseline, mPaint);

                 /* 第二位 */
            p = list.get(i).getNo2() - 1;
            // 第二步 画矩形
            rectF = new RectF(gridWidth * p + gridWidth * 11, gridHeight * i, gridWidth * p + gridWidth * 11 + gridWidth, gridHeight * i + gridHeight);

            // 第三步 写字 设置加粗
            mPaint.setColor(Color.parseColor("#050941"));

            // 获取文字矩形中心点 设置文字居中
            fontMetrics = mPaint.getFontMetricsInt();
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(list.get(i).getNo2() + "", rectF.centerX(), baseline, mPaint);

                 /* 第一位 */
            p = list.get(i).getNo1() - 1;
            // 第二步 画矩形
            rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
            // 第三步 写字 设置加粗
            mPaint.setColor(Color.parseColor("#65185B"));
            // 获取文字矩形中心点 设置文字居中
            fontMetrics = mPaint.getFontMetricsInt();
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);

            //写字
            canvas.drawText(list.get(i).getNo1() + "", rectF.centerX(), baseline, mPaint);
        }

        ((ScrollView) ((getParent()).getParent())).fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 33; //33列
        gridHeight = gridWidth * 11 / 6;
        setMeasuredDimension((int) viewWidth, (int) (gridHeight * list.size()));
    }


}
