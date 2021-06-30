package com.example.mibitelver2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.tumodel.ChannelType;

import java.util.List;

// Hiển thị danh mục kênh
public class PreFollowTypeChannelsAdapter extends RecyclerView.Adapter<PreFollowTypeChannelsAdapter.ViewHolder> {

    private List<ChannelType> listType;
    private Context context;

    public PreFollowTypeChannelsAdapter(List<ChannelType> listType, Context context) {
        this.listType = listType;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pre_follow_channels_item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChannelType type = listType.get(position);
        holder.txtType.setText(type.getType());
        holder.recyclerItem.setLayoutManager(new LinearLayoutManager(context));
        PreFollowChannelsAdapter adapter = new PreFollowChannelsAdapter(type.getListChannel(), context);
        holder.recyclerItem.setAdapter(adapter);
        holder.recyclerItem.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return listType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtType;
        RecyclerView recyclerItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.pre_follow_type_title);
            recyclerItem =itemView.findViewById(R.id.pre_follow_type_recyclerview);
        }
    }
}
