package com.example.mibitelver2.repository;

import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.model.tumodel.CategoryDATA;
import com.example.mibitelver2.retrofit.APIUtils;
import com.example.mibitelver2.retrofit.DataClient;
import com.example.mibitelver2.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {

    private OnResponseComplete onResponseComplete;

    public CategoryRepository(OnResponseComplete onResponseComplete) {
        this.onResponseComplete = onResponseComplete;
    }

    // đọc retrofit, nếu hoàn thành thì đưa vào onResponseComplete, get all category - giao diện 01
    public void getListCategory(){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<CategoryDATA> callData = dataClient.getListCategory();
        callData.enqueue(new Callback<CategoryDATA>() {
            @Override
            public void onResponse(Call<CategoryDATA> call, Response<CategoryDATA> response) {
                List<Category> categories = response.body().getListCategory();
                onResponseComplete.categoryListDataAdded(categories);
            }

            @Override
            public void onFailure(Call<CategoryDATA> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });
    }

    // get list category user chọn - gd 02
    public void getListCategoryOfUser(int idUser){
        DataClient dataClient = APIUtils.getData(Constants.base_url);
        Call<CategoryDATA> categoryDATACall = dataClient.getListCategoryByUserId(idUser);
        categoryDATACall.enqueue(new Callback<CategoryDATA>() {
            @Override
            public void onResponse(Call<CategoryDATA> call, Response<CategoryDATA> response) {
                List<Category> categories = response.body().getListCategory();
                onResponseComplete.categoryListDataAdded(categories);
            }

            @Override
            public void onFailure(Call<CategoryDATA> call, Throwable t) {
                onResponseComplete.onError(t.getMessage());
            }
        });
    }


    public interface OnResponseComplete{
        void categoryListDataAdded(List<Category> listCategory);
        void onError(String error);
    }
}
