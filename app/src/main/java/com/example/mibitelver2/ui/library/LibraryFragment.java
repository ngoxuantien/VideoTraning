package com.example.mibitelver2.ui.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.video.RecentlyViewedVideo;
import com.example.mibitelver2.model.video.VideoData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.adapter.LibraryAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryFragment extends Fragment {

    LibraryAdapter adapter;
    List<VideoData> videos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_library, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.fragment_library_RV);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new LibraryAdapter(this.getActivity(), videos);
        recyclerView.setAdapter(adapter);

        APIVideoInterface api = RetrofitClientMain.getClient()
                .create(APIVideoInterface.class);
        Call<RecentlyViewedVideo> videoList = api.getRecentVideos(3, 0, 5);
        videoList.enqueue(new Callback<RecentlyViewedVideo>() {
            @Override
            public void onResponse(@NotNull Call<RecentlyViewedVideo> call,
                                   @NotNull Response<RecentlyViewedVideo> response) {
                assert response.body() != null;
                videos = response.body().getData();
                adapter.setVideos(videos);
                Log.e("Success", "Get recent videos");
            }

            @Override
            public void onFailure(@NotNull Call<RecentlyViewedVideo> call, @NotNull Throwable t) {
                Log.e("Failed", t.toString());
            }
        });

        CardView watched = root.findViewById(R.id.library_watched_cv);
        watched.setOnClickListener(v -> {
            Intent i = new Intent(root.getContext(), HistoryActivity.class);
            startActivity(i);
        });

        CardView liked = root.findViewById(R.id.library_liked_cv);
        liked.setOnClickListener(v -> {
            Intent i = new Intent(root.getContext(), FavoriteVideoActivity.class);
            startActivity(i);
        });

        CardView saved = root.findViewById(R.id.library_saved_cv);
        saved.setOnClickListener(v -> {
            Intent i = new Intent(root.getContext(), WatchLaterActivity.class);
            startActivity(i);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}