package com.example.anish.practice.app_Retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Retrofit.pojo.ResponseObj;
import com.example.anish.practice.app_Retrofit.pojo.TestObj;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_Retrofit extends AppCompatActivity {
    String TAG = "MainActivity_Retrofit";
    EditText id,password;
    Context context = this;
    ImageView IV_img;
    Bitmap bitmap;
    Uri fileUri;

    final int IMG_REQ_CODE = 1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__retrofit);
        id = (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.password);
        IV_img = (ImageView)findViewById(R.id.iv_img);

        //getJsonFile();
        getXmlFile();
    }

    public void getJsonFile(){

        Utility_Retrofit.getAPIService().getJsonFile().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"success)");
                    //Log.i(TAG,response.body());
                    JsonObject jsonObject = response.body();
                    JsonArray jsonArrayThird = jsonObject.get("third").getAsJsonArray();
                    //Log.i(TAG,"Checking third: SIZE:"+jsonArrayThird.size());
                    for(int i=0;i<jsonArrayThird.size();i++){
                        if(jsonArrayThird.get(i).getAsJsonObject().get("show").getAsBoolean() == true){
                                Log.i(TAG,"Showing:"+jsonArrayThird.get(i).getAsJsonObject().get("third").getAsString());
                        }
                    }

                }else{
                    Log.i(TAG,"failure)");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.i(TAG,"onFailure()");

            }
        });

    }

    public void getXmlFile(){

        Utility_Retrofit.getAPIService().getXmlFile().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"success)");
                    Log.i(TAG,response.body());
//                    String string = response.body();

//                    JsonArray jsonArrayThird = jsonObject.get("third").getAsJsonArray();
//                    Log.i(TAG,"Checking third: SIZE:"+jsonArrayThird.size());
//                    for(int i=0;i<jsonArrayThird.size();i++){
//                        if(jsonArrayThird.get(i).getAsJsonObject().get("show").getAsBoolean() == true){
//                            Log.i(TAG,"Showing:"+jsonArrayThird.get(i).getAsJsonObject().get("third").getAsString());
//                        }
//                    }

                }else{
                    Log.i(TAG,"failure)");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.i(TAG,"onFailure()");

            }
        });

    }

    public void getUserData(View view) {
        if(!id.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("")){

            Map<String, String> loginData;
            loginData = new HashMap<>();
            loginData.put("tag","login");
            loginData.put("email",id.getText().toString().trim());
            loginData.put("password",password.getText().toString().trim());

            //JSONObject response = new JSONObject();
            /*
            "login",
                    id.getText().toString().trim(),
                    password.getText().toString().trim()
                    */
            Utility_Retrofit.getAPIService().getUserDetails(loginData).
                    enqueue(new Callback<ResponseObj>() {
                        @Override
                        public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                            if(response.isSuccessful()){
                                ResponseObj responseObj = (ResponseObj) response.body();
                                if(responseObj != null){
                                    Log.i(TAG,responseObj.getSuccess()+" "+responseObj.getError_msg());

                                }else{
                                    Log.i(TAG,"ResponseObj NULL");
                                }

                            }else{
                                Log.i(TAG,"Response not Successful");
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseObj> call, Throwable t) {

                        }
                    });
            //Log.i(TAG,response.toString());

        }else{

            Toast.makeText(this,"Enter details",Toast.LENGTH_SHORT).show();

        }


    }

    public void uploadData(View view) {
        if(!password.getText().toString().trim().equals("")){
            if(fileUri != null){
                File imgFile = new File(fileUri.getPath());

                RequestBody file = RequestBody.create(MediaType.parse("image/*"),imgFile);


                MultipartBody.Part partFile1 = MultipartBody.Part.createFormData("img_file1",
                        password.getText().toString().trim()+"_file_1.jpg",
                        file);

                MultipartBody.Part partFile2 = MultipartBody.Part.createFormData("img_file2",
                        password.getText().toString().trim()+"_file_2.jpg",
                        file);

                MultipartBody.Part partFile3 = MultipartBody.Part.createFormData("img_file3",
                        password.getText().toString().trim()+"_file_3.jpg",
                        file);


                RequestBody tag = RequestBody.create(MediaType.parse("text/plain"),"retrofit");

                TestObj testObj = new TestObj();
                testObj.setId("1");
                testObj.setName("name");

                //,partDesc1 //,partFile3
                String tagStr = "retrofit";
                Utility_Retrofit.getAPIService().uploadImage(tag,partFile1,partFile2,partFile3,testObj).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        if(response.isSuccessful()){
                            Log.i(TAG,"hit successful");
                            Log.i(TAG,response.body().toString());
                        }else{
                            Log.i(TAG,"hit Not Successful");
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.i(TAG,"hit Failure");
                    }
                });

            }else{
                Toast.makeText(this,"select an image",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"enter image name",Toast.LENGTH_SHORT).show();
        }


    }

    public void getImg(View view) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent,IMG_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMG_REQ_CODE && resultCode == RESULT_OK && data != null){

            fileUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),fileUri);
                IV_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Log.i(TAG,"Could not get Image");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
