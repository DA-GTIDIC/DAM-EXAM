package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dialogs.DialogRound;
import cat.udl.tidic.amd.dam_tips.models.Game;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.viewmodels.GameViewModel;

public class PlayActivity extends AppCompatActivity {

    private Button ronda;
    private GameViewModel gameViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_play);
        setContentView(R.layout.activity_play);
        gameViewModel = new GameViewModel(getApplication());

        ronda = findViewById(R.id.roll);

        gameViewModel.getResponseLiveDataQuestion().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question s) {
                Log.d("Play Activity", "Tenim question " + s.getQuestion() + " id: " + s.getId());
                Toast.makeText(PlayActivity.this, s.getId() + " : " + s.getQuestion(), Toast.LENGTH_LONG).show();

                DialogRound first = DialogRound.newInstance(PlayActivity.this,gameViewModel,s);
                first.show(getSupportFragmentManager(), "question");

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
