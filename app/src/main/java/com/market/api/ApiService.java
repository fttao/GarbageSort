package com.market.api;

import com.market.model.TrashNewsResponse;
import com.market.model.TrashSearchResponse;
import com.market.model.WallPaperResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.market.utils.Constant.KEY;

/**
 * author: ft
 * created on: 2022/8/5 16:37
 * description:ApiService接口 统一管理应用所有的接口
 */
public interface ApiService {

    /**
     * 获取热门壁纸列表
     */
    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    Observable<WallPaperResponse> getWallPaper();

    /**
     * 垃圾分类新闻
     * @param num 数量
     * @return TrashNewsResponse 结果实体
     */
    @GET("/lajifenleinews/index?key=" + KEY)
    Observable<TrashNewsResponse> getTrashNews(@Query("num") Integer num);

    /**
     * 获取热门壁纸列表
     */
    @GET("lajifenlei/index?key=" + KEY)
    Observable<TrashSearchResponse> searchResult(@Query("word") String word);
}
