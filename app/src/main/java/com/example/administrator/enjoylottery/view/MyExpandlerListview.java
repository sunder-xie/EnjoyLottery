package com.example.administrator.enjoylottery.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class MyExpandlerListview extends ExpandableListView {
    public MyExpandlerListview(Context context) {
        super(context);
    }

    public MyExpandlerListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExpandlerListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
