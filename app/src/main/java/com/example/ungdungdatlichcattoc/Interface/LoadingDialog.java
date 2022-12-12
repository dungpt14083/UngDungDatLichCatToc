package com.example.ungdungdatlichcattoc.Interface;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.example.ungdungdatlichcattoc.R;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;
    public LoadingDialog(Activity mactivity)
    {
        activity = mactivity;
    }
  public   void startLoadingDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog_dialog,null));
        dialog=builder.create();
        dialog.show();

    }
 public    void dismissDialog(){
        dialog.dismiss();
    }
}
