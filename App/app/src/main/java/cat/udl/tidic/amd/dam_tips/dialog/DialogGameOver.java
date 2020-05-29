package cat.udl.tidic.amd.dam_tips.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.views.CustomActivty;
import cat.udl.tidic.amd.dam_tips.views.DashboardActivity;
import cat.udl.tidic.amd.dam_tips.views.GameActivity;

public class DialogGameOver extends DialogFragment {

    public View rootView;
    public CustomActivty activity;

    public static DialogGameOver newInstance(CustomActivty activity) {
        DialogGameOver gameOver = new DialogGameOver();
        gameOver.activity = activity;
        return gameOver;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initView();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("PlAY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent restart = new Intent(activity, GameActivity.class);
                startActivity(restart);
            }
        });
        dialog.setNegativeButton("GIVE UP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent restart = new Intent(activity, DashboardActivity.class);
                startActivity(restart);
            }
        });

        AlertDialog gameOver = dialog.setView(rootView).setCancelable(true).create();

        gameOver.setCanceledOnTouchOutside(false);
        return gameOver;
    }

    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_gameover, null, false);

    }
}
