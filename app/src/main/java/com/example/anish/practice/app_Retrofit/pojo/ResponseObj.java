package com.example.anish.practice.app_Retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anish on 28-11-2017.
 */

public class ResponseObj {

    @SerializedName("tag")
    @Expose
    private String tag;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("error_msg")
    @Expose
    private String error_msg;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
