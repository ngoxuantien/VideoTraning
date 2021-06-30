package com.example.mibitelver2.ui.library;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.video.RecentlyViewedVideo;
import com.example.mibitelver2.model.video.VideoData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.util.NetworkStateReceiver;
import com.example.mibitelver2.view.BaseActivity;
import com.example.mibitelver2.adapter.HistoryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;

public class HistoryActivity extends BaseActivity
        implements NetworkStateReceiver.NetworkStateReceiverListener {

    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    private HistoryAdapter adapter;
    private List<VideoData> videoAdapter;
    private List<VideoData> videos;
    private int offset = 0;
    private static final int limit = 5;
    private NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startNetworkBroadcastReceiver(this);
        setContentView(R.layout.activity_history);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        recyclerView = findViewById(R.id.act_his_RV);
        progressBar = findViewById(R.id.progress_bar);
        videoAdapter = new ArrayList<>();
        adapter = new HistoryAdapter(this, videoAdapter);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(int offset) {
        APIVideoInterface api = RetrofitClientMain.getClient()
                .create(APIVideoInterface.class);
        Call<RecentlyViewedVideo> listVideos = api.getRecommendVideo(offset, limit);
        listVideos.enqueue(new Callback<RecentlyViewedVideo>() {
            @Override
            public void onResponse(@NotNull Call<RecentlyViewedVideo> call,
                                   @NotNull Response<RecentlyViewedVideo> response) {
                progressBar.setVisibility(View.GONE);
                assert response.body() != null;
                videos = response.body().getData();
                videoAdapter.addAll(videos);
                adapter.setVideos(videoAdapter);
                Log.e("Success", "Get all viewed videos");
            }

            @Override
            public void onFailure(@NotNull Call<RecentlyViewedVideo> call, @NotNull Throwable t) {
                Log.e("Failed", t.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        /***/
        unregisterNetworkBroadcastReceiver(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        /***/
        registerNetworkBroadcastReceiver(this);
        super.onResume();
    }

    @Override
    public void networkAvailable() {
        getData(offset);
        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY == v.getChildAt(0).getMeasuredHeight()
                            - v.getMeasuredHeight()) {
                        offset++;
                        progressBar.setVisibility(View.VISIBLE);
                        getData(offset);
                    }
                });
    }


    @Override
    public void networkUnavailable() {
        Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
    }

    public void startNetworkBroadcastReceiver(Context currentContext) {
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(
                (NetworkStateReceiver.NetworkStateReceiverListener) currentContext);
        registerNetworkBroadcastReceiver(currentContext);
    }

    /**
     * Register the NetworkStateReceiver with your activity
     * @param currentContext
     */
    public void registerNetworkBroadcastReceiver(Context currentContext) {
        currentContext.registerReceiver(networkStateReceiver,
                new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /**
     Unregister the NetworkStateReceiver with your activity
     * @param currentContext
     */
    public void unregisterNetworkBroadcastReceiver(Context currentContext) {
        currentContext.unregisterReceiver(networkStateReceiver);
    }
}