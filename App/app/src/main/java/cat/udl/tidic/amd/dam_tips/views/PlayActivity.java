package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dialogs.DialogRound;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.GameViewModel;
import params.com.stepprogressview.StepProgressView;

public class PlayActivity extends AppCompatActivity {

    private Button ronda;
    private GameViewModel gameViewModel;
    public TextView respuestaBack;
    private StepProgressView progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_play);
        setContentView(R.layout.activity_play);
        gameViewModel = new GameViewModel(getApplication());

        ronda = findViewById(R.id.roll);
        respuestaBack = findViewById(R.id.resp);

        gameViewModel.getResponseLiveDataQuestion().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question s) {
                Log.d("Play Activity", "Tenim question " + s.getQuestion() + " id: " + s.getId());
                //Toast.makeText(PlayActivity.this, s.getId() + " : " + s.getQuestion(), Toast.LENGTH_LONG).show();

                DialogRound first = DialogRound.newInstance(PlayActivity.this,gameViewModel,s);
                first.show(getSupportFragmentManager(), "question");

            }
        });
        //Para ver como nos ha venido una respuesta del dialog
        respuestaBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(PlayActivity.this, "Nos ha venido una respuesta corracta?: " + respuestaBack.getText().toString(), Toast.LENGTH_LONG).show();
                Boolean actualizado = gameViewModel.actualizarResults(respuestaBack.getText().toString());
                if(actualizado){
                    //hemos perdido? sino,informamos del fallo
                    if(gameViewModel.getDefeat()){
                        Toast.makeText(PlayActivity.this, "Hemos perdido totalmente ", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(PlayActivity.this, "Has fallado la pregunta, una vida menos ", Toast.LENGTH_LONG).show();

                    }

                }
                else{
                    //si hemos ganado lo decimos, sino no pasa nada
                    if(gameViewModel.getWin()){
                        Toast.makeText(PlayActivity.this, "Has ganado colega", Toast.LENGTH_LONG).show();
                        Intent da = new Intent(PlayActivity.this,WinActivity.class);
                        startActivity(da);
                    }
                }
            }
        });

        ronda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameViewModel.getQuestion();
            }
        });
    }
}
