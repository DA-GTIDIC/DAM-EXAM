package cat.udl.tidic.amd.dam_tips.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Answer;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.TrivialViewModel;
import cat.udl.tidic.amd.dam_tips.views.CustomActivty;

public class DialogGame extends DialogFragment {

    private String TAG = getClass().getSimpleName();

    public View rootView;
    public CustomActivty activity;
    public TrivialViewModel trivialViewModel;

    private TextView category;
    private TextView question_textView;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;


    public static DialogGame newInstance(CustomActivty activity, TrivialViewModel trivialViewModel){
        DialogGame game = new DialogGame();
        game.trivialViewModel = trivialViewModel;
        game.activity = activity;
        return game;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initView();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog game = dialog.setView(rootView).setCancelable(true).create();


        trivialViewModel.getQuestion();

        trivialViewModel.getQuestionResponse().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                List<String> answers = new ArrayList<>();
                Log.d(TAG, question.toString());
                category.setText(question.getCategory().getName());
                question_textView.setText(question.getQuestion());
                List<Answer> aux_answers = question.getAnswers();

                Answer ans1 = aux_answers.get(0);
                Answer ans2 = aux_answers.get(1);
                Answer ans3 = aux_answers.get(2);
                Answer ans4 = aux_answers.get(3);

                answer1.setText(ans1.getAnswer());
                answer2.setText(ans2.getAnswer());
                answer3.setText(ans3.getAnswer());
                answer4.setText(ans4.getAnswer());

                    answer1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ans1.isIs_correct()){
                                alreadyAnswered();
                                answer1.setBackgroundResource(R.color.green);
                                trivialViewModel.correctAnswer(question);

                            }else{
                                alreadyAnswered();
                                answer1.setBackgroundResource(R.color.red);
                                trivialViewModel.wrongAnswer(question);
                            }

                        }
                    });
                    answer2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ans2.isIs_correct()){
                                alreadyAnswered();
                                answer2.setBackgroundResource(R.color.green);
                                trivialViewModel.correctAnswer(question);

                            }else{
                                alreadyAnswered();
                                answer2.setBackgroundResource(R.color.red);
                                trivialViewModel.wrongAnswer(question);
                            }
                        }
                    });
                    answer3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ans3.isIs_correct()){
                                alreadyAnswered();
                                answer3.setBackgroundResource(R.color.green);
                                trivialViewModel.correctAnswer(question);
                            }else{
                                alreadyAnswered();
                                answer3.setBackgroundResource(R.color.red);
                                trivialViewModel.wrongAnswer(question);
                            }
                        }
                    });
                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ans4.isIs_correct()){
                            alreadyAnswered();
                            answer4.setBackgroundResource(R.color.green);
                            trivialViewModel.correctAnswer(question);

                        }else{
                            alreadyAnswered();
                            answer4.setBackgroundResource(R.color.red);
                            trivialViewModel.wrongAnswer(question);

                        }
                    }
                });

                }
        });


        game.setCanceledOnTouchOutside(false);


        return game;
    }

    private void alreadyAnswered() {
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);
    }

    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_question, null, false);

        category = rootView.findViewById(R.id.textView_category);
        question_textView = rootView.findViewById(R.id.textView_question);
        answer1 = rootView.findViewById(R.id.button_ans1);
        answer2 = rootView.findViewById(R.id.button_ans2);
        answer3 = rootView.findViewById(R.id.button_ans3);
        answer4 = rootView.findViewById(R.id.button_ans4);



    }

}
