package com.JZIndenstries.unitkeeping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LoadingDialog {

    Activity activity;


    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void showLoadingDialog(Activity activity) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();
    }

    void dismissLoadingDialog(Activity activity){

    }
}