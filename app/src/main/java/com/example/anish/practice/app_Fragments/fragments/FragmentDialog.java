package com.example.anish.practice.app_Fragments.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.practice.R;

/**
 * Created by Anish on 17-08-2017.
 */

public class FragmentDialog extends DialogFragment {

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Dialog title from Frag");
//        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog,null);
//        builder.setView(view);
//        //builder.setMessage("Dialog msg from Frag");
////        builder.setMultiChoiceItems(R.array.title, null, new DialogInterface.OnMultiChoiceClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
////                Toast.makeText(getActivity(),"Item "+i+" :"+b,Toast.LENGTH_SHORT).show();
////            }
////        });
//        Dialog dialog = builder.create();
//        Log.i("DialogFragment","About to return Dialog from onCreateDialog()");
//        return dialog;
//    }
    public Dialog getDialogInActivity(){
        Dialog dialog = getDialog();
        Log.i("DialogFragment","from getDialogInActivity()");
        //dialog.setTitle("New Title as per Activity");
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog,null);
        Bundle bundle = getArguments();


        //Log.i("Dialog","bundle value:"+((Dialog.ArgClass) bundle.getSerializable("obj")).str);

        return view;
    }
}
