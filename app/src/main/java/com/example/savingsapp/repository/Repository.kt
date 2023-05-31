package com.example.savingsapp.repository

import com.example.savingsapp.api.RetrofitInstance
import com.example.savingsapp.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(auth:String) : Response<Post> {
        return RetrofitInstance.api.getPost(auth)

    }

    suspend fun getPost2(number: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }


    suspend fun getCustomPosts(userId: Int, sort: String, order:String): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId, sort, order)
    }

    suspend fun getCustomPosts2(userId: Int, options:Map<String, String>): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPosts2(userId,options)
    }

    suspend fun pushpost(post: Post): Response<Post>{
        return RetrofitInstance.api.pushpost(post)
    }
    suspend fun pushpost2(userId: Int, id:Int, title:String, body:String): Response<Post>{
        return RetrofitInstance.api.pushpost2(userId, id, title, body)
    }



}