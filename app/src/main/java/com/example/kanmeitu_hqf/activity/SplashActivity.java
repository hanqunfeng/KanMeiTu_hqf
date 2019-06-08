package com.example.kanmeitu_hqf.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kanmeitu_hqf.MainActivity;
import com.example.kanmeitu_hqf.R;
import com.example.kanmeitu_hqf.util.SharedPreferencesUtil;

/**
 * @author hanqf
 */
public class SplashActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            next();
        }


    };
    private SharedPreferencesUtil sp;

    private void next() {

        Intent intent = null;
        if (sp.isLogin()) {
            //已经登录，跳转到首页
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            //否则跳转到登录界面
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }
        startActivity(intent);

        //关闭当前界面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sp = SharedPreferencesUtil.getInstance(getApplicationContext());

        handler.postDelayed(() -> {
            //3秒钟后调用，这里

            //这里调用是在子线程，不能直接操作UI，需要用handler切换到主线程
            //用多个线程的目的解决，如果有耗时任务，那么就会卡界面
            //而用了多线程后，将耗时任务放到子线程，这样主线程(UI线程)就不会卡主
            handler.sendEmptyMessage(0);
        }, 3000);
    }
}
