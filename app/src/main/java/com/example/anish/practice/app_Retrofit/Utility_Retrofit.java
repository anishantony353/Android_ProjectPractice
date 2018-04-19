package com.example.anish.practice.app_Retrofit;

import com.example.anish.practice.app_Retrofit.REST.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Anish on 27-11-2017.
 */

public class Utility_Retrofit {

    public static final String APP_URL = "http://172.17.47.173/android.anish.practice/";//"http://103.233.79.142:90/android_V08.saarit.in/"


    public static ApiService getAPIService() {

        //return RetrofitClient.getClient(APP_URL).create(ApiService.class);

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APP_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).
                addConverterFactory(SimpleXmlConverterFactory.create()).
                build();

        return retrofit.create(ApiService.class);


    }
}
