package com.example.mibitelver2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.allHashtag.HashtagData;
import com.example.mibitelver2.model.oneHashtag.OneHashtag;
import com.example.mibitelver2.model.oneHashtag.OneHtData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.view.TrendingCategoryActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentTrendingAdapter extends RecyclerView.Adapter<ParentTrendingAdapter.TrendingHolder> {

    private final Activity activity;
    private List<HashtagData> data;
    private List<OneHtData> oneHtData;

    public ParentTrendingAdapter(Activity activity, List<HashtagData> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(
            @NonNull @NotNull ParentTrendingAdapter.TrendingHolder holder, int position) {
        ChildTrendingAdapter childTrendingAdapter
                = new ChildTrendingAdapter(activity, oneHtData);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(holder.itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        //call API get all data from oneHashtag
        oneHtData = new ArrayList<>();
        APIVideoInterface api = RetrofitClientMain
                .getClient().create(APIVideoInterface.class);
        Call<OneHashtag> dataList = api.getOneHashtagById(data.get(position).getIdHashTag());
        dataList.enqueue(new Callback<OneHashtag>() {
            @Override
            public void onResponse(@NotNull Call<OneHashtag> call,
                                   @NotNull Response<OneHashtag> response) {

                assert response.body() != null;
                oneHtData = response.body().getData();
                childTrendingAdapter.setHtData(oneHtData);
                Log.e("Success", "msg:200 (1)");
            }

            @Override
            public void onFailure(@NotNull Call<OneHashtag> call, @NotNull Throwable t) {
                Log.e("Error", t.toString());
            }
        });

        holder.rv.setLayoutManager(linearLayoutManager);
        holder.rv.setAdapter(childTrendingAdapter);

        holder.nameTag.setText(data.get(position).getTitle());
        holder.noView.setText(String.format(Locale.getDefault(), "%d",
                data.get(position).getTotalView()) );
        holder.type.setText(R.string.sample);
        holder.nameTag.setOnClickListener(v -> {
            Intent i = new Intent(holder.nameTag.getContext(), TrendingCategoryActivity.class);
            i.putExtra(Constants.trending_category, holder.nameTag.getText().toString());
            holder.nameTag.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        if(data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<HashtagData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ParentTrendingAdapter.TrendingHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trending_main_list, parent, false);
        return new TrendingHolder(v);
    }

    static class TrendingHolder extends RecyclerView.ViewHolder {

        TextView nameTag;
        TextView type;
        TextView noView;
        RecyclerView rv;

        public TrendingHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameTag = itemView.findViewById(R.id.trendingNameTag);
            type = itemView.findViewById(R.id.trendingType);
            noView = itemView.findViewById(R.id.trending_main_list_noViews);
            rv = itemView.findViewById(R.id.trending_main_list_RV);
        }
    }
}
