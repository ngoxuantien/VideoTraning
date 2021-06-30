    package com.example.mibitelver2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.oneHashtag.OneHtData;
import com.example.mibitelver2.util.GlideLoader;
import com.example.mibitelver2.view.VideoActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChildTrendingAdapter extends
        RecyclerView.Adapter<ChildTrendingAdapter.TrendingSmallHolder> {

    List<OneHtData> data;
    private final Activity activity;

    public ChildTrendingAdapter(Activity activity, List<OneHtData> data) {
        super();
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public TrendingSmallHolder onCreateViewHolder(
            @NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trending_main_list_item, parent, false);
        return new TrendingSmallHolder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull @NotNull ChildTrendingAdapter.TrendingSmallHolder holder, int position) {

        GlideLoader glideLoader = new GlideLoader();
        glideLoader.loadPicture(data.get(position).getVideoData().getLinkVerticalCoverImage(),
                holder.iv, holder.iv.getContext());
        holder.bind(data.get(position).getVideoData().getIdVideo());

    }

    @Override
    public int getItemCount() {
        if(data!=null)
            return data.size();
        return 0;
    }

    public void setHtData(List<OneHtData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class TrendingSmallHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener{

        int videoId;
        ImageView iv;
        public TrendingSmallHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.tr_main_list_item_iV);
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
