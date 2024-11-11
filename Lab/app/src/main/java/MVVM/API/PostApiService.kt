package MVVM.API

import MVVM.Model.PostModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostApiService {
    @GET("posts")
    suspend fun getUserPosts(): List<PostModel>

    @GET("posts/{id}")
    suspend fun getUserPostById(@Path("id") id: Int): PostModel

    @POST("posts")
    suspend fun createPost(@Body post: PostModel): PostModel

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: PostModel): PostModel

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Unit
}