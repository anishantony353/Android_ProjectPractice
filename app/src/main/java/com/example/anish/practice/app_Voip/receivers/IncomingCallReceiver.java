package com.example.anish.practice.app_Voip.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.anish.practice.app_Voip.WalkieTalkieActivity;

/**
 * Created by Anish on 13-02-2018.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    /**
     * Processes the incoming call, answers it, and hands it over to the
     * WalkieTalkieActivity.
     * @param context The context under which the receiver is running.
     * @param intent The intent being received.
     */
    String TAG = "IncomingCallReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        WalkieTalkieActivity wtActivity = (WalkieTalkieActivity) context;
        wtActivity.callReceived(intent);

        /*
        SipAudioCall incomingCall = null;
        try {
            SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                @Override
                public void onRinging(SipAudioCall call, SipProfile caller) {
                    Log.i(TAG,"onRinging()");
                    try {

                        call.answerCall(30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCallEstablished(SipAudioCall call) {
                    //super.onCallEstablished(call);
                    Log.i(TAG,"onCallEstablished()");
                }

                @Override
                public void onCallEnded(SipAudioCall call) {
                    //super.onCallEnded(call);
                    Log.i(TAG,"onCallEnded()");
                }
            };
            WalkieTalkieActivity wtActivity = (WalkieTalkieActivity) context;
            incomingCall = wtActivity.mSipManager.takeAudioCall(intent, listener);
            incomingCall.answerCall(30);
            incomingCall.startAudio();
            incomingCall.setSpeakerMode(true);
            if(incomingCall.isMuted()) {
                incomingCall.toggleMute();
            }
            wtActivity.call = incomingCall;
            wtActivity.updateStatus(incomingCall);
        } catch (Exception e) {
            if (incomingCall != null) {
                incomingCall.close();
            }
        }
        */
    }
}
