package com.example.mibitelver2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.databinding.ItemHashtagBinding;
import com.example.mibitelver2.modeltien.hashtag.Datum;

import java.util.List;

public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.ViewHolder> {
    private Context context;
    private List<Datum> hashtaglist;

    public HashTagAdapter(Context context, List<Datum> hashtag) {
        this.context = context;
        this.hashtaglist = hashtag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHashtagBinding itemHashtagBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_hashtag, parent, false);
      return new ViewHolder(itemHashtagBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Datum hashtag = hashtaglist.get(position);

        holder.itemHashtagBinding.setHashtag(hashtag);
        holder.bind(hashtag);
    }

    @Override
    public int getItemCount() {
        return hashtaglist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ItemHashtagBinding itemHashtagBinding;
        public ViewHolder(@NonNull ItemHashtagBinding binding) {
            super(binding.getRoot());
            this.itemHashtagBinding=binding;
        }
        public void bind(Object obj) {
            itemHashtagBinding.executePendingBindings();
        }
    }
}
