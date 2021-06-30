package com.example.mibitelver2.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.adapter.InterestAdapter;
import com.example.mibitelver2.databinding.ActivityInterestBinding;
import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.model.tumodel.CategoryPost;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;
import com.example.mibitelver2.viewmodel.CategoryViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestActivity extends AppCompatActivity {

    private ActivityInterestBinding binding;
    private List<Integer> idCategoryChosen;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInterestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init();

        //Toast.makeText(this, Constants.user.getName(), Toast.LENGTH_SHORT).show();
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterestActivity.this, PreFollowChannelsActivity.class));
            }
        });

        // POST category của user lên server
        binding.interestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CategoryPost categoryPost = new CategoryPost(5, idCategoryChosen);
                Call<Void> postCategory = dataClient.postCategoryUser(categoryPost);
                postCategory.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        startActivity(new Intent(InterestActivity.this, PreFollowChannelsActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("Response","Error : " +  t.getMessage());
                    }
                });
            }
        });


        // Đọc list category gợi ý từ server
        CategoryViewModel categoryViewModel = new ViewModelProvider(this)
                .get(CategoryViewModel.class);

        categoryViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                Toast.makeText(InterestActivity.this, categories.size() + " item", Toast.LENGTH_SHORT).show();
                addDataToRecyclerView(categories);
            }
        });

//        List<Category> categories = new ArrayList<>();
//        categories.add(new Category(1,"","","Arte y diseño"));
//        categories.add(new Category(2,"","","Animes"));
//        categories.add(new Category(3,"","","Belleza"));
//        categories.add(new Category(4,"","","Hobbies"));
//        categories.add(new Category(5,"","","Cocina"));
//        categories.add(new Category(6,"","","Deportes"));
//        categories.add(new Category(7,"","","Educación"));
//        categories.add(new Category(8,"","","Mascotas"));
//        categories.add(new Category(9,"","","Moda"));
//        categories.add(new Category(10,"","","Música"));
//        categories.add(new Category(11,"","","Tecnología"));
//        categories.add(new Category(12,"","","Naturaleza"));
//        categories.add(new Category(13,"","","Estilo de vida"));
//        addDataToRecyclerView(categories);

    }

    public void init(){
        sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        // mode private : chỉ lưu trữ và sử dụng trong phạm vi ứng dụng
        editor = sharedPreferences.edit();
        binding.cardSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt(String.valueOf(Constants.user.getIdUser()),1);
                editor.commit();
                startActivity(new Intent(InterestActivity.this, MainActivity.class));
            }
        });

    }
    // add dữ liệu vào recyclerview
    public void addDataToRecyclerView(List<Category> categories){
        InterestAdapter adapter = new InterestAdapter(categories, InterestActivity.this);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW_REVERSE);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        layoutManager.setFlexWrap(FlexWrap.WRAP);

        binding.interestRecyclerview.setLayoutManager(layoutManager);

        binding.interestRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setChosen(int id){
        if (idCategoryChosen == null){
            idCategoryChosen = new ArrayList<>();
        }
        idCategoryChosen.add(id);
    }

    public void removeChosen(int id){
        if (idCategoryChosen == null){
            idCategoryChosen = new ArrayList<>();
        }else if(idCategoryChosen.size() > 0) {
            idCategoryChosen.remove((Integer) id);
        }
    }
}