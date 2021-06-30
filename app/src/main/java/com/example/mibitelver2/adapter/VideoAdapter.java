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

// adapter co all video
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videos;
    private Context context;
    private VideoItemOnClick onClick;

    public VideoAdapter(List<Video> videos, Context context, VideoItemOnClick onClick) {
        this.videos = videos;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(context).inflate(R.layout.all_video_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);
        Glide.with(context).load(video.getThumbnail()).placeholder(R.drawable.conver_image).into(holder.imageThumbnail);
        Glide.with(context).load(video.getImgAvata()).placeholder(R.drawable.avata_user).into(holder.iamgeAvata);
        holder.txtTitle.setText(video.getNameVideo());
        holder.txtName.setText(video.getNameUser());
        holder.txtTime.setText(video.getTimeLine());
        holder.txtView.setText(video.getView() + " views");
        holder.txtHasgtag.setText(video.getHashTag());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.clickVideo(video.getIdVideo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageThumbnail, iamgeAvata;
        TextView txtTitle, txtName, txtHasgtag, txtView, txtTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageThumbnail = itemView.findViewById(R.id.all_video_thumbnail_item);
            iamgeAvata = itemView.findViewById(R.id.all_video_item_image_avata);
            txtTitle = itemView.findViewById(R.id.title_text);
            txtName = itemView.findViewById(R.id.all_video_item_user_name);
            txtHasgtag = itemView.findViewById(R.id.all_video_item_hashtag);
            txtView = itemView.findViewById(R.id.all_video_item_video_view);
            txtTime = itemView.findViewById(R.id.all_video_item_video_time);
        }
    }
}
