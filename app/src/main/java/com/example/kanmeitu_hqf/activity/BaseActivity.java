package com.example.kanmeitu_hqf.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kanmeitu_hqf.util.SharedPreferencesUtil;

/**
 * @author hanqf
 */
public class BaseActivity extends AppCompatActivity {

    protected SharedPreferencesUtil sp;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
}
