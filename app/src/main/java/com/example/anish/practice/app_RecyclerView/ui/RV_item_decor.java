package com.example.anish.practice.app_RecyclerView.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Anish on 24-08-2017.
 */

public class RV_item_decor extends RecyclerView.ItemDecoration {
    String type;
    public RV_item_decor(String type){
        this.type = type;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(type.equals("main")){
            outRect.bottom = 15;
            //outRect.left= 10;
            //outRect.right= 10;

        }else{
            outRect.right= 30;
        }

        //outRect.left= 10;
        //outRect.right= 10;
    }
}
