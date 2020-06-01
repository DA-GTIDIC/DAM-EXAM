package cat.udl.tidic.amd.dam_tips.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.QuestColor;
import cat.udl.tidic.amd.dam_tips.models.Question;


public class QuestAdapter extends ListAdapter<Question, QuestAdapter.PreguntesHolder> {

    private OnItemClickListener eventItemListener;

    public QuestAdapter(@NonNull DiffUtil.ItemCallback<Question> QuestDiffCallback) {
        super(QuestDiffCallback);
    }

    @NonNull
    @Override
    public PreguntesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_qitem, parent, false);
        return new PreguntesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PreguntesHolder holder, int position) {
        Question actualquest = getItem(position);
        holder.namequest.setText(actualquest.getQuestion());
        holder.coloursquest.setBackgroundColor(QuestColor.getColourResource(actualquest.getCategory()));
    }

    public class PreguntesHolder extends RecyclerView.ViewHolder {

        public TextView  namequest;
        private TextView coloursquest;

        public PreguntesHolder(View itemView) {

            super(itemView);
            namequest = itemView.findViewById(R.id.quest_name);
            coloursquest = itemView.findViewById(R.id.quest_colour);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (eventItemListener != null && position != RecyclerView.NO_POSITION) {
                        eventItemListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Question question);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}

