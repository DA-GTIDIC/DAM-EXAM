package cat.udl.tidic.amd.dam_tips.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Question;

public class DialogRate extends AppCompatDialogFragment {

    private View view;
    private QuestionList question;

    public static DialogRate newInstance(QuestionList question){
        DialogRate dialog = new DialogRate();
        dialog.question = question;
        return dialog;
    }

    public Dialog CreateDialog(@Nullable Bundle savedInstanceState){
        init();
        AlertDialog dialogalerta = new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle("Rate question")
                .setCancelable(true)
                .setPositiveButton("Close", null)
                .create();
        return dialogalerta;
    }

    private void init() {
        ViewGroup parent;
        ViewGroup root;
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_ratedialog, null, false);
    }
}
