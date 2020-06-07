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
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.models.QuestionColours;


public class QuestionAdapter extends ListAdapter<Question, QuestionAdapter.PreguntesHolder> {

    private OnItemClickListener eventItemListener;

    public QuestionAdapter(@NonNull DiffUtil.ItemCallback<Question> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PreguntesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_items, parent, false);
        return new PreguntesHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PreguntesHolder holder, int position) {
        Question currentPreguntes = getItem(position);
        holder.namequest.setText(currentPreguntes.getQuestion());
        holder.colourquest.setBackgroundColor(QuestionColours.getColours(currentPreguntes.getCategory()));

    }

    public class PreguntesHolder extends RecyclerView.ViewHolder {
        public TextView namequest;
        private TextView colourquest;

        public PreguntesHolder(View itemView) {
            super(itemView);

            namequest = itemView.findViewById(R.id.namequest);
            colourquest = itemView.findViewById(R.id.colorquest);


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

