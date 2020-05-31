package cat.udl.tidic.amd.dam_tips.viewmodels;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

public class MainActivityViewModel {


    public MainActivityViewModel() {
    }

    public Boolean isCurrentLogIn(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        return !token.equals("");
    }



}


