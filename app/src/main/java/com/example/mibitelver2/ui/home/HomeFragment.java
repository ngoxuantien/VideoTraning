package com.example.mibitelver2.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mibitelver2.view.AllVideoActivity;
import com.example.mibitelver2.adapter.CategoryAdapter;
import com.example.mibitelver2.adapter.HomeVideoHorizontalAdapter;
import com.example.mibitelver2.adapter.HomeVideoVerticalAdapter;
import com.example.mibitelver2.databinding.FragmentHomeBinding;
import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.model.tumodel.ChannelN;
import com.example.mibitelver2.model.tumodel.HashTag;
import com.example.mibitelver2.model.tumodel.HashTagData;
import com.example.mibitelver2.model.tumodel.Video;
import com.example.mibitelver2.model.tumodel.VideoDATA;
import com.example.mibitelver2.model.tumodel.VideoN;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.util.OnClickInterface;
import com.example.mibitelver2.util.VideoItemOnClick;
import com.example.mibitelver2.view.VideoActivity;
import com.example.mibitelver2.viewmodel.CategoryUserViewModel;
import com.example.mibitelver2.viewmodel.ChannelViewModel;
import com.example.mibitelver2.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Để ý hàm onClick để sửa lại - bỏ comment do server chưa có
 */

public class HomeFragment extends Fragment implements OnClickInterface, VideoItemOnClick {

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FragmentHomeBinding binding;
    private int limit = 5;
    private int offset = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setCategory();

        setMostVideoHorizontal();

        setRecommendVideoHorizontal();

        setVideoVertical(2);

