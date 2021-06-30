package com.example.mibitelver2.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.adapter.VideoAdapter;
import com.example.mibitelver2.databinding.ActivityAllVideoBinding;
import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.model.tumodel.HashTag;
import com.example.mibitelver2.model.tumodel.HashTagData;
import com.example.mibitelver2.model.tumodel.Video;
import com.example.mibitelver2.model.tumodel.VideoDATA;
import com.example.mibitelver2.model.tumodel.VideoN;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.util.VideoItemOnClick;
import com.example.mibitelver2.viewmodel.ChannelViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class AllVideoActivity extends AppCompatActivity implements VideoItemOnClick {

    private ActivityAllVideoBinding binding;
    private List<Video> videos = new ArrayList<>();
    private int offset = 0;
    private int limit = 10;
    private boolean canRead = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        int type = getIntent().getIntExtra("type",0);

        VideoAdapter adapter = new VideoAdapter(videos,this,this);
        binding.allVideoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.allVideoRecyclerView.setAdapter(adapter);

        if (type == 0){
            DataClient dataClient = APIUtils.getData(Constants.base_url);
            Call<VideoDATA> getListVideoMostView = dataClient.getListVideoMostView(offset++, limit);
            setVideoToRecyclerView(getListVideoMostView,adapter);

            binding.allVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Boolean read = !recyclerView.canScrollVertically(1);
                    if(read == true && canRead == true){
                        binding.progessBar.setVisibility(View.VISIBLE);
                        Toast.makeText(AllVideoActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                        Call<VideoDATA> getListVideoMostView = dataClient.getListVideoMostView(offset++, limit);
                        setVideoToRecyclerView(getListVideoMostView,adapter);
                    }
                }
            });
        }else{
            binding.allVideoTitle.setText("Video được đề xuất");
            DataClient dataClient = APIUtils.getData(Constants.base_url);
            Call<VideoDATA> getListVideoRecommend = dataClient.getListVideoRecommend(offset++, limit);
            setVideoToRecyclerView(getListVideoRecommend, adapter);
            binding.allVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Boolean read = !recyclerView.canScrollVertically(1);
                    if(read == true && canRead == true){
                        binding.progessBar.setVisibility(View.VISIBLE);
                        Toast.makeText(AllVideoActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                        Call<VideoDATA> getListVideoRecommend = dataClient.getListVideoRecommend(offset++, limit);
                        setVideoToRecyclerView(getListVideoRecommend, adapter);
                    }
                }
            });

        }

        binding.cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    public void init(){

    }

    public void setVideoToRecyclerView(Call<VideoDATA> getListVideo, VideoAdapter adapter){
        getListVideo.enqueue(new Callback<VideoDATA>() {
            @Override
            public void onResponse(Call<VideoDATA> call, Response<VideoDATA> response) {
                if (response.body().getListVideo() == null || response.body().getListVideo().size() == 0){
                    canRead = false;
                    binding.progessBar.setVisibility(View.GONE);
                    Toast.makeText(AllVideoActivity.this, "Hết video!!!", Toast.LENGTH_SHORT).show();
                }
                if (response.body().getListVideo() != null){
                    for (VideoN videoN : response.body().getListVideo()){
                        Video video = new Video();
                        binding.progessBar.setVisibility(View.GONE);
                        int idVideo = videoN.getIdVideo();

                        video.setIdVideo(idVideo);
                        video.setThumbnail(videoN.getLinkHorizontalCoverImage());
                        video.setNameVideo(videoN.getTitle());
                        String timeLine = videoN.getUpdateAt();
                        video.setTimeLine(timeLine);
                        video.setView(videoN.getTotalView());



                        ChannelViewModel channelViewModel = new ViewModelProvider(getViewModelStore(), new ChannelViewModel(idVideo,2))
                                .get(ChannelViewModel.class);
                        channelViewModel.getChannelLiveData().observe(AllVideoActivity.this, new Observer<ChannelN>() {
                            @Override
                            public void onChanged(ChannelN channelN) {
                                if (channelN != null){
                                    video.setImgAvata(channelN.getMuralPhoto());
                                    video.setNameUser(channelN.getNickName());
                                    Log.d("video", video.getImgAvata());
                                }else{
                                    video.setImgAvata("");
                                    video.setNameUser("");
                                    Log.d("video", video.getImgAvata());
                                }
                            }
                        });

                        DataClient dataClient1 = APIUtils.getData(Constants.base_url);
                        Call<HashTagData> getHashTag = dataClient1.getListHashTagByVideoId(idVideo);
                        getHashTag.enqueue(new Callback<HashTagData>() {
                            @Override
                            public void onResponse(Call<HashTagData> call, Response<HashTagData> response) {
                                String hashTag = "#";
                                for (HashTag hag : response.body().getListHashTag()){
                                    hashTag += hag.getTitle() + " #";
                                }

                                if (hashTag.length() > 2){
                                    hashTag = hashTag.substring(0,hashTag.length() - 2);
                                }
                                if (hashTag.length() == 1){
                                    hashTag = "";
                                }

                                video.setHashTag(hashTag);
                                Log.d("video", video.getHashTag());
                                videos.add(video);
                                Log.d("video", "video size = " + videos.size());
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<HashTagData> call, Throwable t) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<VideoDATA> call, Throwable t) {

            }
        });
    }

    @Override
    public void clickVideo(int idVideo) {
        Toast.makeText(this, idVideo + "idvideo", Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(AllVideoActivity.this,VideoActivity.class );
         intent.putExtra("idVideo", idVideo);
         startActivity(intent);
    }
}