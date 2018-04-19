package com.example.anish.practice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anish.practice.R;
import com.example.anish.practice.pojo.Image;

import java.util.ArrayList;

/**
 * Created by Anish on 08-08-2017.
 */

public class GlideRvAdapter extends RecyclerView.Adapter<GlideRvAdapter.Holder> {
    ArrayList<Image> images;
    Context context;

    public GlideRvAdapter(Context context, ArrayList<Image> images){
        this.images = images;
        this.context = context;
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public Holder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.iv);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.glide_rv_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(images.get(position).getUrl()).into(holder.iv);
        holder.tv.setText(images.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
