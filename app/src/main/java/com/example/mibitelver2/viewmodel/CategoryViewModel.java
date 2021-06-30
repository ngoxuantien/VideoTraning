package com.example.mibitelver2.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mibitelver2.model.tumodel.Category;
import com.example.mibitelver2.repository.CategoryRepository;

import java.util.List;

/* Luồng tạo
* tạo categoryRepository -> categoryViewMode -> InterestActivity
* */
public class CategoryViewModel extends ViewModel implements CategoryRepository.OnResponseComplete {

    CategoryRepository categoryRepository = new CategoryRepository(this);
    MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    public CategoryViewModel() {
        // khởi tạo đọc dữ liệu đọc retrofit
        categoryRepository.getListCategory();

    }

    public MutableLiveData<List<Category>> getCategories() {
        return categories;
    }

    public void setCategories(MutableLiveData<List<Category>> categories) {
        this.categories = categories;
    }

    @Override
    public void categoryListDataAdded(List<Category> listCategory) {

        // khi hoàn thành thì set vào category
        categories.setValue(listCategory);

    }

    @Override
    public void onError(String error) {
        Log.d("error", error);
    }
}
