package com.example.anish.practice.design;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Anish on 10-08-2017.
 */

public class RV_ItemDecoration extends RecyclerView.ItemDecoration {

    public RV_ItemDecoration(){

    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) %2 ==  0) {
            outRect.bottom = 15;

        }else{
            outRect.left = 15;
            outRect.bottom = 15;
        }
    }
}
