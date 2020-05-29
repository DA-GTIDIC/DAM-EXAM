package cat.udl.tidic.amd.dam_tips.views;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dialog.DialogGame;
import cat.udl.tidic.amd.dam_tips.dialog.DialogGameOver;
import cat.udl.tidic.amd.dam_tips.dialog.DialogGameWon;
import cat.udl.tidic.amd.dam_tips.utils.Trivial;
import cat.udl.tidic.amd.dam_tips.viewmodels.TrivialViewModel;

public class GameActivity extends CustomActivty implements LifecycleOwner {

    private final int lifes = 3;

    private String TAG = getClass().getSimpleName();

    private Button roll;
    private CheckBox category_db;
    private CheckBox category_patterns;
    private CheckBox category_os;
    private CheckBox category_net;

    private ImageView vida1;
    private ImageView vida2;
    private ImageView vida3;

    private RatingBar progress_dB;
    private RatingBar progress_os;
    private RatingBar progress_net;
    private RatingBar progress_pat;

    private TrivialViewModel trivialViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trivialViewModel = new TrivialViewModel(getApplication());

        initViews();

        trivialViewModel.getScore().observe(this, new Observer<Trivial>() {
            @Override
            public void onChanged(Trivial trivial) {
                Log.d(TAG, trivial.toString());
                progress_dB.setProgress(trivial.getDb_correct_answers());
                if (trivial.getDb_correct_answers() == 3){
                    category_db.setChecked(true);
                }

                progress_os.setProgress(trivial.getOs_correct_answers());
                if (trivial.getOs_correct_answers() == 3){
                    category_os.setChecked(true);
                }

                progress_net.setProgress(trivial.getNet_correct_answers());
                if (trivial.getNet_correct_answers() == 3){
                    category_net.setChecked(true);
                }

                progress_pat.setProgress(trivial.getPatterns_correct_answers());
                if (trivial.getPatterns_correct_answers() == 3){
                    category_patterns.setChecked(true);
                }

                if (trivialViewModel.allCompleted()){
                    DialogGameWon gamewon = DialogGameWon.newIntance(GameActivity.this);
                    gamewon.show(getSupportFragmentManager(), "YOU WON THE GAME!");
                }

            }
        });
        trivialViewModel.getLifes().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer lifes) {
                Log.d(TAG, "TE QUEDAN vidas = " + lifes);
                if (lifes == 2){
                    vida3.setVisibility(View.INVISIBLE);
                }if (lifes == 1){
                    vida2.setVisibility(View.INVISIBLE);
                }if (lifes == 0){
                    vida1.setVisibility(View.INVISIBLE);
                    DialogGameOver gameOver = DialogGameOver.newInstance(GameActivity.this);
                    gameOver.show(getSupportFragmentManager(), "GAME OVER");

                }
            }
        });

    }

    private void initViews() {
        setContentView(R.layout.activity_game);
        roll = findViewById(R.id.roll);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGame game = DialogGame.newInstance(GameActivity.this, trivialViewModel);
                game.show(getSupportFragmentManager(), "Trivial Game");
            }
        });


        vida1 = findViewById(R.id.vida1);
        vida2 = findViewById(R.id.vida2);
        vida3 = findViewById(R.id.vida3);

        progress_dB = findViewById(R.id.ratingBar_dB);
        progress_os = findViewById(R.id.ratingBar_os);
        progress_net = findViewById(R.id.ratingBar_net);
        progress_pat = findViewById(R.id.ratingBar_patterns);

        category_db = findViewById(R.id.checkBox_dB);
        category_os = findViewById(R.id.checkBox_os);
        category_net = findViewById(R.id.checkBox_net);
        category_patterns = findViewById(R.id.checkBox_pat);


    }


}
