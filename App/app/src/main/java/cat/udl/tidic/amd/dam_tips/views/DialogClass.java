package cat.udl.tidic.amd.dam_tips.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import cat.udl.tidic.amd.dam_tips.Preguntes;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Pregunta;


public class DialogClass extends AppCompatDialogFragment {

    private AccountDAO userService;
    private SharedPreferences mPreferences;
    private String TAG = "DIALOGFILTER";
    private Preguntes preguntes;
    private Pregunta pregunta;
    private View rootView;
    private RatingBar ratingBar;

    public static DialogClass newInstance(Preguntes preguntes){
        DialogClass dialog = new DialogClass();
        dialog.preguntes = preguntes;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        init();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("Valora la pregunta")
                .setCancelable(true)
                .setPositiveButton("Cerrar", null)
                .create();
        return alertDialog;
    }
    private void init(){
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog, null, false);

    }

}
