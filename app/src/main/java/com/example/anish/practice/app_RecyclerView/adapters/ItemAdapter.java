package com.example.anish.practice.app_RecyclerView.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.practice.R;

/**
 * Created by Anish on 23-08-2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.HolderItem> {

    class HolderItem extends RecyclerView.ViewHolder{

        public HolderItem(View itemView) {
            super(itemView);
        }
    }
    @Override
    public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        return new HolderItem(view);
    }

    @Override
    public void onBindViewHolder(HolderItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
