package com.fountane.www.fountanehrapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import com.fountane.www.fountanehrapp.R;

public class Utils {

    public static ProgressDialog progressDialog;

    public static void displayProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progress_dialog);
    }

    public static void dismissProgressDialog() {
        if (progressDialog.isShowing() && progressDialog != null) {
            progressDialog.dismiss();
        }
    }



}
