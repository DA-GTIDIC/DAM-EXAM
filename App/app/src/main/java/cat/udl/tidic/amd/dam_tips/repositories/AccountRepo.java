package cat.udl.tidic.amd.dam_tips.repositories;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAOImpl;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepo {

    private String TAG = "AccountRepo";

    private AccountDAO accountDAO;
    private MutableLiveData<String> mResponseLogin;

    public AccountRepo() {
        this.accountDAO = new AccountDAOImpl();
        this.mResponseLogin = new MutableLiveData<>();
    }


    public void createTokenUser(){


        accountDAO.createTokenUser().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                int code = response.code();
                Log.d(TAG,  "createTokenUser() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){

                    JsonObject res = response.body();
                    Log.d(TAG,  "createTokenUser() -> ha rebut del backend un missatge:  " + res);

                    String authToken = res.get("token").getAsString();
                    Log.d(TAG,  "createTokenUser() -> ha rebut el token:  " + authToken);
                    String aExam = res.get("exam").getAsString();
                    Log.d(TAG,  "createTokenUser() -> ha rebut el token de l'examen:  " + aExam);
                    mResponseLogin.setValue(authToken);
                    PreferencesProvider.providePreferences().edit().
                            putString("token", authToken).apply();
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error:  " + error_msg);
                        PreferencesProvider.providePreferences().edit().remove("token").apply();
                        mResponseLogin.setValue(error_msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "createTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
                PreferencesProvider.providePreferences().edit().remove("token").apply();
                mResponseLogin.setValue(error_msg);
            }

        });
    }


    public void deleteTokenUser(){
        String token = PreferencesProvider.providePreferences().getString("token","");
        Log.d(TAG,  "deleteTokenUser() -> eliminarem aquest token:  " + token);
        JsonObject body = new JsonObject();
        body.addProperty("token", token);
        accountDAO.deleteTokenUser(body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
                Log.d(TAG,  "deleteTokenUser() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){
                    PreferencesProvider.providePreferences().edit().remove("token").apply();
                    mResponseLogin.setValue("Ok. 200. Deleted.");
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "deleteTokenUser() -> ha rebut l'error:  " + error_msg);
                        mResponseLogin.setValue(error_msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage();
                Log.d(TAG,  "deleteTokenUser() onFailure() -> ha rebut el missatge:  " + error_msg);
                mResponseLogin.setValue(error_msg);
            }

        });
    }

    public MutableLiveData<List<Question>> getQuestionList(){

        accountDAO.getQuestList().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(response.code() == 200){
                    Log.d(TAG, "Llista d'assignatures obtinguda");
                    List<Question> list = response.body();
                    Log.d(TAG, "Llista retornada" + list);
                }
                else{
                    Log.d(TAG, "size FAIL() onFailure --> ha rebut l'error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d(TAG, "Error en la comunicació amb la API: " + t.getMessage());
            }
            });
        return null;
    }





    public MutableLiveData<String> getmResponseLogin() {
        return mResponseLogin;
    }

}
