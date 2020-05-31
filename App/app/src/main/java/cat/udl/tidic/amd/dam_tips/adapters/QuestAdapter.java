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

import org.bouncycastle.asn1.x509.Holder;
import org.w3c.dom.Text;

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


/*

package cat.udl.tidic.amd.dam_tips.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Question;

class QuestAdaper extends RecyclerView.Adapter<QuestAdaper.MyViewHolder> {
    private String[] mDataset;

    public void submitList(List<Question> questions) {
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuestAdaper(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuestAdaper.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_quest, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}







 */