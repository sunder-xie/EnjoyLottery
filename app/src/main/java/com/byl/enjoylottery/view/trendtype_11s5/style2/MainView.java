package com.byl.enjoylottery.view.trendtype_11s5.style2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.byl.enjoylottery.bean.BallBean;
import com.byl.enjoylottery.service.MapApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lz on 2017/5/10.
 */

public class MainView extends View {
    List<BallBean> list = MapApplication.getInstence().getList();
    float viewWidth, viewHeight, gridWidth, gridHeight;
    Context context;
    int column = 12;
    // 五个号从小到大排列
    Integer[] nums = new Integer[5];

    public MainView(Context context) {
        super(context);
        this.context = context;
    }

    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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

        mPaint.setColor(Color.parseColor("#e2f4e2"));
        canvas.drawRect(0, 0, 12 * gridWidth, gridHeight * list.size(), mPaint);

        for (int i = 0; i < list.size(); i++) {
            String sysDate = list.get(i).getIssueNumber();
            int date = Integer.parseInt(sysDate.substring(4, 6));
            if (date % 2 == 0) {
                mPaint.setColor(Color.parseColor("#e2f4e2"));
            } else {
                mPaint.setColor(Color.parseColor("#eff0d2"));
            }
            canvas.drawRect(0, gridHeight * i, gridWidth, gridHeight * (i + 1), mPaint);
        }
        mPaint.setColor(Color.parseColor("#C9C9C9"));
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

        //黑线
        for (int i = 0; i < list.size() + 1; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth((float) 1.0);
                mPaint.setColor(Color.BLACK);
                canvas.drawLine(0, i * gridHeight, viewWidth, i * gridHeight, mPaint);
            }
        }
        for (int i = 0; i < column; i++) {
            if (i % 11 == 0) {
                mPaint.setStrokeWidth((float) 1.0);
                mPaint.setColor(Color.BLACK);
                canvas.drawLine((i + 1) * gridWidth, 0, gridWidth * i + gridWidth, gridHeight * list.size(), mPaint);
            }
        }
        //连码背景变色
        for (int i = 0; i < list.size(); i++) {
            nums[0] = list.get(i).getNo1();
            nums[1] = list.get(i).getNo2();
            nums[2] = list.get(i).getNo3();
            nums[3] = list.get(i).getNo4();
            nums[4] = list.get(i).getNo5();
            Arrays.sort(nums, new MyComparator());
            mPaint.setColor(Color.parseColor("#94F9E2"));
            if (nums[0] - nums[4] == 4) {
                //五连码
                canvas.drawRect(gridWidth * nums[4], gridHeight * i, gridWidth * nums[0] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[0] - nums[3] == 3) {
                //四连码
                canvas.drawRect(gridWidth * nums[3], gridHeight * i, gridWidth * nums[0] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[1] - nums[4] == 3) {
                //四连码
                canvas.drawRect(gridWidth * nums[4], gridHeight * i, gridWidth * nums[1] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[0] - nums[2] == 2) {
                //三连码
                canvas.drawRect(gridWidth * nums[2], gridHeight * i, gridWidth * nums[0] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[1] - nums[3] == 2) {
                //三连码
                canvas.drawRect(gridWidth * nums[3], gridHeight * i, gridWidth * nums[1] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[2] - nums[4] == 2) {
                //三连码
                canvas.drawRect(gridWidth * nums[4], gridHeight * i, gridWidth * nums[2] + gridWidth, gridHeight * (i + 1), mPaint);
            } else if (nums[0] != 0) {
                if(getJ(nums) == 5 || getO(nums) == 5 ){
                    //全奇偶
                    canvas.drawRect(0, gridHeight * i, viewWidth + gridWidth, gridHeight * (i + 1), mPaint);
                }
            }

        }


//        开始画球
        RectF rectF;
        mPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        for (int i = 0; i < list.size(); i++) {
            mPaint.setAntiAlias(false);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Humanist777BoldBT.ttf");
            mPaint.setTypeface(typeface);
            mPaint.setLetterSpacing((float) -0.25);
            mPaint.setTextSize(45);
            mPaint.setFakeBoldText(true);
            if (list.get(i).getNo1() != 0) {
       /*       ----------         一道华丽的分割线                --            */
                int p = list.get(i).getNo1();
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint.setColor(Color.parseColor("#65185b"));
                // 获取文字矩形中心点 设置文字居中
                Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
                float baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo1() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */



            /*       ----------         一道华丽的分割线                --            */
                //第二个球
                p = list.get(i).getNo2();
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint.setColor(Color.parseColor("#65185b"));
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo2() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第三个球
                p = list.get(i).getNo3();
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint.setColor(Color.parseColor("#65185b"));
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo3() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第四个球
                p = list.get(i).getNo4();
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint.setColor(Color.parseColor("#050941"));
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo4() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */

              /*       ----------         一道华丽的分割线                --            */
                //第五个球
                p = list.get(i).getNo5();
                // 第二步 画矩形
                rectF = new RectF(gridWidth * p, gridHeight * i, gridWidth * p + gridWidth, gridHeight * i + gridHeight);
                // 第三步 写字 设置加粗
                mPaint.setColor(Color.parseColor("#050941"));
                baseline = (rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2;
                mPaint.setTextAlign(Paint.Align.CENTER);
                //写字
                canvas.drawText(list.get(i).getNo5() + "", rectF.centerX(), baseline, mPaint);
            /*       ----------         一道华丽的分割线                --            */
            }



            /* 期号 */

            mPaint.setTypeface(Typeface.DEFAULT);
            mPaint.setLetterSpacing(0);
            mPaint.setTextSize(30);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            // 第二步 画矩形
            rectF = new RectF(0, gridHeight * i, gridWidth, gridHeight * i + gridHeight);
            // 第三步 写字 设置加粗
            mPaint.setColor(Color.parseColor("#D1073F"));
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

    class MyComparator implements java.util.Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else return 0;
        }
    }

    private int getJ(Integer[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) result++;
        }
        return result;
    }

    private int getO(Integer[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) result++;
        }
        return result;
    }


}
