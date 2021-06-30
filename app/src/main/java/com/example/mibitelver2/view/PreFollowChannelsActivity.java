package com.example.mibitelver2.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.adapter.PreFollowTypeChannelsAdapter;
import com.example.mibitelver2.databinding.ActivityPreFollowChannelsBinding;
import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.model.tumodel.Channel;
import com.example.mibitelver2.model.tumodel.ChannelListDATA;
import com.example.mibitelver2.model.tumodel.ChannelPost;
import com.example.mibitelver2.model.tumodel.ChannelType;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.viewmodel.CategoryUserViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreFollowChannelsActivity extends AppCompatActivity {

    private ActivityPreFollowChannelsBinding binding;
    private List<Integer> listChannelRegister = new ArrayList<>();
    private List<Category> listCategoryUser = new ArrayList<>();
    private List<ChannelType> listChannelType = new ArrayList<>();
    private PreFollowTypeChannelsAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreFollowChannelsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//        // mode private : chỉ lưu trữ và sử dụng trong phạm vi ứng dụng
//        editor = sharedPreferences.edit();
//
//        binding.preFollowChannelSkipButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editor.putInt(String.valueOf(Constants.user.getIdUser()),1);
//                editor.commit();
//                startActivity(new Intent(PreFollowChannelsActivity.this, MainActivity.class));
//            }
//        });


        adapter = new PreFollowTypeChannelsAdapter(listChannelType, this);
        RecyclerView recyclerView = findViewById(R.id.pre_follow_channel_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        init();

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PreFollowChannelsActivity.this, MainActivity.class));
            }
        });

       // FollowChannels();

        binding.preFollowChannelBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PreFollowChannelsActivity.this, com.example.mibitelver2.view.InterestActivity.class));
            }
        });

        binding.preFollowChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "";
                for (int i : listChannelRegister){
                    s += i + " ";
                }
                Toast.makeText(PreFollowChannelsActivity.this, s, Toast.LENGTH_SHORT).show();

                ChannelPost channelPost = new ChannelPost(Constants.user.getIdUser(), listChannelRegister);
                DataClient dataClient = APIUtils.getData(Constants.base_url);
                Call<Void> postChannel = dataClient.postChannelUserFollow(channelPost);
                postChannel.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(PreFollowChannelsActivity.this, "Post Channels thành công!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PreFollowChannelsActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

    }

    public void FollowChannels(){
        List<Channel> listMusic = new ArrayList<>();
        listMusic.add(new Channel(1,R.drawable.avata_user, "Tú Nguyễn", "No Description", 0));
        listMusic.add(new Channel(2,R.drawable.avata_user, "Tú Nguyễn", "No Description", 0));
        listMusic.add(new Channel(3,R.drawable.avata_user, "Tú Nguyễn", "No Description", 0));
        listMusic.add(new Channel(4,R.drawable.avata_user, "Tú Nguyễn", "No Description", 0));
        listMusic.add(new Channel(5,R.drawable.avata_user, "Tú Nguyễn", "No Description", 0));

        List<Channel> listSport = new ArrayList<>();
        listSport.add(new Channel(6,R.drawable.avata_user, "Tú Nguyễn", "No Sport", 0));
        listSport.add(new Channel(7,R.drawable.avata_user, "Tú Nguyễn", "No Sport", 0));
        listSport.add(new Channel(8,R.drawable.avata_user, "Tú Nguyễn", "No Sport", 0));
        listSport.add(new Channel(9,R.drawable.avata_user, "Tú Nguyễn", "No Sport", 0));
        listSport.add(new Channel(10,R.drawable.avata_user, "Tú Nguyễn", "No Sport", 0));

        List<ChannelType> model = new ArrayList<>();
//        model.add(new ChannelType("Music",listMusic));
//        model.add(new ChannelType("Sport", listSport));

        PreFollowTypeChannelsAdapter adapter = new PreFollowTypeChannelsAdapter(model, this);
        RecyclerView recyclerView = findViewById(R.id.pre_follow_channel_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    public void init(){
        CategoryUserViewModel categoryUserViewModel = new ViewModelProvider(this, new CategoryUserViewModel(1))
                .get(CategoryUserViewModel.class);
        categoryUserViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                for (Category category : categories){

                    DataClient dataClient = APIUtils.getData(Constants.base_url);
                    Call<ChannelListDATA> getListChannels = dataClient.getChannelList(category.getId());
                    getListChannels.enqueue(new Callback<ChannelListDATA>() {
                        @Override
                        public void onResponse(Call<ChannelListDATA> call, Response<ChannelListDATA> response) {
                            listChannelType.add(new ChannelType(category.getName(), response.body().getListChannelN()));
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ChannelListDATA> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public void setChannel(int idChannel){
        listChannelRegister.add(idChannel);
    }

    public void removeChannel(int idChannel){
        listChannelRegister.remove((Integer) idChannel);
    }
}