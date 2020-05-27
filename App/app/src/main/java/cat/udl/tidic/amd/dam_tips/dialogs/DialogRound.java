package cat.udl.tidic.amd.dam_tips.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.GameViewModel;

public class DialogRound extends DialogFragment {


    public View rootView;
    private FragmentActivity activity;
    private GameViewModel gameViewModel;
    public Question question;
    public static DialogRound newInstance(FragmentActivity activity, GameViewModel viewModel, Question q){
        DialogRound dialog = new DialogRound();
        Bundle args = new Bundle();
        dialog.activity = activity;
        dialog.gameViewModel = viewModel;
        dialog.setQuestion(q);
        return dialog;
    }
    public void setQuestion(Question q){
        this.question = q;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = builder.setView(rootView)
                .setCancelable(false)
                .create();

        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    private void initView() {
        /*
        rootView = LayoutInflater.from
                (getContext()).inflate(R.layout.dialog_set_profile_step3, null, false);
        recyclerView_generes = rootView.findViewById(R.id.recyclerView_generes);
        add_generes = rootView.findViewById(R.id.imageView_addGenere);

         */
    }
}
