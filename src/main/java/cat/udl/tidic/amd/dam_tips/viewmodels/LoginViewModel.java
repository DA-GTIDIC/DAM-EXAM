package cat.udl.tidic.amd.dam_tips.viewmodels;

import android.content.SharedPreferences;
import android.util.Base64;

import androidx.lifecycle.MutableLiveData;

import java.nio.charset.StandardCharsets;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.repositories.AccountRepo;

public class LoginViewModel {

    public static final int ERROR_EMPTY_EMAIL = 1;
    public static final int ERROR_EMPTY_PASS = 2;


    private AccountRepo accountRepo;

    public LoginViewModel(){
        accountRepo = new AccountRepo();
    }

    public int validate(String email, String password){

        if (email.isEmpty()) {
            return ERROR_EMPTY_EMAIL;
        }

        if (password.isEmpty()) {
            return ERROR_EMPTY_PASS;
        }

        return 0;
    }

    public void login(String email, String password){

        String auth_token = email + ":" + password;
        byte[] data = auth_token.getBytes(StandardCharsets.UTF_8);
        auth_token = Base64.encodeToString(data, Base64.DEFAULT);
        auth_token = ("Authentication " + auth_token).trim();

        // Ha de ser commit, ens hem d'assegurar que i són per l'interceptor.
        PreferencesProvider.providePreferences().edit().putString("token", auth_token).commit();

        this.accountRepo.createTokenUser();
    }

    public void logout(){
        this.accountRepo.deleteTokenUser();
    }


    public MutableLiveData<String> getResponseLogin() {
        return this.accountRepo.getmResponseLogin();
    }
}
