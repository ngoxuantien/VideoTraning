package com.example.mibitelver2.view;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mibitelver2.R;
import com.example.mibitelver2.adapter.CommentAdapter;
import com.example.mibitelver2.databinding.LayoutBottomSheetFragmentBinding;
import com.example.mibitelver2.modeltien.comment.Comment;
import com.example.mibitelver2.viewmodel.CommentViewModel;
import com.example.mibitelver2.viewmodel.VideoUserViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottonSheetDialogFragment extends BottomSheetDialogFragment {
    private CommentViewModel commentViewModel;
    private VideoUserViewModel videoUserViewModel;
    LayoutBottomSheetFragmentBinding layoutBottomSheetFragmentBinding;
    private CommentAdapter commentAdapter;
    private View view;
    private Handler handler;

    public static MyBottonSheetDialogFragment newInstance() {
        MyBottonSheetDialogFragment myBottonSheetDialogFragment = new MyBottonSheetDialogFragment();

        return myBottonSheetDialogFragment;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet_fragment, null);
        bottomSheetDialog.setContentView(view);

        layoutBottomSheetFragmentBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_bottom_sheet_fragment, null, false);
        commentViewModel = new ViewModelProvider(requireActivity()).get(CommentViewModel.class);
        videoUserViewModel = new ViewModelProvider(requireActivity()).get(VideoUserViewModel.class);
        // c??i c??

        commentViewModel.getCommentsParent("2", "3");
        commentViewModel.setIcCommentResponse("0");
        layoutBottomSheetFragmentBinding.setComment(MyBottonSheetDialogFragment.this);

        bottomSheetDialog.setContentView(layoutBottomSheetFragmentBinding.getRoot());

        change();

        return bottomSheetDialog;

    }


    private void change() {
        commentViewModel.commentsParent.observe(this, new Observer<Comment>() {
            @Override
            public void onChanged(Comment comment) {
                commentAdapter = new CommentAdapter(getContext(), comment.getData(), getActivity());
                layoutBottomSheetFragmentBinding.setAdapter(commentAdapter);
            }
        });
    }

    public void clickreport() {
        BottomSheetReportFragment bottomSheetReportFragment = BottomSheetReportFragment.newInstance();
        bottomSheetReportFragment.show((getActivity()).getSupportFragmentManager(), bottomSheetReportFragment.getTag());
    }

    public void onclick() {

        commentViewModel.addComment(videoUserViewModel.getIdvideo(), 3, commentViewModel.getinput());
        commentViewModel.getCommentsParent("2", "3");
        change();


    }

}

