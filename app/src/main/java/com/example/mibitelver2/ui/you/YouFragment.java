package com.example.mibitelver2.ui.you;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.databinding.FragmentYouBinding;

public class YouFragment extends Fragment {

    private YouViewModel youViewModel;
    private FragmentYouBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        youViewModel =
                new ViewModelProvider(this).get(YouViewModel.class);

        binding = FragmentYouBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}