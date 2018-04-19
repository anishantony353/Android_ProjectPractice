package com.example.anish.practice.app_Retrofit.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Anish on 28-12-2017.
 */

public class TestObj implements Serializable {
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
