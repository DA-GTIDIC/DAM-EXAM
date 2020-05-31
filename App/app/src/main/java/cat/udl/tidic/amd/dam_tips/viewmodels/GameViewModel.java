package cat.udl.tidic.amd.dam_tips.viewmodels;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.tidic.amd.dam_tips.models.Game;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.services.GameServiceI;
import cat.udl.tidic.amd.dam_tips.services.GameServiceImpl;

public class GameViewModel extends AndroidViewModel {

    private GameServiceI respository;
    private Game game;
    private MutableLiveData<Question> responseQuestion;
    private int currentCategory = 0;
    private SharedPreferences mPreferences;

    public void init(){
    }

    public GameViewModel(@NonNull Application application){
        super(application);
        game = new Game();
        respository = new GameServiceImpl();
        responseQuestion = respository.getLiveDataQuestion();
        this.mPreferences = PreferencesProvider.providePreferences();
    }


    public void getQuestion(){

/*
        String cat = categories[this.currentCategory];

        this.currentCategory++;

        if(this.currentCategory >= categories.length){
            this.currentCategory= 0;
        }
        */
        String cat = game.getAvailableCategory();
        String header = this.mPreferences.getString("token","");
        Log.d("token","Token: " + header);
        respository.getQuestion(header,cat);

    }

    public Boolean actualizarResults(String result){
        //devuelve true si se ha perdido una vida, false si no
        if(result.equals("-1")){
            game.fallarPregunta();
            return true;
        }
        else{
            game.encertarPregunta(result);
            return false;
        }
    }

    public Boolean getDefeat(){
        return game.gameFailed();
    }

    public Boolean getWin(){
        return game.gameWinned();
    }


    public LiveData getResponseLiveDataQuestion(){
        return responseQuestion;
    }


   // public void ronda();
}
