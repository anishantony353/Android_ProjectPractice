package com.example.anish.practice.app_MemoryLeak;

import android.content.Context;

/**
 * Created by Anish on 24-11-2017.
 */

public class RandomClass {



    public static RandomClass randomClass;

    static Context context;

    public RandomClass(Context context){
        this.context = context;

    }

   public static RandomClass getRandomClass(Context context){
        //con = context;
       if(randomClass == null){

           randomClass = new RandomClass(context);
           return randomClass;
       }else{

           return randomClass;
       }

   }


}
