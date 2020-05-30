package cat.udl.tidic.amd.dam_tips.dao;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.models.User;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class AccountDAOImpl implements AccountDAO{

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<JsonObject> createTokenUser() {
        return  retrofit.create(AccountDAO.class).createTokenUser();
    }

    @Override
    public Call<Void> deleteTokenUser(JsonObject token) {
        return  retrofit.create(AccountDAO.class).deleteTokenUser(token);
    }
    @Override
    public Call<Void> createPregunta(@Body Pregunta questionJSON) {
        return  retrofit.create(AccountDAO.class).createPregunta(questionJSON);
    }
    @Override
    public Call<String> getNumber(@Path("owner_id") String owner) {
        return  retrofit.create(AccountDAO.class).getNumber(owner);
    }
    public Call<User> getUserProfile() {
        return  retrofit.create(AccountDAO.class).getUserProfile();
    }
}
