package com.example.anish.practice.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Anish on 08-07-2017.
 */

public class Utility {

    static public ProgressDialog progressDialog;

    public static String app_url = "http://172.17.47.173/android.directions/";  // http://172.17.46.54/android.survey.tracker/  ...http://103.233.79.142:90/android.survey.tracker/

    public static String getDataFromTheUrl(List<NameValuePair> postData, String url) {
        HttpResponse httpResponse = null;
        String serverResponse = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            // httpClient = getClient(httpClient);
            HttpPost httpPost = new HttpPost(url);
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                if (postData != null)
                    httpPost.setEntity(new UrlEncodedFormEntity(postData));
                try {
                    httpResponse = httpClient.execute(httpPost);
                }
                catch (ClientProtocolException e) {
                    e.printStackTrace();
                    //serverResponse = "serverProblem";
                }
                catch (IOException e) {
                    e.printStackTrace();
                    //serverResponse = "serverProblem";
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //serverResponse = "serverProblem";
                }
                // Retrieve response
                if (httpResponse != null) {
                    InputStream is = httpResponse.getEntity().getContent();
                    String output = null;
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is));
                    // store response in buffer
                    while ((output = br.readLine()) != null) {
                        stringBuffer.append(output);
                    }
                    stringBuffer.trimToSize();
                    is.close();
                    br.close();
                    br = null;
                    is = null;
                    httpResponse.getEntity().consumeContent();
                    serverResponse = stringBuffer.toString(); // get response in
                    // string to
                    // parse it
                    Log.d("LOGD", " getDataFromTheUrl url " + url);
                    //Log.d("CamToolServer getDataFromTheUrl", "response " + serverResponse);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                //serverResponse = "serverProblem";
            } catch (Exception ex) {
                //serverResponse = "serverProblem";

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //serverResponse = "serverProblem";
        }
        return serverResponse;
    }

    public static void getProgressDialog(Context context,String msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public static void dismissDialog(){
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
