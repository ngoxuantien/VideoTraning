package com.example.mibitelver2.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.video.VideoData;
import com.example.mibitelver2.model.video.ListVideo;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.adapter.TrendingCategoryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingCategoryActivity extends BaseActivity {

    String categoryTxt;
    List<VideoData> allVideos;
    TrendingCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_category);

        TextView back = findViewById(R.id.activity_category_backArrow);
        back.setOnClickListener(v -> onBackPressed());

        RecyclerView recyclerView = findViewById(R.id.trending_category_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrendingCategoryAdapter(this, allVideos);
        recyclerView.setAdapter(adapter);

        TextView category = findViewById(R.id.trending_category_categoryTv);

        allVideos = new ArrayList<>();
        APIVideoInterface api = RetrofitClientMain
                .getClient().create(APIVideoInterface.class);

        Call<ListVideo> videos = api.getAllVideosByCategory();
        videos.enqueue(new Callback<ListVideo>() {
            @Override
            public void onResponse(@NotNull Call<ListVideo> call,
                                   @NotNull Response<ListVideo> response) {
                assert response.body() != null;
                allVideos = response.body().getData();
                adapter.setVideos(allVideos);
                Log.e("Success", "");
            }

            @Override
            public void onFailure(@NotNull Call<ListVideo> call, @NotNull Throwable t) {
                Log.e("Error: ", t.toString());
            }
        });

        if(getIntent().hasExtra(Constants.trending_category)) {
            categoryTxt = getIntent().getStringExtra(Constants.trending_category);
            category.setText(categoryTxt);
        }
    }
}