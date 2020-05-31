package cat.udl.tidic.amd.dam_tips.adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.QuestColor;
import cat.udl.tidic.amd.dam_tips.models.Question;

public class QuestAdapter extends ListAdapter<Question, QuestAdapter.QuestHolder> {

    private OnItemClickListener ItemListener;

    public QuestAdapter(QuestDiffCallback questDiffCallback) {
        super(questDiffCallback);
    }

    @NonNull
    @Override
    public QuestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_qitem, parent, false);
        return new QuestHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestHolder holder, int position) {
        Question actualquest = getItem(position);
        holder.namequest.setText(actualquest.getQuestion());
        holder.colorsquest.setBackgroundColor(QuestColor.getColourResource(actualquest.getCategory()));

    }

    public class QuestHolder extends RecyclerView.ViewHolder{
        public TextView namequest;
        private TextView colorsquest;

        public QuestHolder(View itemView) {
            super(itemView);
            namequest = itemView.findViewById(R.id.quest_name);
            colorsquest = itemView.findViewById(R.id.quest_colour);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(ItemListener != null && pos != RecyclerView.NO_POSITION) {
                        //ItemListener.onItemClick(getItem(pos));
                    }
                }
            });
        }
    }
    public interface OnClickListener {
        void onItemClick(Question question);
    }

    public void setItemListener(OnItemClickListener listener){
        this.ItemListener = listener;
    }

    public class OnItemClickListener {
    }
}

