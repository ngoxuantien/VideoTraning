package com.example.mibitelver2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.databinding.LibraryHistoryItemBinding;
import com.example.mibitelver2.model.channel.Channel;
import com.example.mibitelver2.model.channel.ChannelData;
import com.example.mibitelver2.model.video.VideoData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.view.VideoActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryHolder> {

    private final Activity activity;
    private List<VideoData> videos;
    ChannelData channelData;

    public LibraryAdapter(Activity activity, List<VideoData> videos) {
        super();
        this.activity = activity;
        this.videos = videos;
    }

    @NonNull
    @NotNull
    @Override
    public LibraryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LibraryHistoryItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.library_history_item, parent, false);
        return new LibraryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LibraryAdapter.LibraryHolder holder, int position) {
        //Call API get channel data with videoId
        channelData = new ChannelData();
        APIVideoInterface api = RetrofitClientMain.getClient()
                .create(APIVideoInterface.class);
        Call<Channel> channels =
                api.getChannelData(videos.get(position).getIdVideo());
        channels.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(@NotNull Call<Channel> call,
                                   @NotNull Response<Channel> response) {
                assert response.body() != null;
                channelData = response.body().getChannelData();
                Log.e("Success", "Load channel data");
            }

            @Override
            public void onFailure(@NotNull Call<Channel> call, @NotNull Throwable t) {
                Log.e("Failed", t.toString());
            }
        });

        VideoData currentVideo = videos.get(position);
        holder.binding.setVideoData(currentVideo);
        holder.binding.setChannelData(channelData);
        holder.binding.setImgUrl(currentVideo.getLinkVerticalCoverImage());
        holder.bind(currentVideo.getIdVideo());
    }

    @Override
    public int getItemCount() {
        if (videos != null) {
            return videos.size();
        }
        return 0;
    }

    public void setVideos(List<VideoData> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public class LibraryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int videoId;

        LibraryHistoryItemBinding binding;

        public LibraryHolder(LibraryHistoryItemBinding binding) {
            super(binding.getRoot());
            this. binding = binding;
            itemView.setOnClickListener(this);
        }

        public void bind(int videoId) {
            this.videoId = videoId;
        }

        @Override
        public void onClick(View v) {
            Intent i = VideoActivity.newIntent(activity, videoId);
            activity.startActivity(i);
        }
    }
}
