package com.example.mibitelver2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.databinding.ItemResponseCommentBinding;
import com.example.mibitelver2.modeltien.comment.Comment;
import com.example.mibitelver2.modeltien.comment.Datum;
import com.example.mibitelver2.modeltien.putpostmodel.LikeComment;
import com.example.mibitelver2.view.BottomSheetReportFragment;
import com.example.mibitelver2.viewmodel.CommentViewModel;

import java.util.List;

public class CommentResponseAdapter extends RecyclerView.Adapter<CommentResponseAdapter.ViewHolder> {
    private Context context;
    private List<Datum> commentListResponse;
    private CommentViewModel commentViewModel;

    public CommentResponseAdapter(Context context, List<Datum> commentListResponse) {
        this.context = context;
        this.commentListResponse = commentListResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemResponseCommentBinding itemResponseCommentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_response_comment, parent, false);
        return new ViewHolder(itemResponseCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //   Glide.with(context).load(commentListResponse.get(position).getCommentImage()).into(holder.commentImage);
        holder.itemResponseCommentBinding.setComment(commentListResponse.get(position));
        commentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CommentViewModel.class);
        holder.itemResponseCommentBinding.setNumberLike(commentListResponse.get(position).getLike()+"");
        if (commentListResponse.get(position).getIsLike() == 1) {
            holder.itemResponseCommentBinding.setIsLike(1);
        } else {
            holder.itemResponseCommentBinding.setIsLike(0);
        }
        holder.itemResponseCommentBinding.setClickLike(() -> {
            commentViewModel.commentsParent.observe((LifecycleOwner) context, new Observer<Comment>() {
                @Override
                public void onChanged(Comment comment) {
                    if (commentListResponse.get(position).getIsLike() == 1) {
                        commentListResponse.get(position).setIsLike(0);
                        commentViewModel.putLikeComment(new LikeComment(commentListResponse.get(position).getIdComment(), 3, false));
                        holder.itemResponseCommentBinding.setIsLike(0);
                        holder.itemResponseCommentBinding.setNumberLike(commentListResponse.get(position).getLike()-1+"");
                        commentListResponse.get(position).setLike(commentListResponse.get(position).getLike()-1);
                    } else {
                        commentListResponse.get(position).setIsLike(1);
                        commentViewModel.putLikeComment(new LikeComment(commentListResponse.get(position).getIdComment(), 3, true));
                        holder.itemResponseCommentBinding.setIsLike(1);
                        holder.itemResponseCommentBinding.setNumberLike(commentListResponse.get(position).getLike()+1+"");
                        commentListResponse.get(position).setLike(commentListResponse.get(position).getLike()+1);
                    }

                }

            });
        });
        holder.itemResponseCommentBinding.setClickReport(() -> {
            // thêm dữ liệu để biết trường idComment
            commentViewModel.setIdCommentReport(commentListResponse.get(position).getIdComment());
            BottomSheetReportFragment bottomSheetReportFragment = BottomSheetReportFragment.newInstance();
            bottomSheetReportFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), bottomSheetReportFragment.getTag());
        });
    }

    @Override
    public int getItemCount() {
        return commentListResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemResponseCommentBinding itemResponseCommentBinding;

        public ViewHolder(@NonNull ItemResponseCommentBinding binding) {
            super(binding.getRoot());
            this.itemResponseCommentBinding = binding;


        }

        public void bind(Object obj) {
            itemResponseCommentBinding.executePendingBindings();
        }
    }
}
