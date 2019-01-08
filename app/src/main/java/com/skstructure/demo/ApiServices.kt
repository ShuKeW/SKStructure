package com.skstructure.demo

import com.skstructure.modules.retrofit2.http.GET
import com.skstructure.modules.retrofit2.http.Query

/**
 *  @date 2019/1/2   3:46 PM
 *  @author weishukai
 *  @describe
 */
interface ApiServices {
    @GET("/satinGodApi")
    fun loadJokeList(@Query("type") type: Int, @Query("page") page: Int): JokeData

    @GET("/satinCommentApi")
    fun loadJokeComment(@Query("id") id: Long, @Query("page") page: Int): JokeComment
}