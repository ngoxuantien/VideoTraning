package com.example.mibitelver2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mibitelver2.R;
import com.example.mibitelver2.databinding.ItemCommentBinding;
import com.example.mibitelver2.modeltien.comment.Comment;
import com.example.mibitelver2.modeltien.comment.Datum;
import com.example.mibitelver2.modeltien.putpostmodel.LikeComment;
import com.example.mibitelver2.view.BottomSheetReportFragment;
import com.example.mibitelver2.viewmodel.CommentViewModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<Datum> commentList;
    private CommentViewModel commentViewModel;
    private List<Datum> commentResponse;
    private Activity activity;


    public CommentAdapter(Context context, List<Datum> commentList, Activity activity) {
        this.context = context;
        this.commentList = commentList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding itemCommentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_comment, parent, false);

        return new ViewHolder(itemCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Datum comment = commentList.get(position);
        final boolean[] h = {true};
        holder.itemCommentBinding.setComment(comment);
        holder.bind(comment);
        // set list comment con lu??n ????ng
        holder.itemCommentBinding.setRoll(true);
        // set cu???n ra cu???n v??o
        holder.itemCommentBinding.setClickCollape(() -> {
            if (h[0]) {
                holder.itemCommentBinding.setRoll(true);
                h[0] = false;
            } else {
                holder.itemCommentBinding.setRoll(false);
                h[0] = true;
            }
        });
        holder.itemCommentBinding.setNumberLike(commentList.get(position).getLike()+"");
        commentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CommentViewModel.class);
        // ph???i fix id user
        commentViewModel.getComment(commentList.get(position).getIdComment() + "", "3");

        commentViewModel.commentsParent.observe((LifecycleOwner) context, new Observer<Comment>() {
            @Override
            public void onChanged(Comment comment) {
                if (comment.getData().get(position).getIsLike() == 1) {
                    holder.itemCommentBinding.setIsLike(1);
                } else {
                    holder.itemCommentBinding.setIsLike(0);
                }

            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        holder.itemCommentBinding.setClickResponse(() -> {
            // set id coment cha v??o livedata
            commentViewModel.setIcCommentResponse(commentList.get(position).getIdComment() + "");
            //    holder.showKeyboard();
            Toast toast = Toast.makeText(context, "th??? nhi???m test", Toast.LENGTH_SHORT);
            toast.show();
            //
        });

        holder.itemCommentBinding.setClickReport(() -> {
            // th??m d??? li???u ????? bi???t tr?????ng idComment
            commentViewModel.setIdCommentReport(commentList.get(position).getIdComment());
            BottomSheetReportFragment bottomSheetReportFragment = BottomSheetReportFragment.newInstance();
            bottomSheetReportFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), bottomSheetReportFragment.getTag());
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        holder.itemCommentBinding.setClickLike(() -> {
            commentViewModel.commentsParent.observe((LifecycleOwner) context, new Observer<Comment>() {
                @Override
                public void onChanged(Comment comment) {
                    if(commentList.get(position).getIsLike()==1){
                        commentList.get(position).setIsLike(0);
                        commentViewModel.putLikeComment(new LikeComment(commentList.get(position).getIdComment(),3,false));
                        holder.itemCommentBinding.setIsLike(0);
                        holder.itemCommentBinding.setNumberLike(commentList.get(position).getLike()-1+"");
                        commentList.get(position).setLike(commentList.get(position).getLike()-1);
                    }else {
                        commentList.get(position).setIsLike(1);
                        commentViewModel.putLikeComment(new LikeComment(commentList.get(position).getIdComment(),3,true));
                        holder.itemCommentBinding.setIsLike(1);
                        holder.itemCommentBinding.setNumberLike(commentList.get(position).getLike()+1+"");
                        commentList.get(position).setLike(commentList.get(position).getLike()+1);
                    }

                }

            });
        });

        commentViewModel.comment.observe((LifecycleOwner) context, new Observer<Comment>() {
            @Override
            public void onChanged(Comment comment) {
                commentResponse = comment.getData();

                if (commentResponse.size() != 0) {
                    holder.itemCommentBinding.setView(true);
                    // x??t recyclerview
                    holder.itemCommentBinding.setAdapter(new CommentResponseAdapter(context, commentResponse));
                    // set test s??? b??nh lu???n c???a list
                    holder.itemCommentBinding.setNumberOfComment("Ver respuestas (" + commentResponse.size() + ")");
                } else {
                    holder.itemCommentBinding.setView(false);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCommentBinding itemCommentBinding;
        //   RecyclerView recyclerViewResponse;

        public ViewHolder(@NonNull ItemCommentBinding binding) {
            super(binding.getRoot());
            this.itemCommentBinding = binding;

        }


        //// c???n t??m hi???u th??m///
        public void bind(Object obj) {
            itemCommentBinding.executePendingBindings();
        }

        public void showKeyboard() {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 3);
        }

    }


}