        // view all list video nhiều view nhất
        binding.viewAllMostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllVideoActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
            }
        });

        // view all list video đề suất
        binding.viewAllVideoSuggess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllVideoActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });

        return view;

    }

    public void setCategory() {

        CategoryUserViewModel categoryUserViewModel = new ViewModelProvider(this, new CategoryUserViewModel(1))
                .get(CategoryUserViewModel.class);
        categoryUserViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                // set a LinearLayoutManager
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
                binding.homeRecyclerviewItemType.setLayoutManager(linearLayoutManager);

                binding.homeRecyclerviewItemType.setHasFixedSize(true);
                CategoryAdapter adapter = new CategoryAdapter(categories, getContext(), HomeFragment.this);
                binding.homeRecyclerviewItemType.setAdapter(adapter);
            }
        });


    }

    public void setRecommendVideoHorizontal() {
        List<Video> videos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        HomeVideoHorizontalAdapter adapter = new HomeVideoHorizontalAdapter(videos, getContext(), HomeFragment.this);
        binding.homeRecyclerviewItemVideo2.setLayoutManager(linearLayoutManager2);
        binding.homeRecyclerviewItemVideo2.setAdapter(adapter);
        binding.homeRecyclerviewItemVideo2.setHasFixedSize(true);

        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<VideoDATA> getListVideoRecommend = dataClient.getListVideoRecommend(offset, limit);
        getListVideoRecommend.enqueue(new Callback<VideoDATA>() {
            @Override
            public void onResponse(Call<VideoDATA> call, Response<VideoDATA> response) {


                for (VideoN videoN : response.body().getListVideo()) {
                    Video video = new Video();

                    int idVideo = videoN.getIdVideo();

                    video.setIdVideo(idVideo);
                    video.setThumbnail(videoN.getLinkHorizontalCoverImage());
                    video.setNameVideo(videoN.getTitle());
                    String timeLine = videoN.getUpdateAt();
                    video.setTimeLine(timeLine);
                    video.setView(videoN.getTotalView());


                    ChannelViewModel channelViewModel = new ViewModelProvider(getViewModelStore(),
                            new ChannelViewModel(idVideo, 2))
                            .get(ChannelViewModel.class);
                    channelViewModel.getChannelLiveData().observe(getViewLifecycleOwner(), new Observer<ChannelN>() {
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
                            for (HashTag hag : response.body().getListHashTag()) {
                                hashTag += hag.getTitle() + " #";
                            }

                            if (hashTag.length() > 2) {
                                hashTag = hashTag.substring(0, hashTag.length() - 2);
                            }
                            if (hashTag.length() == 1) {
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

            @Override
            public void onFailure(Call<VideoDATA> call, Throwable t) {

            }
        });

    }

    public void setMostVideoHorizontal() {
        // list video xem nhiều nhất

        List<Video> videos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        binding.homeRecyclerviewItemVideo1.setLayoutManager(linearLayoutManager);

        HomeVideoHorizontalAdapter adapter = new HomeVideoHorizontalAdapter(videos, getContext(), HomeFragment.this);
        binding.homeRecyclerviewItemVideo1.setAdapter(adapter);
        binding.homeRecyclerviewItemVideo1.setHasFixedSize(true);

        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<VideoDATA> getListVideoMostView = dataClient.getListVideoMostView(offset, limit);
        getListVideoMostView.enqueue(new Callback<VideoDATA>() {
            @Override
            public void onResponse(Call<VideoDATA> call, Response<VideoDATA> response) {
                for (VideoN videoN : response.body().getListVideo()) {
                    Video video = new Video();

                    int idVideo = videoN.getIdVideo();

                    video.setIdVideo(idVideo);
                    video.setThumbnail(videoN.getLinkHorizontalCoverImage());
                    video.setNameVideo(videoN.getTitle());
                    String timeLine = videoN.getUpdateAt();
                    video.setTimeLine(timeLine);
                    video.setView(videoN.getTotalView());

                    ChannelViewModel channelViewModel = new ViewModelProvider(getViewModelStore(), new ChannelViewModel(idVideo, 2))
                            .get(ChannelViewModel.class);
                    channelViewModel.getChannelLiveData().observe(getViewLifecycleOwner(), new Observer<ChannelN>() {
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
                            for (HashTag hag : response.body().getListHashTag()) {
                                hashTag += hag.getTitle() + " #";
                            }

                            if (hashTag.length() > 2) {
                                hashTag = hashTag.substring(0, hashTag.length() - 2);
                            }
                            if (hashTag.length() == 1) {
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

            @Override
            public void onFailure(Call<VideoDATA> call, Throwable t) {

            }
        });


    }

    public void setVideoVertical(int idCategory) {
        List<Video> videos = new ArrayList<>();
        HomeVideoVerticalAdapter adapter = new HomeVideoVerticalAdapter(videos, getContext(), HomeFragment.this);
        binding.homeRecyclerviewItemVideo3.setNestedScrollingEnabled(false);
        binding.homeRecyclerviewItemVideo3.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeRecyclerviewItemVideo3.setAdapter(adapter);
        binding.homeRecyclerviewItemVideo3.setHasFixedSize(false);


        VideoViewModel videoViewModel = new ViewModelProvider(this, new VideoViewModel(idCategory, offset))
                .get(VideoViewModel.class);
        videoViewModel.getListVideo().observe(getViewLifecycleOwner(), new Observer<List<VideoN>>() {
            @Override
            public void onChanged(List<VideoN> videoNS) {
                if (videoNS != null) {
                    setVideoToRecyclerview(videoNS, adapter, null, videos);
                }

            }
        });


    }

    public void setVideoToRecyclerview(List<VideoN> videoNS, HomeVideoVerticalAdapter adapterVertical,
                                       HomeVideoHorizontalAdapter adapterHorizontal, List<Video> videos) {
        for (VideoN videoN : videoNS) {
            Video video = new Video();

            int idVideo = videoN.getIdVideo();

            video.setIdVideo(idVideo);
            video.setThumbnail(videoN.getLinkHorizontalCoverImage());
            video.setNameVideo(videoN.getTitle());
            String timeLine = videoN.getUpdateAt();
            video.setTimeLine(timeLine);
            video.setView(videoN.getTotalView());


            ChannelViewModel channelViewModel = new ViewModelProvider(getViewModelStore(),
                    new ChannelViewModel(idVideo, Constants.user.getIdUser()))
                    .get(ChannelViewModel.class);
            channelViewModel.getChannelLiveData().observe(getViewLifecycleOwner(), new Observer<ChannelN>() {
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
                    for (HashTag hag : response.body().getListHashTag()) {
                        hashTag += hag.getTitle() + " #";
                    }

                    if (hashTag.length() > 2) {
                        hashTag = hashTag.substring(0, hashTag.length() - 2);
                    }
                    if (hashTag.length() == 1) {
                        hashTag = "";
                    }

                    video.setHashTag(hashTag);
                    Log.d("video", video.getHashTag());
                    videos.add(video);
                    Log.d("video", "video size = " + videos.size());
                    adapterVertical.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<HashTagData> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public void onClick(Category category) {
        Toast.makeText(getContext(), "onClick thành công!!! " + category.getId(), Toast.LENGTH_SHORT).show();
        setVideoVertical(category.getId());
    }


    @Override
    public void clickVideo(int idVideo) {
        Toast.makeText(getContext(), idVideo + "idvideo", Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(getContext(), VideoActivity.class);
         intent.putExtra("idVideo", idVideo);
         startActivity(intent);
    }
}