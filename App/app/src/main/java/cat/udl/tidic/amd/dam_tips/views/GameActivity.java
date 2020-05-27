package cat.udl.tidic.amd.dam_tips.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Dialog;
import cat.udl.tidic.amd.dam_tips.models.Player;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.utils.GameManager;
import cat.udl.tidic.amd.dam_tips.viewmodels.GameViewModel;
import params.com.stepprogressview.StepProgressView;

public class GameActivity extends CustomActivty {

    public GameViewModel gameViewModel;
    public Button round;
    private Button restartButton;
    private TextView resultText;
    public Player player;
    public ImageView[] lives;
    public StepProgressView pb;
    public TextView cat;
    private CardView pantallafinal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player = new Player();
        gameViewModel = new GameViewModel(this, this);
        getAllActivityData();
        setOnClickListeners();
        setUpObservers();
        refreshUI();
    }

    private void setUpObservers()
    {
        gameViewModel.getAQ().observe(this, this::onGetQuestion);
        gameViewModel.validateAnswer().observe(this, this::onValidateQuestion);
    }



    @SuppressLint("SetTextI18n")
    private void onGetQuestion(Question question)
    {
        player.setCurrentCategory(question.getCat());
        setCatText(question.getCat().toUpperCase(),player.getCategoriaNumber(question.getCat()));
        Dialog.GenerateQuestion(question,this,this);
    }

    public void setCatText(String categoria, int encertades)
    {
        cat.setText("Pregunta de la Categoria : " + categoria + "("+ encertades +"/" + GameManager.MAXIM_PER_CATEGORIA + ")");
    }

    public void getAllActivityData()
    {
        lives = new ImageView[3];
        round = findViewById(R.id.roll);
        lives[0] = findViewById(R.id.vida1);
        lives[1] = findViewById(R.id.vida2);
        lives[2] = findViewById(R.id.vida3);
        cat = findViewById(R.id.categoria);
        pb = findViewById(R.id.stepProgressView2);
        restartButton = findViewById(R.id.restartbutton);
        resultText = findViewById(R.id.finalResult);
        pantallafinal = findViewById(R.id.pantallafinal);
    }

    public void refreshUI()
    {
        pb.setCurrentProgress(player.getProgress());
        refreshLives();

        if (player.getLives() == 0)
        {
            showEndUI(getString(R.string.lose));
        }
        else if ( player.getProgress() == GameManager.MAXPOINTS)
        {
            showEndUI(getString(R.string.win));
        }
    }

    void refreshLives()
    {
        for(int i = player.getLives(); i < GameManager.LIVES; i++)
        {
            lives[i].setVisibility(View.GONE);
        }
    }

    private void showEndUI(String text)
    {
        pantallafinal.setVisibility(View.VISIBLE);
        resultText.setText(text);
    }

    void setOnClickListeners()
    {
        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                gameViewModel.getAQuestion(player.generateRandomCategory());
                round.setEnabled(false);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               restartGame();
            }
        });
    }

    private void restartGame()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    private void onValidateQuestion(Boolean validate)
    {
        if (validate)
        {
            Log.d("GameActivity", "THE USER ANSWER IS CORRECT!");
            player.sumarPreguntaCorrecta();
            setCatText( player.getCurrentCategory().toUpperCase(), player.getCategoriaNumber(player.getCurrentCategory()));
        }
        else
        {
            Log.d("GameActivity", "THE USER ANSWER IS INCORRECT!");
            player.restarVida();

        }

        refreshUI();
    }

    public void validateAnswer(Question q, String selected)
    {
        Log.d("GameActivity", "THE USER ANSWER IS "+ selected);
        gameViewModel.validatUserAnswer(q,selected);
    }

    public void ErrorDialog()
    {
        Dialog.ErrorDialog(this,this);
        round.setEnabled(true);
    }


}
