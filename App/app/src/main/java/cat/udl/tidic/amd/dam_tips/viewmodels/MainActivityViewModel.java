package cat.udl.tidic.amd.dam_tips.viewmodels;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.repositories.AccountRepo;

public class MainActivityViewModel {
    private SharedPreferences sp;
    private AccountRepo repo;
    private MutableLiveData<Integer> mut;


    public MainActivityViewModel() {
        repo = new AccountRepo();
        mut = repo.getmSizequestions();
        sp = PreferencesProvider.providePreferences();

    }

    public Boolean isCurrentLogIn(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        return !token.equals("");
    }

    public void getSizeQUesiotn(){
        //String token = "a8635c726c0692c9c44c26de7a60453ab774969e31108be918";
        repo.getQuestions();

    }

    public MutableLiveData<Integer> getresponseSize(){
        return mut;
    }



}


