package com.example.anish.practice.app_payUmoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.anish.practice.R;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity_payUmoney extends AppCompatActivity {
    Context context = this;
    Handler handler;
    String TAG = "MainActivity_payUmoney";

    String merchantId = "4928947";//"5956553"; // "4928947" test
    String key = "7USQfv";//"1c0Rhwex"; //"7USQfv" test
    String salt = "ZoAUGtJB";//"D8J9M8A7cl"; // "ZoAUGtJB" test

    String txn_id = "1854785458457885";
    double amount = 10;
    String productinfo = "pencil";
    String firstname = "anish";
    String email = "anishantony353@gmail.com";

    String u_firstname = "anish";
    String u_email = "anishantony353@gmail.com";
    String u_phone = "8097835308";
    String udf1 = "";
    String udf2 = "";
    String udf3 = "";
    String udf4 = "";
    String udf5 = "";

    PayUmoneySdkInitializer.PaymentParam.Builder builder;
    PayUmoneySdkInitializer.PaymentParam paymentParam;
    String hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pay_umoney);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                PayUmoneyFlowManager.startPayUMoneyFlow(
                        paymentParam,
                        MainActivity_payUmoney.this,
                        R.style.PayU_AppTheme_Base,
                        false);

                //PayUmoneySdkInitializer.startPaymentActivityForResult(MainActivity_payUmoney.this,paymentParam);
                return true;
            }
        });




    }

    public void makePayment(View view) {

        //new Thread(new Task()).start();

        String hashSequence = key+"|"+txn_id+"|"+amount+"|"+productinfo+"|"+firstname+"|"+email+"|"+udf1+"|"+udf2
                +"|"+udf3+"|"+udf4+"|"+udf5+"||||||"+salt;
        hash= hashCal("SHA-512", hashSequence);


        builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txn_id)                                             // Transaction ID
                .setPhone(u_phone)                                           // User Phone number
                .setProductName(productinfo)                   // Product Name or description
                .setFirstName(u_firstname)                              // User First name
                .setEmail(u_email)                                            // User Email ID
                .setsUrl("https://test.payumoney.com/mobileapp/payumoney/success.php")                    // Success URL (surl)
                .setfUrl("https://test.payumoney.com/mobileapp/payumoney/failure.php")                     //Failure URL (furl)
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(key)                        // Merchant key
                .setMerchantId(merchantId);             // Merchant ID

        //declare paymentParam object
        paymentParam = builder.build();
        //set the hash
        paymentParam.setMerchantHash(hash);

        // Invoke the following function to open the checkout page.
        PayUmoneyFlowManager.startPayUMoneyFlow(
                paymentParam,
                MainActivity_payUmoney.this,
                R.style.PayU_AppTheme_Base,
                false);


    }

    public static String hashCal(String type, String hashString) {
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(type);
            messageDigest.update(hashString.getBytes());
            byte[] mdbytes = messageDigest.digest();
            for (byte hashByte : mdbytes) {
                hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result Code is -1 send from Payumoney activity
        Log.d("WalkieTalkieActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {

            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            Log.i(TAG,"PayuResponse:"+transactionResponse.getPayuResponse());
            Log.i(TAG,"MerchantResponse:"+transactionResponse.getTransactionDetails());

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if(transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)){
                    //Success Transaction
                    Log.i(TAG,"Success Transaction");
                } else{
                    //Failure Transaction
                    Log.i(TAG,"Failure Transaction");
                }


                // Response from Payumoney
               // String payuResponse = transactionResponse.getPayuResponse();
                //Log.i(TAG,"Payu Response:"+payuResponse);
                // Response from SURl and FURL
                //String merchantResponse = transactionResponse.getTransactionDetails();
            }  else if (resultModel != null && resultModel.getError() != null) {

                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }
        }
    }


    /*
    public void makePayment(View view) {

        new Thread(new Task()).start();



    }

    class Task implements Runnable {

        @Override
        public void run() {

            hash = createHash();
            Log.i(TAG,"Hash : "+hash);
            builder = createParamBuilder();
            paymentParam = builder.build();
            paymentParam.setMerchantHash(hash);

            handler.sendEmptyMessage(1);


        }
    }

    private PayUmoneySdkInitializer.PaymentParam.Builder createParamBuilder() {
        builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txn_id)                                             // Transaction ID
                .setPhone(u_phone)                                           // User Phone number
                .setProductName(productinfo)                   // Product Name or description
                .setFirstName(u_firstname)                              // User First name
                .setEmail(u_email)                                            // User Email ID
                .setsUrl("https://test.payumoney.com/mobileapp/payumoney/success.php")                    // Success URL (surl)
                .setfUrl("https://test.payumoney.com/mobileapp/payumoney/failure.php")                     //Failure URL (furl)
                .setUdf1(udf1)
                .setUdf2(udf2)
                .setUdf3(udf3)
                .setUdf4(udf4)
                .setUdf5(udf5)
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(key)                        // Merchant key
                .setMerchantId(merchantId);             // Merchant ID

        return builder;
    }

    private String createHash() {

        String hashSequence = key+"|"+txn_id+"|"+amount+"|"+productinfo+"|"+firstname+"|"+email+"+"+udf1+"|"+udf2+"|"+udf3+"|"+udf4
                +"|"+udf5+"||||||"+salt;

        return hashCal("SHA-512", hashSequence);

    }


    // method to create Hash
    public static String hashCal(String type, String hashString) {
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(type);
            messageDigest.update(hashString.getBytes());
            byte[] mdbytes = messageDigest.digest();
            for (byte hashByte : mdbytes) {
                hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    */
}
