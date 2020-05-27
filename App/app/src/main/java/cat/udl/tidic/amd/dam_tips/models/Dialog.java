package cat.udl.tidic.amd.dam_tips.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.views.GameActivity;

public class Dialog
{
    public static void GenerateQuestion(Question q, Context c, GameActivity gameActivity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(q.getQuestion());
        builder.setCancelable(false);
        final int[] checkedItem = {0};

        builder.setSingleChoiceItems(convertToArray(q), checkedItem[0], new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                checkedItem[0] = which;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                int selected = checkedItem[0];
                String answer = convertToArray(q)[selected];
               gameActivity.validateAnswer(q, answer);
               gameActivity.round.setEnabled(true);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void ErrorDialog(Context c, GameActivity gameActivity)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(c.getString(R.string.errorconnection));
        alertDialog.setMessage(c.getString(R.string.errorconnectionBody));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private static String[] convertToArray(Question q)
    {
        List<String> answers = new ArrayList<String>();
        for (Answer a : q.getAnswer())
        {
            answers.add(a.getAnswerBody());
        }

        return answers.toArray(new String[0]);
    }
}
