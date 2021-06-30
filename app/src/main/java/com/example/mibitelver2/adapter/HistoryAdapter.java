package com.example.mibitelver2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.channel.Channel;
import com.example.mibitelver2.model.channel.ChannelData;
import com.example.mibitelver2.model.video.VideoData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.util.GlideLoader;
import com.example.mibitelver2.view.VideoActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private final Activity activity;
    private List<VideoData> videos;
    ChannelData channelData;

    public HistoryAdapter(Activity activity, List<VideoData> videos) {
        super();
        this.activity = activity;
        this.videos = videos;
    }

    @NonNull
    @NotNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_item, parent, false);
        return new HistoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryAdapter.HistoryHolder holder, int position) {
        //Call API get all channels with videoId
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
                if(channelData != null)
                    holder.owner.setText(channelData.getFullName());
                Log.e("Success", "Load channel data");
            }

            @Override
            public void onFailure(@NotNull Call<Channel> call, @NotNull Throwable t) {
                Log.e("Failed", t.toString());
            }
        });

        VideoData currentVideo = videos.get(position);
        GlideLoader glideLoader = new GlideLoader();
        glideLoader.loadPicture(videos.get(position).getLinkVerticalCoverImage(),
                holder.iV, activity);

        holder.title.setText(currentVideo.getTitle());
        holder.nOViews.setText(String.format(
                Locale.getDefault(), "%d", currentVideo.getTotalView()));
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

    public class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int videoId;
        ImageView iV;
        TextView title;
        TextView nOViews;
        TextView owner;
        TextView anchor;


        public HistoryHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iV = itemView.findViewById(R.id.act_his_item_iv);
            title = itemView.findViewById(R.id.act_his_item_title);
            nOViews = itemView.findViewById(R.id.act_his_item_view);
            owner = itemView.findViewById(R.id.act_his_item_channel);
            anchor = itemView.findViewById(R.id.anchor);
            iV.setOnClickListener(this);

            //Overflow on each item
            anchor.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(activity, anchor);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.item_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {

                        case R.id.remove:

                            break;

                        case R.id.download:

                            break;

                        case R.id.share:

                            break;

                    }
                    return false;
                });
            });
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
