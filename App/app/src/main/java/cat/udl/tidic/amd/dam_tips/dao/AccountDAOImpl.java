package cat.udl.tidic.amd.dam_tips.dao;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amd.dam_tips.models.Pregunta;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AccountDAOImpl implements AccountDAO {

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
    public Call<List<Pregunta>> getQuestionlist() {
        return  retrofit.create(AccountDAO.class).getQuestionlist();
    }
}
