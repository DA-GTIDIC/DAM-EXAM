package cat.udl.tidic.amd.dam_tips.adapters;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.amd.dam_tips.models.Question;

public class QuestionDiffCallback extends DiffUtil.ItemCallback<Question> {

    @Override
    public boolean areItemsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return oldItem.getQuestion().equals(newItem.getQuestion());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return oldItem.equals(newItem);
    }
}
