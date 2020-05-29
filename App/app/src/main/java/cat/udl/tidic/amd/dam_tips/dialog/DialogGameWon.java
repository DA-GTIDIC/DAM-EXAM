package cat.udl.tidic.amd.dam_tips.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
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

public class DialogGameWon extends DialogFragment {
    public View rootView;
    public CustomActivty activity;

    public static DialogGameWon newIntance(CustomActivty activity) {
        DialogGameWon gameWon = new DialogGameWon();
        gameWon.activity = activity;
        return gameWon;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initView();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setPositiveButton("GO TO OPTIONS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.goTo(DashboardActivity.class);
            }
        });
        AlertDialog gameWon = dialog.setView(rootView).setCancelable(true).create();

        gameWon.setCanceledOnTouchOutside(false);
        return gameWon;
    }

    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_gamewon, null, false);

    }
}
