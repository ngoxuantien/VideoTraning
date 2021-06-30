package com.example.mibitelver2.retrofit;

import com.example.mibitelver2.model.tumodel.CategoryDATA;
import com.example.mibitelver2.model.tumodel.CategoryPost;
import com.example.mibitelver2.model.tumodel.ChannelDATA;
import com.example.mibitelver2.model.tumodel.ChannelListDATA;
import com.example.mibitelver2.model.tumodel.ChannelPost;
import com.example.mibitelver2.model.tumodel.HashTagData;
import com.example.mibitelver2.model.tumodel.UserPost;
import com.example.mibitelver2.model.tumodel.UserResponse;
import com.example.mibitelver2.model.tumodel.VideoDATA;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DataClient {

    // get random category
    @Headers({
            "Content-TypePet: application/json"
    })
    @GET("categories/random/")
    Call<CategoryDATA> getListCategory();

    // PostCategory
    @Headers({
            "Content-TypePet: application/json"
    })
    @POST("categories/")
    Call<Void> postCategoryUser(@Body CategoryPost categoryPost);

    // get list category by user id = ?
    @Headers({
            "Content-TypePet: application/json"
    })
    @GET("categories/{id_user}")
    Call<CategoryDATA> getListCategoryByUserId(@Path("id_user") int user_id);

    //get all video of category
    @GET("videos/categories/{id}")
    Call<VideoDATA> getListVideoByCategoryId(@Path("id") int idCategory,
                                             @Query("offset") int offset,
                                             @Query("limit") int limit
                                             );

    //get all hashtag of video
    @GET("hashtags/12")
    Call<HashTagData> getListHashTagByVideoId(@Query("id_video") int idVideo);

    // get channel of video
    @GET("channels")
    Call<ChannelDATA> getChannelOfVideo(@Query("id_video") int idVideo,
                                        @Query("id_user") int idUser);

    // get list channel of category - mh03
    @GET("channels/categories/{id}")
    Call<ChannelListDATA> getChannelList(@Path("id") int id);

    // theo doi nhieu kenh
    @POST("followers")
    Call<Void> postChannelUserFollow(@Body ChannelPost channelPost);

    // list video xem nhiều nhất amount = 5 -> 5 video, amuont = 0 -> all video
    //http://localhost:9999/api/v1/videos/view?amount=5
    @GET("videos/view")
    Call<VideoDATA> getListVideoMostView(@Query("offset") int offset,
                                         @Query("limit") int limit);

    // list video đề xuất amount = 0 -> all, amount = 5 -> 5
    //http://localhost:9999/api/v1/videos/recommend?amount=5
    @GET("videos/recommend")
    Call<VideoDATA> getListVideoRecommend(@Query("offset") int offset,
                                          @Query("limit") int limit);

    // login
    @POST("authenticate")
    Call<UserResponse> userLogin(@Body UserPost user);


}
