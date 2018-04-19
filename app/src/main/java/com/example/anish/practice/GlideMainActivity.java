package com.example.anish.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.anish.practice.adapters.GlideRvAdapter;
import com.example.anish.practice.design.RV_ItemDecoration;
import com.example.anish.practice.pojo.Image;
import com.example.anish.practice.utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GlideMainActivity extends AppCompatActivity {
    String CLASS_TAG = "GlideMainActivity";
    String jsonUrl = "https://api.androidhive.info/json/glide.json";
    private ArrayList<Image> images;

    RecyclerView rv;
    Context context = this;

    GlideRvAdapter glideRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_main);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(context,2));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new RV_ItemDecoration());


        images = new ArrayList<>();
        glideRvAdapter = new GlideRvAdapter(context,images);
        rv.setAdapter(glideRvAdapter);

        getJson();


    }

    private void getJson() {
        Utility.getProgressDialog(this,"Getting data");

        JsonArrayRequest req = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Utility.dismissDialog();
                Log.i(CLASS_TAG,"JSON Array response:"+response);
                Image image;
                for(int i=0;i<response.length();i++){
                    image = new Image();
                    try {
                        image.setName(response.getJSONObject(i).getString("name"));
                        image.setUrl(response.getJSONObject(i).getJSONObject("url").getString("small"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    images.add(image);
                }

                glideRvAdapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(CLASS_TAG,"volley response error:"+error);
                Utility.dismissDialog();

            }
        });

        Volley.newRequestQueue(this).add(req);

    }


}
