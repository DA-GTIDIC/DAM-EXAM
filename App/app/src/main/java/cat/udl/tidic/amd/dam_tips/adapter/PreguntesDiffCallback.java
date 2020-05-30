package cat.udl.tidic.amd.dam_tips.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.amd.dam_tips.models.Pregunta;


public class PreguntesDiffCallback extends DiffUtil.ItemCallback<Pregunta> {


    @Override
    public boolean areItemsTheSame(@NonNull Pregunta oldItem, @NonNull Pregunta newItem) {
        return oldItem.getQuestion() == newItem.getQuestion();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Pregunta oldItem, @NonNull Pregunta newItem) {
        return oldItem.equals(newItem);
    }


}
