package cat.udl.tidic.amd.dam_tips.viewmodels;

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



