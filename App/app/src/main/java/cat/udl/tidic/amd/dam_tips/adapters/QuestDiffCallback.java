package cat.udl.tidic.amd.dam_tips.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import cat.udl.tidic.amd.dam_tips.models.Question;

public class QuestDiffCallback extends DiffUtil.ItemCallback<Question> {

    @Override
    public boolean areItemsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return false;
    }
}
