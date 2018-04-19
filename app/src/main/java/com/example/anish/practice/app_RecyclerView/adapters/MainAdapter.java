package com.example.anish.practice.app_RecyclerView.adapters;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.practice.R;
import com.example.anish.practice.app_RecyclerView.ui.RV_item_decor;

/**
 * Created by Anish on 23-08-2017.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    class HolderEven extends RecyclerView.ViewHolder{
        //RecyclerView rv;
        public HolderEven(View itemView) {
            super(itemView);

            //rv = (RecyclerView)itemView.findViewById(R.id.RV_item);
            //rv.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            //rv.setItemAnimator(new DefaultItemAnimator());
            //rv.addItemDecoration(new RV_item_decor("inner"));
            //ItemAdapter adapter = new ItemAdapter();
            //rv.setAdapter(adapter);



        }
    }

    class HolderOdd extends RecyclerView.ViewHolder{
        RecyclerView rv;
        public HolderOdd(View itemView) {
            super(itemView);

            rv = (RecyclerView)itemView.findViewById(R.id.RV_item);
            rv.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.addItemDecoration(new RV_item_decor("inner"));
            ItemAdapter adapter = new ItemAdapter();
            rv.setAdapter(adapter);



        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null);
        //Holder holder = new Holder(view);
        Log.i("MainAdapter","About to create ViewHolder TYPE :"+viewType);



        if(viewType == 0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nomal,null);
            HolderEven holderEven = new HolderEven(view);
            return holderEven;
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,null);
            HolderOdd holderOdd = new HolderOdd(view);
            return holderOdd;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("MainAdapter","from onBindViewHolder() position :"+position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("MainAdapter","ViewType :"+position%2);
        return position%2;
    }
}
