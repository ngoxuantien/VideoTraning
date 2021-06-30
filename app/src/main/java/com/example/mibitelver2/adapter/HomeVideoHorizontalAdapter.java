package com.example.mibitelver2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mibitelver2.R;
import com.example.mibitelver2.model.tumodel.Video;
import com.example.mibitelver2.util.VideoItemOnClick;

import java.util.List;

public class HomeVideoHorizontalAdapter extends RecyclerView.Adapter<HomeVideoHorizontalAdapter.ViewHolder> {

    private List<Video> videos;
    private Context context;
    private VideoItemOnClick videoItemOnClick;

    public HomeVideoHorizontalAdapter(List<Video> videos, Context context, VideoItemOnClick videoItemOnClick) {
        this.videos = videos;
        this.context = context;
        this.videoItemOnClick = videoItemOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);
        Glide.with(context).load(video.getThumbnail()).placeholder(R.drawable.conver_image).into(holder.imageThumbnail);
        Glide.with(context).load(video.getImgAvata()).placeholder(R.drawable.avata_user).into(holder.imageAvata);
        holder.txtTitle.setText(video.getNameVideo());
        holder.txtName.setText(video.getNameUser());
        holder.txtTime.setText(video.getTimeLine());
        holder.txtView.setText(video.getView() + " views");
        holder.txtHasgtag.setText(video.getHashTag());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoItemOnClick.clickVideo(video.getIdVideo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageThumbnail, imageAvata;
        TextView txtTitle, txtName, txtHasgtag, txtView, txtTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageThumbnail = itemView.findViewById(R.id.home_item_video_thumbnail);
            imageAvata = itemView.findViewById(R.id.home_item_video_image_avata);
            txtTitle = itemView.findViewById(R.id.home_item_video_title_text);
            txtName = itemView.findViewById(R.id.home_item_video_name);
            txtHasgtag = itemView.findViewById(R.id.home_item_video_hashtag);
            txtView = itemView.findViewById(R.id.home_item_video_view);
            txtTime = itemView.findViewById(R.id.home_item_video_time);
        }
    }
}
