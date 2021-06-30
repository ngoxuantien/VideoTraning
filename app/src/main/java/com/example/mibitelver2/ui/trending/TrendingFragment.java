package com.example.mibitelver2.ui.trending;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.model.allHashtag.AllHashtag;
import com.example.mibitelver2.model.allHashtag.HashtagData;
import com.example.mibitelver2.retrofit.RetrofitClientMain;
import com.example.mibitelver2.retrofit.retrofitInterface.APIVideoInterface;
import com.example.mibitelver2.adapter.ParentTrendingAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingFragment extends Fragment {

    private List<HashtagData> hashtags;
    private ParentTrendingAdapter parentTrendingAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendingViewModel trendingViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trending, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.fragment_trending_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);
        parentTrendingAdapter = new ParentTrendingAdapter(this.getActivity(), hashtags);
        recyclerView.setAdapter(parentTrendingAdapter);

        hashtags = new ArrayList<>();
        APIVideoInterface api = RetrofitClientMain
                .getClient().create(APIVideoInterface.class);

        Call<AllHashtag> hashtagCall = api.getAllHashtag();

        hashtagCall.enqueue(new Callback<AllHashtag>() {
            @Override
            public void onResponse(@NotNull Call<AllHashtag> call,
                                   @NotNull Response<AllHashtag> response) {
                assert response.body() != null;
                hashtags = response.body().getData();
                parentTrendingAdapter.setData(hashtags);
                Log.e("Success", "msg: 200");
            }

            @Override
            public void onFailure(@NotNull Call<AllHashtag> call, @NotNull Throwable t) {
                Log.e("Error", t.toString());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}