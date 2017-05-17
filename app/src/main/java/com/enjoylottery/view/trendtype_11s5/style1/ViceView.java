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
import android.widget.ScrollView;

import com.enjoylottery.service.MapApplication;
import com.enjoylottery.bean.BallBean;

import java.util.List;

/**
 * Created by lz on 2017/5/9.
 * 副图
 */

public class  ViceView extends View {
    List<BallBean> list = MapApplication.getInstence().getList();
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
    }

    public ViceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint mPaint = new Paint();
        //抗锯齿处理
        PaintFlagsDrawFilter drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | 3);
        canvas.setDrawFilter(drawFilter);
        mPaint.setAntiAlias(true);
        //画填充色
        for (int i = 0; i < rowNum + 1; i++) {
            if (i % 2 == 0) {
                mPaint.setColor(0xffCEEBCD);
                canvas.drawRect(11 * gridWidth, gridHeight * i, 22 * gridWidth, gridHeight * (i + 1), mPaint);
            }
        }
        mPaint.setColor(0xff3EB0F4);
        mPaint.setStrokeWidth((float) 1.0);
        //画格
        for (int i = 0; i < rowNum; i++) {
            //画横线
            canvas.drawLine(0, i * gridHeight, viewWidth, i * gridHeight, mPaint);
        }
        for (int i = 0; i < column; i++) {
            //画竖线
            canvas.drawLine(i * gridWidth, 0, gridWidth * i, gridHeight * rowNum, mPaint);
        }

        //画折线 -- 保证球覆盖在线上 所以单独先划折线
        for (int i = 1; i < rowNum -1; i++) {
            // 第一列
            int pos = list.get(i - 1).getNo1();
            mPaint.setColor(Color.parseColor("#592e0e"));
            mPaint.setStrokeWidth(2);
            float x1 = gridWidth * (pos - 1) + gridWidth / 2;
            float y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo1();
            float x2 = gridWidth * (pos - 1) + gridWidth / 2;
            float y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);

            // 第二列
            pos = list.get(i - 1).getNo2();
            mPaint.setColor(Color.parseColor("#085a8c"));
            mPaint.setStrokeWidth(2);
            x1 = gridWidth * (pos - 1) + gridWidth * 11 + gridWidth / 2;
            y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo2();
            x2 = gridWidth * (pos - 1) + gridWidth * 11 + gridWidth / 2;
            y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);

            //第三列
            pos = list.get(i - 1).getNo3();
            mPaint.setColor(Color.parseColor("#592e0e"));
            mPaint.setStrokeWidth(2);
            x1 = gridWidth * (pos - 1) + gridWidth * 22 + gridWidth / 2;
            y1 = i * gridHeight - gridHeight / 2;
            pos = list.get(i).getNo3();
            x2 = gridWidth * (pos - 1) + gridWidth * 22 + gridWidth / 2;
            y2 = (i) * gridHeight + gridHeight / 2;
            canvas.drawLine(x1, y1, x2, y2, mPaint);
        }
        //开始画球
        // 前三位走势图
        RectF rectF;
        mPaint.setAntiAlias(false);

        //球的半径
        float radius = gridWidth / 2 + 10;
        for (int i = 0; i < rowNum - 1; i++) {

            // 因为绘制图形过程中 后绘制的图形 如果绘制区域和其他坐标重叠会有一个叠加 为了保证重叠区域的方向一致 所以先从最右侧画起
                     /* 第三位 */
            int p = list.get(i).getNo3() - 1;
            // 第一步 画圆
            mPaint.setColor(Color.parseColor("#592e0e"));
            float circleX = gridWidth * p + gridWidth * 22 + gridWidth / 2;
            float circleY = gridHeight * i + gridHeight / 2;
            canvas.drawCircle(circleX, circleY, radius, mPaint);
            // 第二步 画矩形
            rectF = new RectF(gridWidth * p + gridWidth * 22, gridHeight * i, gridWidth * p + gridWidth * 22 + gridWidth, gridHeight * i + gridHeight);
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
            canvas.drawText(list.get(i).getNo3() + "", rectF.centerX(), baseline, mPaint);

                 /* 第二位 */
            p = list.get(i).getNo2() - 1;
            // 第一步 画圆
            mPaint.setColor(Color.parseColor("#085a8c"));
            circleX = gridWidth * p + gridWidth * 11 + gridWidth / 2;
            circleY = gridHeight * i + gridHeight / 2;
            canvas.drawCircle(circleX, circleY, radius, mPaint);
            // 第二步 画矩形
            rectF = new RectF(gridWidth * p + gridWidth * 11, gridHeight * i, gridWidth * p + gridWidth * 11 + gridWidth, gridHeight * i + gridHeight);

            // 第三步 写字 设置加粗
            mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(30);
            // 获取文字矩形中心点 设置文字居中
            fontMetrics = mPaint.getFontMetricsInt();
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(list.get(i).getNo2() + "", rectF.centerX(), baseline, mPaint);

                 /* 第一位 */
            // 第一步 画圆
            p = list.get(i).getNo1() - 1;
            mPaint.setColor(Color.parseColor("#592e0e"));
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
            // 获取文字矩形中心点 设置文字居中
            fontMetrics = mPaint.getFontMetricsInt();
            baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
            mPaint.setTextAlign(Paint.Align.CENTER);
            //写字
            canvas.drawText(list.get(i).getNo1() + "", rectF.centerX(), baseline, mPaint);
        }

        ((ScrollView)((getParent()).getParent())).fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure( widthMeasureSpec,heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        gridWidth = viewWidth / 33; //33列
        gridHeight = gridWidth * 11 / 6;
        setMeasuredDimension((int)viewWidth, (int)(gridHeight*list.size()));
    }


}
