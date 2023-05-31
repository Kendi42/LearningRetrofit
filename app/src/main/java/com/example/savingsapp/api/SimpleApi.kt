package com.example.savingsapp.api

import com.example.savingsapp.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

//    @Headers(
//        "Authorization: 123123123",
//        "Platform: Android"
//    )
    @GET("posts/1")
    suspend fun getPost(@Header("Auth") auth: String): Response<Post>


    // Dynamically get end point from user
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ): Response<Post>

    // Implementing custom sort and order
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
        ): Response<List<Post>>

    // Using a hash map to reduce crowded code
    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId: Int,
        @QueryMap options:Map<String, String>
    ): Response<List<Post>>

    // Implementing a POST request to the server
    @POST("posts")
    suspend fun pushpost(
        // THis information will feature in the request Body of the post request
        @Body post: Post
    ): Response<Post>

    // FormURLEncoded format
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushpost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String

    ): Response<Post>



}