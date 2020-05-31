package cat.udl.tidic.amd.dam_tips.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.repositories.QuestionRepo;

public class QuestionViewModel {

    private QuestionRepo questionRepo;

    public QuestionViewModel(){questionRepo = new QuestionRepo();}

    public void getQuestions(String auth){questionRepo.getQuestions(auth);}

    public MutableLiveData<List<Question>> getResponseQuestions(){
        return this.questionRepo.getmResponseQuestions();
    }
}
