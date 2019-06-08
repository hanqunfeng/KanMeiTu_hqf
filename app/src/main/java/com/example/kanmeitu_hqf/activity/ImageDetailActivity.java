package com.example.kanmeitu_hqf.activity;


import android.os.Bundle;

import com.example.kanmeitu_hqf.R;
import com.example.kanmeitu_hqf.util.Constants;
import com.example.kanmeitu_hqf.util.ImageUtil;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * @author hanqf
 */
public class ImageDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        PhotoView pv = findViewById(R.id.pv);

        //获取传递过来的参数
        String url = getIntent().getStringExtra(Constants.ID);

        //显示图片
        ImageUtil.show(this, pv, url);
    }
}
