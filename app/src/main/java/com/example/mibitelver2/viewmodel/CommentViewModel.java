package com.example.mibitelver2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mibitelver2.modeltien.comment.Comment;
import com.example.mibitelver2.modeltien.putpostmodel.CommentPost;
import com.example.mibitelver2.modeltien.putpostmodel.LikeComment;
import com.example.mibitelver2.modeltien.putpostmodel.Likeput;
import com.example.mibitelver2.modeltien.putpostmodel.ReportPost;
import com.example.mibitelver2.repository.MovieRepository;

public class CommentViewModel extends AndroidViewModel {

    public static String inputComnet;
    public MutableLiveData<Comment> commentsParent = new MutableLiveData<>();
    public MutableLiveData<Comment> comment = new MutableLiveData<>();
    // id comment cha
    private MutableLiveData<String> idCommentRespons = new MutableLiveData<>();
    private MovieRepository movieRepository = new MovieRepository();
    // id comment report
    private MutableLiveData<Integer> idCommentReport = new MutableLiveData<>();




    public CommentViewModel(@NonNull Application application) {
        super(application);
    }


    //get
    public void getCommentsParent(String id, String idUser) {
        commentsParent = movieRepository.getCommentsParent(id, idUser);

    }

    public int getIdCommentReport() {
        return idCommentReport.getValue();
    }

    public void getComment(String id, String idUser) {
        comment = movieRepository.getComment(id, idUser);
    }

    public String getinput() {
        return inputComnet;
    }

//set
    public void setIcCommentResponse(String id) {
        idCommentRespons.setValue(id);

    }

    public void setIdCommentReport(int id) {
        idCommentReport.setValue(id);
    }

//pot & put API
    public void addComment(int idVideo, int idUser, String content) {

        String id = "";
        id = idCommentRespons.getValue();

        if (id.equals("0") != true) {
            movieRepository.postComment(new CommentPost(content, "2", "0", idUser, idVideo, Integer.parseInt(id)));
            setIcCommentResponse("0");

        } else {
            movieRepository.postComment(new CommentPost(content, "1", "0", idUser, idVideo, 1));


        }


    }
    public void putLikeComment(LikeComment likeComment){
        movieRepository.putLikeComment(likeComment);
    }

    public void postReport(ReportPost reportPost) {
        movieRepository.postReport(reportPost);
    }

    public void putLike(Likeput likeput) {
        movieRepository.putLike(likeput);
    }

}
