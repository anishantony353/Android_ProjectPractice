package com.example.anish.practice.app_Voip;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anish.practice.R;
import com.example.anish.practice.app_Voip.receivers.IncomingCallReceiver;

import java.text.ParseException;


public class WalkieTalkieActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "WalkieTalkieActivity";

    public SipManager mSipManager = null;
    public SipProfile mSipProfile = null;
    public SipAudioCall call = null;

    public IncomingCallReceiver callReceiver;
    public SipAudioCall.Listener listener;

    public Button BT_call,BT_receive;
    public TextView TV_msg;

    boolean isOnCall= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkie_talkie_activity);

        initializeViews();

        registerReceiver();

        // setting up SIP Manager
        if (mSipManager == null) {
            mSipManager = SipManager.newInstance(this);
        }

        //setting up SIP Profile
        SipProfile.Builder builder = null;
        try {
            builder = new SipProfile.Builder("8008", "172.17.47.17");
            builder.setPassword("goautodial");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mSipProfile = builder.build();

        setRegistrationListner();
        openProfile();
        createCallListner();


    }

    private void createCallListner() {
         listener = new SipAudioCall.Listener() {
            @Override
            public void onRinging(SipAudioCall call, SipProfile caller) {
                Log.i(TAG,"onRinging()");

                BT_call.setVisibility(View.GONE);
                TV_msg.setText("Incoming Call...");
                try {

                    //call.answerCall(30);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCallEstablished(SipAudioCall call) {
                //super.onCallEstablished(call);
                Log.i(TAG,"onCallEstablished()");
                call.startAudio();
                call.setSpeakerMode(true);
                if(call.isMuted()) {
                    call.toggleMute();
                }

                TV_msg.setText("On Call...");
                BT_receive.setText("End");
                isOnCall = true;
            }

            @Override
            public void onCallEnded(SipAudioCall call) {
                //super.onCallEnded(call);
                Log.i(TAG,"onCallEnded()");
                TV_msg.setText("Call Ended...");
                BT_receive.setText("Receive");
                isOnCall = false;
            }
        };
    }

    private void initializeViews() {
        BT_call = findViewById(R.id.BT_call);
        BT_call.setOnClickListener(this);
        BT_receive = findViewById(R.id.BT_receive);
        BT_receive.setOnClickListener(this);
        TV_msg = findViewById(R.id.TV_msg);
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        this.registerReceiver(callReceiver, filter);
    }

    private void openProfile() {
        Intent intent = new Intent();
        intent.setAction("android.SipDemo.INCOMING_CALL");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, Intent.FILL_IN_DATA);
        try {
            mSipManager.open(mSipProfile, pendingIntent, null);
        } catch (SipException e) {
            e.printStackTrace();
        }
    }

    private void setRegistrationListner() {

        try {
            mSipManager.setRegistrationListener(mSipProfile.getUriString(), new SipRegistrationListener() {

                public void onRegistering(String localProfileUri) {
                    //updateStatus("Registering with SIP Server...");
                    Log.i(TAG,"onRegistering()");
                }

                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    //updateStatus("Ready");
                    Log.i(TAG,"onRegisteringDone()");
                }

                public void onRegistrationFailed(String localProfileUri, int errorCode,
                                                 String errorMessage) {
                    //updateStatus("Registration failed.  Please check settings.");
                    Log.i(TAG,"onRegisteringFailed()");
                }
            });
        } catch (SipException e) {
            e.printStackTrace();
        }

    }

    public void closeLocalProfile() {
        if (mSipManager == null) {
            return;
        }
        try {
            if (mSipProfile != null) {
                mSipManager.close(mSipProfile.getUriString());
            }
        } catch (Exception ee) {
            Log.d(TAG, "Failed to close local profile.", ee);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.BT_call:
                try {
                    call = mSipManager.makeAudioCall(mSipProfile.getUriString(), "8008@172.17.47.17", listener, 30);
                } catch (SipException e) {
                    e.printStackTrace();
                    Log.i(TAG,"Exception while calling");
                }
                break;

            case R.id.BT_receive:
                if(call != null){
                    if(!isOnCall){
                        try {
                            call.answerCall(30);
                        } catch (SipException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            call.endCall();

                        } catch (SipException e) {
                            e.printStackTrace();
                        }
                    }


                }

                break;

        }

    }

    public void callReceived(Intent intent){
        Log.i(TAG,"callReceived()");
        try {
            call = mSipManager.takeAudioCall(intent,listener);


        } catch (SipException e) {
            e.printStackTrace();
        }

    }
}
