package com.example.mibitelver2.api;

import com.example.mibitelver2.model.videoInfo.VideoInfo;
import com.example.mibitelver2.modeltien.channel.Channel;
import com.example.mibitelver2.modeltien.comment.Comment;
import com.example.mibitelver2.modeltien.hashtag.HashTag;
import com.example.mibitelver2.modeltien.putpostmodel.CommentPost;
import com.example.mibitelver2.modeltien.putpostmodel.LikeComment;
import com.example.mibitelver2.modeltien.putpostmodel.Likeput;
import com.example.mibitelver2.modeltien.putpostmodel.PostFollower;
import com.example.mibitelver2.modeltien.putpostmodel.ReportPost;
import com.example.mibitelver2.modeltien.putpostmodel.WhatLatePut;
import com.example.mibitelver2.modeltien.responsecoment.ResponseCommentSend;
import com.example.mibitelver2.modeltien.responselikecommnet.ResponseLikeComment;
import com.example.mibitelver2.modeltien.responsepostfollower.ResponsePostFollower;
import com.example.mibitelver2.modeltien.responsepostlike.ResponsePostLike;
import com.example.mibitelver2.modeltien.responsereport.ResponsePostReport;
import com.example.mibitelver2.modeltien.responsewhatlate.ResponseWhatLate;
import com.example.mibitelver2.modeltien.video.VideoAcount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("videos/one?")
    Call<VideoAcount> getPost(@Query("id_video") String id,
                              @Query("id_user") String iduser);

    @GET("channels")
    Call<Channel> getChannel(@Query("id_video") String id,
                             @Query("id_user") String iduser);

    @GET("comments/video/{idVideo}")
    Call<Comment> getCommentsParent(@Path("idVideo") String idVideo, @Query("id_user") String iduser,
                                    @Query("offset") int offset,
                                    @Query("limit") int limit);

    @GET("comments/parent/{id_parent}")
    Call<Comment> getComments(@Path("id_parent") String id,
                              @Query("id_user") String iduser, @Query("offset") int offset,
                              @Query("limit") int limit);

    @GET(" hashtags/12")
    Call<HashTag> getHashTag(@Query("id_video") String id);


    @POST("comments")
    Call<ResponseCommentSend> postComment(@Body CommentPost commentPost);


    @PUT("videos/like")
    Call<ResponsePostLike> putLike(@Body Likeput likeput);

    @PUT("comments/like")
    Call<ResponseLikeComment>putLikeComment(@Body LikeComment likeComment);

    @PUT("user_video/viewlate")
    Call<ResponseWhatLate> putWhatLate(@Body WhatLatePut whatLatePut);

    @POST("followers/one")
    Call<ResponsePostFollower> postFollowers(@Body PostFollower postFollower);


    @HTTP(method = "DELETE", path = "followers", hasBody = true)
    Call<ResponsePostFollower> deleteFollowers(@Body PostFollower postFollower);


    @POST("reports")
    Call<ResponsePostReport> postReport(@Body ReportPost reportPost);

    @GET("videos/info")
    Call<VideoInfo> getVideoInfo(
            @Query("id_video") int videoId,
            @Query("id_user") int userId
    );


}
