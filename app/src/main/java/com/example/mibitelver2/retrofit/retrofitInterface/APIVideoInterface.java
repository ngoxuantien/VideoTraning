package com.example.mibitelver2.retrofit.retrofitInterface;

import com.example.mibitelver2.model.allHashtag.AllHashtag;
import com.example.mibitelver2.model.channel.Channel;
import com.example.mibitelver2.model.oneHashtag.OneHashtag;
import com.example.mibitelver2.model.video.OneVideo;
import com.example.mibitelver2.model.video.ListVideo;
import com.example.mibitelver2.model.video.RecentlyViewedVideo;
import com.example.mibitelver2.model.videoInfo.VideoInfo;
import com.example.mibitelver2.model.videoInfo.VideoInfoData;
import com.example.mibitelver2.model.videoInfo.VideoLike;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIVideoInterface {

    @GET("videos/one")
    Call<OneVideo> getVideoActivity(@Query("id_user") int idUser,
                                    @Query("id_video") int idVideo);

    @GET("user_video/users/{id}")
    Call<ListVideo> getListVideo(
            @Path("id") int userId,
            @Query("amount") int amount
    );

    @GET("videos/hashtags/{id}?sort=-view&size=0")
    Call<OneHashtag> getOneHashtagById(@Path("id") int id);

    @GET("videos/categories/1")
    Call<ListVideo> getAllVideosByCategory();

    @GET("hashtags")
    Call<AllHashtag> getAllHashtag();

    @GET("channels")
    Call<Channel> getChannelData(@Query("id_video") int video_id);

    @GET("videos/info")
    Call<VideoInfo> getVideoInfo(
            @Query("id_video") int videoId,
            @Query("id_user") int userId
    );

    @GET("user_video/viewed/user/{id}")
    Call<RecentlyViewedVideo> getRecentVideos(@Path("id") int user_id,
                                              @Query("offset") int offset,
                                              @Query("limit") int limit);

    @GET("user_video/user/{id}?amount=0")
    Call<RecentlyViewedVideo> getAllViewedVideos(@Path("id") int user_id);

    @GET("videos/recommend")
    Call<RecentlyViewedVideo> getRecommendVideo(@Query("offset") int offset,
                                                @Query("limit") int limit);

    @PUT("videos/like")
    Call<VideoLike> setLikeVideo(
            @Body VideoLike videoLike
    );

    Call<VideoInfoData> setVideoInfo(int i, int i1, VideoInfoData data);
}
