package com.example.mibitelver2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.view.PreFollowChannelsActivity;
import com.example.mibitelver2.model.tumodel.ChannelN;

import java.util.List;

// Hiển thị list các channel theo danh mục kênh
public class PreFollowChannelsAdapter extends RecyclerView.Adapter<PreFollowChannelsAdapter.ViewHolder>{

    private List<ChannelN> listChannel;
    private Context context;

    public PreFollowChannelsAdapter(List<ChannelN> listChannel, Context context) {
        this.listChannel = listChannel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pre_follow_channels_item_channels, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChannelN channel = listChannel.get(position);
        holder.img.setImageResource(R.drawable.avata_user);
        holder.txtName.setText(channel.getFullName());
        holder.txtDes.setText(channel.getShortBiography());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"click oke",Toast.LENGTH_SHORT).show();
                if (holder.txtChannel.getCurrentTextColor() == Color.parseColor("#415263")){
                    holder.txtChannel.setTextColor(Color.parseColor("#e3eaf2"));
                    holder.txtChannel.setText("+ Seguir");
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#11CCCC"));
                    ((PreFollowChannelsActivity) context).setChannel(channel.getIdUser());

                }else{

                    holder.txtChannel.setTextColor(Color.parseColor("#415263"));
                    holder.txtChannel.setText("Siguiendo");
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#e3eaf2"));
                    ((PreFollowChannelsActivity) context).removeChannel(channel.getIdUser());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listChannel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtName, txtDes, txtChannel;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.pre_follow_item_image);
            txtChannel = itemView.findViewById(R.id.pre_follow_item_regis_channel);
            txtName = itemView.findViewById(R.id.pre_follow_item_name);
            txtDes = itemView.findViewById(R.id.pre_follow_item_des);
            cardView = itemView.findViewById(R.id.pre_follow_item_cardview);
        }
    }
}
