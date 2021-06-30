package com.example.mibitelver2.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.repository.CategoryRepository;

import java.util.List;

// view model cho category người dùng đã chọn từ gd 01
public class CategoryUserViewModel extends ViewModel implements CategoryRepository.OnResponseComplete, ViewModelProvider.Factory {
    private CategoryRepository categoryRepository = new CategoryRepository(this);
    private MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    private int idUser;
    public CategoryUserViewModel(int idUser) {
        this.idUser = idUser;
        categoryRepository.getListCategoryOfUser(this.idUser);
    }

    public MutableLiveData<List<Category>> getCategories() {
        return categories;
    }

    @Override
    public void categoryListDataAdded(List<Category> listCategory) {
        categories.setValue(listCategory);
    }

    @Override
    public void onError(String error) {
        Log.d("error" , error);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CategoryUserViewModel(idUser);
    }
}
