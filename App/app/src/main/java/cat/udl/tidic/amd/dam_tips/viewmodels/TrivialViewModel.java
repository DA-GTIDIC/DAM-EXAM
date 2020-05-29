package cat.udl.tidic.amd.dam_tips.viewmodels;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import cat.udl.tidic.amd.dam_tips.models.CategoryEnum;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.repositories.TrivialRepo;
import cat.udl.tidic.amd.dam_tips.utils.Trivial;

public class TrivialViewModel extends AndroidViewModel {

    private String TAG = getClass().getSimpleName();

    private int lifes = 3;

    private SharedPreferences mPreferences;
    private TrivialRepo trivialRepo;
    private Trivial trivialGame = new Trivial();

    private MutableLiveData<Question> questionResponse;
    private MutableLiveData<Trivial> scoreResponse;
    private MutableLiveData<Integer> lifesResponse;

    public TrivialViewModel(@NonNull Application application) {
        super(application);
        trivialRepo = new TrivialRepo();
        mPreferences = PreferencesProvider.providePreferences();
        questionResponse = trivialRepo.getQuestionResponse();
        scoreResponse = new MutableLiveData<>();
        lifesResponse = new MutableLiveData<>();

    }

    public void getQuestion(){
        CategoryEnum randomCategory = Trivial.getRandomCategory();
        String token = mPreferences.getString("token", "");
        Log.d(TAG, "Categoria : " + randomCategory.getName());
        trivialRepo.getRandomQuestion(token, randomCategory);

    }


    public MutableLiveData<Question> getQuestionResponse(){
        return questionResponse;
    }

    public void correctAnswer(Question question) {
        trivialGame.correctAnswer(question.getCategory());
        scoreResponse.setValue(trivialGame);

    }

    public void wrongAnswer(Question question) {
        lifes--;
        if (lifes == 0){
            //HAS PERDUT
        }
        lifesResponse.setValue(lifes);

    }

    public MutableLiveData<Integer> getLifes() {
        return lifesResponse;
    }

    public MutableLiveData<Trivial> getScore() {
        return scoreResponse;
    }

    public boolean allCompleted() {
        return (trivialGame.getDb_correct_answers() == 3 &&
            trivialGame.getNet_correct_answers() == 3 &&
            trivialGame.getOs_correct_answers() == 3 &&
            trivialGame.getPatterns_correct_answers() == 3);
    }
}
