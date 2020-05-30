package cat.udl.tidic.amd.dam_tips.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.models.PreguntaEnum;


public class PreguntesAdapter extends ListAdapter<Pregunta, PreguntesAdapter.PreguntesHolder> {

    private OnItemClickListener eventItemListener;

    public PreguntesAdapter(@NonNull DiffUtil.ItemCallback<Pregunta> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PreguntesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.preguntes_item_list, parent, false);
        return new PreguntesHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PreguntesHolder holder, int position) {
        Pregunta currentPreguntes = getItem(position);
        holder.pregunta_name.setText(currentPreguntes.getQuestion());
        holder.colors.setBackgroundColor(PreguntaEnum.getColourResource(currentPreguntes.getCategory()));

    }

    public class PreguntesHolder extends RecyclerView.ViewHolder {
        public TextView pregunta_name;
        private TextView colors;

        public PreguntesHolder(View itemView) {
            super(itemView);

            pregunta_name = itemView.findViewById(R.id.pregunta_name);
            colors = itemView.findViewById(R.id.question_colour);


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
        void onItemClick(Pregunta preguntaa);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
