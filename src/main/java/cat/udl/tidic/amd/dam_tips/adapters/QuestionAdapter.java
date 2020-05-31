package cat.udl.tidic.amd.dam_tips.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Question;

public class QuestionAdapter extends ListAdapter <Question, QuestionAdapter.QuestionHolder> {

    public QuestionAdapter(@NonNull DiffUtil.ItemCallback<Question> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_question,
                null, false);
        return new QuestionHolder(itemView);
    }

    public void onBindViewHolder(@NonNull QuestionHolder holder, int position){
        final Question question = getItem(position);
        holder.textViewQuestion.setText(question.getQuestion());
        switch (question.getCategory().getId()){
            case "DB": holder.textViewQuestion.setTextColor(Color.rgb(200,0,0)); break;
            case "OS":holder.textViewQuestion.setTextColor(Color.rgb(0,200,0));break;
            case "NET": holder.textViewQuestion.setTextColor(Color.rgb(0,0,200)); break;
            case "PATTERNS": holder.textViewQuestion.setTextColor(Color.rgb(200,0,200)); break;
        }


    }

    class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView textViewQuestion;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);

            textViewQuestion = itemView.findViewById(R.id.questionInfo);

        }
    }
}

