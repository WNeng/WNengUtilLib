package com.android.wneng.utils.recyclerview.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * description:
 * <p/>
 * Created by wangwn on 2016/4/7.
 */
public class BottomSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public BottomSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildPosition(view) == 0) {

//            outRect.top = space;
        }
        outRect.bottom = space;


    }

}
