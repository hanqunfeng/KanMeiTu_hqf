package com.example.kanmeitu_hqf.api;


import com.example.kanmeitu_hqf.domain.Image;
import com.example.kanmeitu_hqf.domain.response.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by smile on 02/03/2019.
 */
public interface Service {
    /**
     * 获取图片列表
     *
     * @return
     */
    @GET("v1/images")
    Observable<ListResponse<Image>> images();
}
