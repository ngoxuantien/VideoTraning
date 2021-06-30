package com.example.mibitelver2.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mibitelver2.api.ApiClient;
import com.example.mibitelver2.api.ApiInterface;
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
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRepository {

    private ApiInterface apiInterface;

    //get API
    public MovieRepository() {
        apiInterface = ApiClient.getIntance().create(ApiInterface.class);
    }

    public MutableLiveData<VideoAcount> getVideo(String idVideo, String idUser) {
        MutableLiveData<VideoAcount> videoAcountMutableLiveData = new MutableLiveData<>();
        apiInterface.getPost(idVideo, idUser).enqueue(new Callback<VideoAcount>() {
            @Override
            public void onResponse(Call<VideoAcount> call, Response<VideoAcount> response) {
                if (response.isSuccessful()) {
                    videoAcountMutableLiveData.setValue(response.body());
                    //        Log.d("erro", response.body().getData().getLinkVideo());
                }
            }

            @Override
            public void onFailure(Call<VideoAcount> call, Throwable t) {

            }
        });
        return videoAcountMutableLiveData;
    }

    public MutableLiveData<Channel> getChannel(String id, String idUser) {
        MutableLiveData<Channel> channelMutableLiveData = new MutableLiveData<>();
        apiInterface.getChannel(id, idUser).enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                if ((response.isSuccessful())) {
                    channelMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {

            }
        });
        return channelMutableLiveData;
    }

    public MutableLiveData<Comment> getCommentsParent(String id, String idUser) {
        MutableLiveData<Comment> commentMutableLiveData = new MutableLiveData<>();
        apiInterface.getCommentsParent(id, idUser, 0, 200).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    commentMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
        return commentMutableLiveData;
    }

    public MutableLiveData<Comment> getComment(String id, String idUser) {
        MutableLiveData<Comment> commentMutableLiveData = new MutableLiveData<>();
        apiInterface.getComments(id, idUser, 0, 200).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    commentMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
        return commentMutableLiveData;
    }

    public MutableLiveData<HashTag> getHashTag(String id) {
        MutableLiveData<HashTag> hashTagMutableLiveData = new MutableLiveData<>();
        apiInterface.getHashTag(id).enqueue(new Callback<HashTag>() {
            @Override
            public void onResponse(Call<HashTag> call, Response<HashTag> response) {
                if (response.isSuccessful()) {
                    hashTagMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<HashTag> call, Throwable t) {

            }
        });
        return hashTagMutableLiveData;
    }

    public MutableLiveData<VideoInfo> getVideoInfor(int id_Video, int id_User) {
        MutableLiveData<VideoInfo> videoInfor = new MutableLiveData();
        apiInterface.getVideoInfo(id_Video, id_User).enqueue(new Callback<VideoInfo>() {
            @Override
            public void onResponse(Call<VideoInfo> call, Response<VideoInfo> response) {
                if (response.isSuccessful()) {
                    videoInfor.setValue(response.body());
                    Log.d("message getVideoInFor", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<VideoInfo> call, Throwable t) {
                Log.d("Error getVideoInfor", t.getMessage());
            }
        });
        return videoInfor;
    }


    //post API
    public void postComment(CommentPost commentPost) {
        apiInterface.postComment(commentPost).enqueue(new Callback<ResponseCommentSend>() {
            @Override
            public void onResponse(Call<ResponseCommentSend> call, Response<ResponseCommentSend> response) {
                Log.d("ddd", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseCommentSend> call, Throwable t) {

            }
        });


    }

    public void postFollower(PostFollower postFollower) {
        apiInterface.postFollowers(postFollower).enqueue(new Callback<ResponsePostFollower>() {
            @Override
            public void onResponse(Call<ResponsePostFollower> call, Response<ResponsePostFollower> response) {

            }

            @Override
            public void onFailure(Call<ResponsePostFollower> call, Throwable t) {

            }
        });
    }

    public void postReport(ReportPost reportPost) {
        apiInterface.postReport(reportPost).enqueue(new Callback<ResponsePostReport>() {
            @Override
            public void onResponse(Call<ResponsePostReport> call, Response<ResponsePostReport> response) {
                Log.d("messs", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePostReport> call, Throwable t) {

            }
        });
    }


    //put API
    public void putLike(Likeput likeput) {
        apiInterface.putLike(likeput).enqueue(new Callback<ResponsePostLike>() {
            @Override
            public void onResponse(Call<ResponsePostLike> call, Response<ResponsePostLike> response) {
                Log.d("loi put like", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePostLike> call, Throwable t) {
                Log.d("loi put like", t.getMessage());
            }
        });
    }

    public void putWhatLate(WhatLatePut whatLatePut) {
        apiInterface.putWhatLate(whatLatePut).enqueue(new Callback<ResponseWhatLate>() {
            @Override
            public void onResponse(Call<ResponseWhatLate> call, Response<ResponseWhatLate> response) {

            }

            @Override
            public void onFailure(Call<ResponseWhatLate> call, Throwable t) {

            }
        });
    }
    public void putLikeComment(LikeComment likeComment){
        apiInterface.putLikeComment(likeComment).enqueue(new Callback<ResponseLikeComment>() {
            @Override
            public void onResponse(Call<ResponseLikeComment> call, Response<ResponseLikeComment> response) {
                Log.d("messLikeComment", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseLikeComment> call, Throwable t) {

            }
        });
    }


    //delete API
    public void deleteFollower(PostFollower postFollower) {
        apiInterface.deleteFollowers(postFollower).enqueue(new Callback<ResponsePostFollower>() {
            @Override
            public void onResponse(Call<ResponsePostFollower> call, Response<ResponsePostFollower> response) {

            }

            @Override
            public void onFailure(Call<ResponsePostFollower> call, Throwable t) {

            }
        });
    }


}
