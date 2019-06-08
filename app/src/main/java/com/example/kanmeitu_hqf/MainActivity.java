package com.example.kanmeitu_hqf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kanmeitu_hqf.activity.LoginActivity;
import com.example.kanmeitu_hqf.adapter.ImageAdapter;
import com.example.kanmeitu_hqf.api.Api;
import com.example.kanmeitu_hqf.domain.Image;
import com.example.kanmeitu_hqf.domain.response.ListResponse;
import com.example.kanmeitu_hqf.util.SharedPreferencesUtil;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesUtil sp;

    private RecyclerView rv;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());

        rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        //显示2列，我们这里实现的是每个图片的宽高都是一样
        //最好的效果其实是根据图片高度和宽度来缩放
        //因为每一张图片大小不一样
        //但这样实现涉及到的知识点很多

        //这里使用了GridLayoutManager
        //他会显示类似9宫格布局效果

        //详细的可以学习这门课程
        //http://www.ixuea.com/courses/8
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);

        adapter = new ImageAdapter(this);
        rv.setAdapter(adapter);

        //设置数据
        ArrayList<Image> datas = new ArrayList<>();
//        测试空数据
//        for (int i = 1; i < 100; i++) {
//            datas.add(new Image(""));
//        }

        for (int i = 1; i < 20; i++) {
            //图片来自http://image.baidu.com
            datas.add(new Image(String.format("http://dev-courses-quick.oss-cn-beijing.aliyuncs.com/%d.jpg", i)));
        }
        adapter.setData(datas);

//        fetchData();
    }

    public void onLogoutClick(View view) {
        sp.setLogin(false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 对应的网址已经不可以使用了
     */
    private void fetchData() {
        //这里涉及到很多知识，可以够讲几门课程了
        //在这里大家只需简单理解，这样写就能请求到数据就行了
        Api
                .getInstance()
                .images()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResponse<Image> imageListResponse) {
                        //当数据请求完毕后，他会解析成对象，并返回给我们
                        //真实项目中还会进行一系列的错误处理

                        //请参考我们的《Android开发项目实战之我的云音乐》课程
                        //http://www.ixuea.com/courses/10
                        adapter.setData(imageListResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //真实项目中会将错误，提示给用户
                        //同时写到日志
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
