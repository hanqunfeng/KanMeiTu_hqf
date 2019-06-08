package com.example.kanmeitu_hqf.util;

import android.content.Context;
import android.widget.Toast;


/**
 * @author hanqf
 */
public class ToastUtil {

    private static Toast toast;

    static {
        toast = null;
    }

    public static void shortToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String message, int length_type) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), message, length_type);
        }
        toast.setText(message);
        toast.show();

    }
}
