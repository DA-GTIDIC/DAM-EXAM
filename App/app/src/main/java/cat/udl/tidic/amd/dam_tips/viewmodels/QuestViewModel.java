package cat.udl.tidic.amd.dam_tips.viewmodels;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.repositories.AccountRepo;

public class QuestViewModel {

    private final String TAG = "QuestViewModel";
    private AccountRepo accountRepo;

    public QuestViewModel(){
        accountRepo = new AccountRepo();
    }

    public void getQuestion(){
        accountRepo.getQuestionList();
    }

}



