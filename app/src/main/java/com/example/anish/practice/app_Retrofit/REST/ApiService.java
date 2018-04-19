package com.example.anish.practice.app_Retrofit.REST;

import com.example.anish.practice.app_Retrofit.pojo.ResponseObj;
import com.example.anish.practice.app_Retrofit.pojo.TestObj;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Anish on 27-11-2017.
 */

public interface ApiService {

    @POST("index.php")
    @FormUrlEncoded
    Call<ResponseObj> getUserDetails(@FieldMap Map<String, String> loginData);
    /*
    Call<JsonObject> getUserDetails(@Field("tag")String tag,
                                    @Field("email")String email,
                                    @Field("password")String password);
    */

    @Multipart
    @POST("index.php")//"img_upload.php"
    Call<JsonObject> uploadImage(@Part("tag") RequestBody tag,
                                 @Part MultipartBody.Part file1,
                                 @Part MultipartBody.Part file2,
                                 @Part MultipartBody.Part file3,
                                 @Part("obj")TestObj testObj);

    @GET("json_file.json")
    Call<JsonObject> getJsonFile();

    @GET("xml_file.xml")
    Call<String> getXmlFile();
    //, @Part("desc") RequestBody desc
    //Call<JsonObject> uploadImage(@Part MultipartBody.Part file1,@Part MultipartBody.Part file2,@Part MultipartBody.Part file3); //, @Part("desc") RequestBody desc
}
